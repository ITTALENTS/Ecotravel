package com.ecotravel.controllers;

import javax.servlet.http.HttpSession;

import jdbc.dao.ProfileDAO;
import jdbc.model.Driver;
import jdbc.model.Passenger;
import jdbc.model.Person;
import jdbc.model.Profile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value={"/", "Welcome"})
// when we run our PROJECT, it will send us to this controller, which will handle the request
public class LoginFormController {
	
	
	// @RequestMapping(method = RequestMethod.GET) is used to declare the showHome() 
	// method as the controller's default service method to handle HTTP GET request. 
	@RequestMapping(method = RequestMethod.GET)
	public String showHome() {
		return "Welcome";
	}
	
	
	// this method handles POST requests to from Welcome page:
	@RequestMapping(method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, 
			HttpSession session, Model model) {
		
		System.out.println("Logging in: " + username + " - " + password);
		
		// this method is for local test only:
		// Person p = (Person)loginPerson(username, password);
//		Person p = new Driver();
//		Profile prof = new Profile();
//		prof.setEmail("hard_trck18@abv.bg");
//		prof.setPassword("dddpass");
//		prof.setUsername("shofer4eto93");
//		p.setProfile(prof);
//		p.setBirthYear(1993);
//		p.setName("Ivan");
//		p.setRating(0);
//		p.setTelephone("0889 723 711");
		
		
		// check his user name and password:
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ProfileDAO profileDAO = (ProfileDAO) context.getBean("profileDAO");
		Person p = (Person) profileDAO.login(username, password);		
		
		
		if(p == null) { // no such user
			model.addAttribute("login_error", "Error! No such user or invalid password.");
			return "Welcome";
		} else {
			session.setAttribute("loggedInUser", p);
			 session.setMaxInactiveInterval(1*60); // 15 minutes
			if(p instanceof Driver)
				return "ChooseForm";
			else if(p instanceof Passenger)// instance of Passenger
				return "AdvertisementsPage";
			else{
				model.addAttribute("login_error", "nito driver nito passenger");
				return "Welcome";
			}
		}
		
	}

	
	static Person loginPerson(String username, String password) {
//		return null;
//		return new jdbc.model.Passenger();
		return new jdbc.model.Driver();
	}

	
	
	@RequestMapping(value="ForgottenPassword", method = RequestMethod.GET)
	public String goToForgottenPassword() {
		return "ForgottenPassword";
	}
	
	
	
	
	
}
