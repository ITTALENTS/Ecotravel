package com.ecotravel.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import jdbc.dao.DriverDAO;
import jdbc.dao.TripBetweenTownsDAO;
import jdbc.model.Addvertisment;
import jdbc.model.Driver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	
	@RequestMapping(value="AdvertisementsPage", method = RequestMethod.POST)
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

		List<Addvertisment> ads = tripDAO.showActiveAdvertisments(fromCity, toCity, date);
		
		session.setAttribute("all_valid_advertisements", ads);
		
		return "ChooseTrip";
	}
	
	
	@RequestMapping(value="SubscribeForTrip", method = RequestMethod.POST)
	public String subscribeUserToTrip(@RequestParam int advertismentId, HttpSession session, Model model) {
		
		session.setAttribute("advertisement_id", advertismentId);
		
		model.addAttribute("email_sent_msg", "Successfully sent email :) \n Wait the driver to mail you.");
		
		return "AdvertiementsPage";
	}
	
	
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
		System.out.println("fromCfreePlaces" + freePlaces);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DriverDAO driverDAO = (DriverDAO) context.getBean("driverDAO");
		
		Driver currentUser = (Driver) session.getAttribute("logeInUser");
		String username = currentUser.getProfile().getUsername();
		
		driverDAO.openAdvertisment(username, fromCity, toCity, date, time, freePlaces);

		return "ProfilePageDriver";
		
	}
	
	
	//TODO: add controller for edit advertisement
	
}
