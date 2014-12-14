package com.ecotravel.controllers;

import javax.servlet.http.HttpSession;

import jdbc.model.Driver;
import jdbc.model.Passenger;
import jdbc.model.Person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			model.addAttribute("isSmoking", ((Driver) p).getIsSmoking());
			model.addAttribute("licenseYear", ((Driver) p).getLicenseYear());
			model.addAttribute("music", ((Driver) p).getMusicInTheCar());
			model.addAttribute("numberOfTravels", ((Driver) p).getNumberOfTravels());
			return "ProfilePageDriver";
		} else if (p instanceof Passenger) {
			return "ProfilePagePassenger";
		} else return null;
		
	}
	
	//TODO: controller for edit profile

}