package com.ecotravel.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import jdbc.dao.DriverDAO;
import jdbc.dao.TripBetweenTownsDAO;
import jdbc.model.Addvertisment;
import jdbc.model.Driver;
import jdbc.model.Person;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import utils.MailSender;

@Controller
public class AdvertisementsController {

	// redirects driver from choose form to create trip
	@RequestMapping(value="CreateTrip", method = RequestMethod.GET)
	public String sendDriverToCreateTrip(HttpSession session) {
		return "CreateTrip";
	}
	

	
	
	// redirects to driver/passenger to AdvertisementsPage
	@RequestMapping(value="AdvertisementsPage", method = RequestMethod.GET)
	public String sendPassengerTo(HttpSession session) {
		return "AdvertisementsPage";
	}
	
	
	
	
	@RequestMapping(value="SearchAdvertisement", method = RequestMethod.GET)
	public String searchAdvertisements(@RequestParam String fromCity,
								@RequestParam String toCity, 
								@RequestParam String date, HttpSession session, Model model) {

		// WARNING!!! the order of arguments must match order of inputs in <form> !!!

		System.out.println("from: " + fromCity);
		System.out.println("to: " + toCity);
		System.out.println("date: " + date);
		model.addAttribute("searching_msg", "Searching...");
		
		// here call a method to find advertisements
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		TripBetweenTownsDAO tripDAO = (TripBetweenTownsDAO) context.getBean("tripBetweenTownsDAO");

		List<Addvertisment> ads = tripDAO.showMatchingAdvertisments(fromCity, toCity, date);
	
		for(Addvertisment ad : ads) {
			System.out.println("driver: " + ad.getDriver().getProfile().getUsername());
		}
		
//		if(ads.isEmpty())
//			model.addAttribute("no_trips_msg", "Sorry, no matching trips.");
		
		session.setAttribute("matching_advertisements", ads);
		return "ChooseTrip";
	}
	
	
	
	
	@RequestMapping(value="SubscribeForTrip", method = RequestMethod.GET)
	public String subscribeUserToTrip(@RequestParam String driverUsername, 
									HttpSession session, Model model) {
		
//		System.out.println("Subscribing for ad: " + selectedAdv);

//		List<Addvertisment> matchingAds = (List<Addvertisment>)session.getAttribute("matching_ads");
//		String driverUsername = matchingAds.get(selectedAdv).getDriver().getProfile().getUsername();
		
		System.out.println("Driver in this ad is: " + driverUsername);
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//		MailSender emailer = (MailSender) context.getBean("mailSender");
		MailSender emailer = new MailSender();
		try{
			Person currentUser = (Person) session.getAttribute("loggedInUser");
			
			emailer.sendTripEmail(driverUsername, currentUser.getProfile().getUsername());
			model.addAttribute("email_sent_msg", "Successfully sent email :) \n Wait the driver to mail you.");
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			DriverDAO driverDAO = (DriverDAO) context.getBean("driverDAO");
			driverDAO.receivedMails(driverUsername);
		}
		catch(RuntimeException e){
			model.addAttribute("email_sent_msg", "For some reasons you can't apply for this trip now! Please try again!");
		}
		
		return "AdvertisementsPage";
	}
	
	
	
	
	// ADDs an advertisement
	@RequestMapping(value="CreateTrip", method = RequestMethod.POST)
	public String createNewTrip(@RequestParam String fromCity,
							@RequestParam String toCity,
							@RequestParam String date,
							@RequestParam String time,
							@RequestParam int freePlaces, Model model, HttpSession session) {
		
		System.out.println("fromCity: " + fromCity);
		System.out.println("toCity: " + toCity);
		System.out.println("date: " + date);
		System.out.println("time: " + time);
		System.out.println("fromCfreePlaces: " + freePlaces);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DriverDAO driverDAO = (DriverDAO) context.getBean("driverDAO");
		
		Driver currentUser = (Driver) session.getAttribute("loggedInUser");
		String username = currentUser.getProfile().getUsername();
		
		if(driverDAO.getActiveAdvertismentsForDriver(username).size() >= 1) {
			model.addAttribute("create_trip_error_msg", "You can not add any more trips at this time.");
			return "CreateTrip";
		}
		else
			driverDAO.openAdvertisment(username, fromCity, toCity, date, time, freePlaces);

		// here update session attribute "active_ads"
		List<Addvertisment> activeAds = driverDAO.getActiveAdvertismentsForDriver(username);
		session.setAttribute("active_ads", activeAds);
		
		return "redirect:Profile";
		
	}
	
	
	
	
	// if driver wants to delete his advertisement
	@RequestMapping(value="DeleteAdvertisement", method = RequestMethod.GET)
	public String deleteAdvertisement(HttpSession session, Model model) {
		
		Person currentUser = (Person) session.getAttribute("loggedInUser");
		Addvertisment adv = ((List<Addvertisment>)session.getAttribute("active_ads")).get(0);
		String dateOfAdv = adv.getDate();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DriverDAO driverDAO = (DriverDAO) context.getBean("driverDAO");
		driverDAO.deleteAdvertisment(currentUser.getProfile().getUsername(), dateOfAdv);
		
		// here update session attribute "active_ads"
		List<Addvertisment> activeAds = driverDAO.getActiveAdvertismentsForDriver(currentUser.getProfile().getUsername());
		session.setAttribute("active_ads", activeAds);
		
		return "redirect:Profile";
	}
	
	
	
	
	
	@RequestMapping(value="EditAdvertisementFreePlaces", method = RequestMethod.GET)
	public String goToEditAdvertisementFreePlaces() {
		return "EditAdvertisementFreePlaces";
	}
	
	
	
	
	//if driver wants to edit his edit advertisement
	@RequestMapping(value="EditFreePlaces", method = RequestMethod.POST) 
	public String editAdvertisement(@RequestParam int freePlaces,
									HttpSession session, Model model) {
		
		System.out.println("New free places to set: " + freePlaces);
		
		Person currentUser = (Person) session.getAttribute("loggedInUser");
		Addvertisment adv = ((List<Addvertisment>)session.getAttribute("active_ads")).get(0);
		String dateOfAdv = adv.getDate();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DriverDAO driverDAO = (DriverDAO) context.getBean("driverDAO");
		driverDAO.updateAdvertisment(currentUser.getProfile().getUsername(), dateOfAdv, freePlaces);
		
		// here update session attribute "active_ads"
		List<Addvertisment> activeAds = driverDAO.getActiveAdvertismentsForDriver(currentUser.getProfile().getUsername());
		session.setAttribute("active_ads", activeAds);
		
		return "redirect:Profile";
	}
	
	
	
	
}
