package com.ecotravel.controllers;

import javax.servlet.http.HttpSession;

import jdbc.dao.ProfileDAO;
import jdbc.model.Driver;
import jdbc.model.Person;

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
	

//	@Autowired // we need this to validate via annotations
//	private Person person;
	
	// this method handles POST requests to from Welcome page:
	@RequestMapping(method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, 
			HttpSession session, Model model) {
		
		 System.out.println("Logging in: " + username + " : " + password);
		
		 // check his user name and password:
//		 Person p = (Person)loginPerson(username, password);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ProfileDAO profileDAO = (ProfileDAO) context.getBean("profileDAO");
		Person p = (Person) profileDAO.login(username, password);		
		
		
		if(p == null) { // no such user
			model.addAttribute("login_error", "Error! No such user or invalid password.");
			return "Welcome";
		} else {
			session.setAttribute("loggedInUser", p);
			if(p instanceof Driver)
				return "ChooseForm";
			else // instance of Passenger
				return "AdvertisementsPage";
		}
		
	}

	
	static Person loginPerson(String username, String password) {
		return null;
//		return new jdbc.model.Passenger();
//		return new jdbc.model.Driver();
	}

	
	
	@RequestMapping(value="ForgottenPassword", method = RequestMethod.GET)
	public String goToForgottenPassword() {
		return "ForgottenPassword";
	}
	
}
