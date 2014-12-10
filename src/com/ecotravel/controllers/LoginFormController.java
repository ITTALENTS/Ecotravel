package com.ecotravel.controllers;

import javax.servlet.http.HttpSession;

import jdbc.model.Driver;
import jdbc.model.Person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
// when we run our PROJECT, it will send us to this controller, which will handle the request
public class LoginFormController {
	
	
	// @RequestMapping(method = RequestMethod.GET) is used to declare the showHome() 
	// method as the controller's default service method to handle HTTP GET request. 
	@RequestMapping(method = RequestMethod.GET)
	public String showHome() {
		return "Welcome";
	}
	

//	@Autowired // we need this to validate via annotations
//	private Person person;
	
	// this method handles POST requests to from Welcome page:
	@RequestMapping(method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, 
			HttpSession session, Model model) {
		
		// check his user name and password:
		Person p = loginPerson(username, password);
		
		if(p == null) { // no such user
			model.addAttribute("login_error", "Error! No such user or invalid password.");
			return "Welcome";
		} else {
			session.setAttribute("loggedInUser", p);
			if(p instanceof Driver)
				return "ChooseForm";
			else // instanceof Passenger
				return "ProfilePage";
		}
		
	}

	
	static Person loginPerson(String username, String password) {
//		return null;
//		return new jdbc.model.Passenger();
		return new jdbc.model.Driver();
	}

	
}
