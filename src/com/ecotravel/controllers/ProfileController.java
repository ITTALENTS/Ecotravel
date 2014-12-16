package com.ecotravel.controllers;

import javax.servlet.http.HttpSession;

import jdbc.dao.DriverDAO;
import jdbc.dao.ProfileDAO;
import jdbc.model.Driver;
import jdbc.model.Passenger;
import jdbc.model.Person;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
	
	
	@RequestMapping(value = "Profile", method = RequestMethod.GET)
	public String viewPersonalProfile(HttpSession session, Model model) {
		
		Person p = (Person) session.getAttribute("loggedInUser");
		
		model.addAttribute("birthYear", p.getBirthYear());
		model.addAttribute("name", p.getName());
		model.addAttribute("email", p.getProfile().getEmail());
		model.addAttribute("username", p.getProfile().getUsername());
		model.addAttribute("rating", p.getRating());
		model.addAttribute("phone", p.getTelephone());
		
		if(p instanceof Driver) {
			model.addAttribute("isSmoking", ((Driver) p).getIsSmoking() ? "Yes" : "No");
			model.addAttribute("licenseYear", ((Driver) p).getLicenseYear());
			model.addAttribute("music", ((Driver) p).getMusicInTheCar());
			model.addAttribute("numberOfTravels", ((Driver) p).getNumberOfTravels());
			return "ProfilePageDriver";
		} else if (p instanceof Passenger) {
			return "ProfilePagePassenger";
		} else return null;
		
	}
	
	
	
	
	@RequestMapping(value = "EditProfile", method = RequestMethod.GET)
	public String editProfile(HttpSession session, Model model) {
		
		Person p = (Person)session.getAttribute("loggedInUser");
		
		//TODO: ADD CODE HERE !!!!!!!!!!!!1
		
		if(p instanceof Person)
			return "redirect:ProfilePagePassenger";
		else // p instanceof Driver
			return "redirect:ProfilePageDriver";
	}

	
	
	
	// this controller redirects Driver to ChooseForm when he clicks Home button
	// redirects Passenger to AdvertisementsPage when he clicks Home button
	// redirects user to Welcome if he isn't logged in
	@RequestMapping(value="Home", method = RequestMethod.GET)
	public String goToChooseForm(HttpSession session) {
		if(session.getAttribute("loggedInUser") instanceof  Driver){
			return "ChooseForm";
		}
		else if(session.getAttribute("loggedInUser") instanceof  Passenger){
			return "AdvertisementsPage";
		}
		else{
	    	return "redirect:Welcome";
		}
	}
	
	
	
	// this controller is for header's link AboutUs
	@RequestMapping(value="AboutUs", method = RequestMethod.GET)
	public String openAboutUsPage() {
		
		return "AboutUs";
	}
	
	
	// this controller is for header's link History
	@RequestMapping(value="History", method = RequestMethod.GET)
	public String openHistoryPage() {
		
		return "History";
	}
	
	
	
	
	// when passenger views advertisements from ChooseTrip and wants to see the
	// profile of the driver who has posted the advertisement:
	@RequestMapping(value="ViewDriversProfile", method = RequestMethod.GET)
	public String viewAnotherDriverProfile(@RequestParam String driverUsername, 
										HttpSession session, Model model) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DriverDAO driverDAO = (DriverDAO) context.getBean("driverDAO");
		Driver driver = driverDAO.showProfile(driverUsername);
		
		model.addAttribute("name", driver.getName());
		model.addAttribute("username", driver.getProfile().getUsername());
		model.addAttribute("birthYear", driver.getBirthYear());
		model.addAttribute("email", driver.getProfile().getEmail());
		model.addAttribute("phone", driver.getTelephone());
		model.addAttribute("licenseYear", driver.getLicenseYear());
		model.addAttribute("isSmoking", driver.getIsSmoking());
		model.addAttribute("music", driver.getMusicInTheCar());
		model.addAttribute("numberOfTravels", driver.getNumberOfTravels());
		model.addAttribute("rating", driver.getRating());
		
		return "ProfileOfAnotherDriver";
	}
	
	
	
	
}
