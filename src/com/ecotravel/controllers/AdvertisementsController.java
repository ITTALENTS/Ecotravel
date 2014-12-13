package com.ecotravel.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	
//	@RequestMapping(value="AdvertismentsPage", method = RequestMethod.POST)
//	public String registerNewUser(@RequestParam String townFrom,
//								@RequestParam String townTo,
//								@RequestParam String bday,
//								 Model model) {
//		
//		// WARNING!!! the order of arguments must match order of inputs in <form> !!!
//		
//		System.out.println("from: " +townFrom );
//		System.out.println("to: " + townTo);
//		System.out.println("date: " + bday);
//		model.addAttribute("reg_complete_msg", "getting atributes is done");
//		
//		return "AdvertismentsPage";
//		}
	
}
