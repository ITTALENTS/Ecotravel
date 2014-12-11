package com.ecotravel.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/register")
public class RegistrationController {
	
	@RequestMapping(method = RequestMethod.POST)
	public String registerNewUser(HttpServletRequest request, HttpSession session) {
		
		// Spring automatically sets attributes to session object
		// System.out.println(request.getParameter("driver license"));

		// here place code to validate the input
		// if something is not valid, return "RegisterForm";
		
		// if everything is valid:
		if(request.getParameter("driver license").equals("Yes")) {
			return "RegisterFormDriver";
		}
		
		// here place code to validate and register user and update DB
		
		else return "ChooseForm";
	}
	
	
	//TODO: Da se napravi metod koito sled registraciqta da preprashta kum login stranicata
	
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
