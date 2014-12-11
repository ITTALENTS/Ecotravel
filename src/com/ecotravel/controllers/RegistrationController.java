package com.ecotravel.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
	
	@RequestMapping(value="/pages/RegisterForm", method = RequestMethod.POST)
	public String registerNewUser(@RequestParam String username, 
								@RequestParam String password,
								@RequestParam String rePassword,
								@RequestParam String email,
								@RequestParam String name,
								@RequestParam String telephone,
								@RequestParam int birthYear) {
		
		System.out.println("username: " + username);
		System.out.println("pass: " + password);
		System.out.println("rePass: " + rePassword);
		System.out.println("email: " + email);
		System.out.println("name: " + name);
		System.out.println("telephone: " + telephone);
		System.out.println("birthYear: " + birthYear);
		
		
		// get all parameters with @RequestParam()
		// call DAO method which checks if user name and mail are free
		// if not, print error message
		// if yes, call another method which checks driving license parameter
			// if license = yes
				// ????
			// if license = no
				// call register method from DAO (it writes the new user in DB)
				// and then send user to login page with message "reg. completed. please log in"
		
		
		
		// Spring automatically sets attributes to session object
		// System.out.println(request.getParameter("driver license"));

		// here place code to validate the input
		// if something is not valid, return "RegisterForm";
		
		// if everything is valid:
//		if(request.getParameter("driver license").equals("Yes")) {
//			return "RegisterFormDriver";
//		}
		
		// here call method to validate and register user and update DB
		
		//else return "ChooseForm";
		
		return null;
	}
	
	
	@RequestMapping(value="/pages/RegisterFormDriver", method = RequestMethod.POST)
	public String registerDriver(@RequestParam int numberOfTravels, 
								@RequestParam boolean isSmoking,
								@RequestParam String musicInTheCar) {
		
		return null;
	}
	
	

	
//	// another implementation:
//	@RequestMapping(method = RequestMethod.POST)
//	public String registerUser(ModelMap model, /* @Valid Person person */ BindingResult result) {
//	
//		if(result.hasErrors()) {
//			
//			// do something
//			return null;
//		} else {
//			return "ChooseForm";
//		}
//		
//	}
	
}
