package com.ecotravel.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.model.Driver;
import jdbc.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	
	
	// the method attribute defines the service method to handle HTTP POST request.
	@RequestMapping(method = RequestMethod.POST)
	public String letUserLogin(HttpSession session, HttpServletRequest request) {

		String username = (String) request.getAttribute("username");
		String password = (String) request.getAttribute("password");
		
		boolean doesUserExist = false;
		// here perform a check in DB: IF THIS USER EXISTS
		// if user exists check his password
		// only if user exists and his pass is correct, then set doesUserExist = true
		
		
		// if user exists and the password is right:
		if(doesUserExist) {
			session.setAttribute("user", username);
			session.setAttribute("password", password);
			// if user is driver
			return "ChooseForm";
			// else return Page with Advertisements
		}
		
		// if user doesn't exist
		else {
			request.setAttribute("error_login_message", "Invalid username or password!");
			return "Welcome";
		}
		
	}
	
/*	
	@Autowired
	private Person person;
	
	// another implementation:
	@RequestMapping(method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, 
			HttpSession session, Model model) {
		
		// check if driver
		Person p = person.loginPerson(username, password);
		if(p == null) { // no such user
			model.addAttribute("login_error", "Error! No such user or invalid password.");
			return "Welcome";
		} else {
			session.setAttribute("loggedInUser", p);
			if(p instanceof Driver)
				return "ChooseForm";
			else
				return "ProfilePage";
		}
		
	}
*/
	
/*	@RequestMapping(method = RequestMethod.POST)
	public String logout(HttpSession session, Model model) {
		session.removeAttribute("");
		return "Welcome";
	}*/

	
}
