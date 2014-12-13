package com.ecotravel.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdvertisementsController {

	// redirects driver from choose form to create trip
	@RequestMapping("CreateTrip")
	public String sendDriverToCreateTrip(HttpSession session) {
		return "CreateTrip";
	}
	
	// redirects to driver/passenger to AdvertisementsPage
	@RequestMapping("AdvertisementsPage")
	public String sendPassengerTo(HttpSession session) {
		return "AdvertisementsPage";
	}
	
	
	
	@RequestMapping(value = "AdvertismentsPage", method = RequestMethod.POST)
	public String registerNewUser(@RequestParam String fromCity,
								@RequestParam String toCity, 
								@RequestParam String date, Model model) {

		// WARNING!!! the order of arguments must match order of inputs in <form> !!!

		System.out.println("from: " + fromCity);
		System.out.println("to: " + toCity);
		System.out.println("date: " + date);
		model.addAttribute("searching_msg", "Searching...");

		return "ChooseTrip";
	}
	
}
