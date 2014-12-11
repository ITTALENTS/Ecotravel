package com.ecotravel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("pages/RegisterForm")
public class RegistrationController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showRegistrationPage() {
		return "RegisterForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String registerNewUser(@RequestParam String name,
								@RequestParam int birthYear,
								@RequestParam String telephone,
								@RequestParam String email,
								@RequestParam String username, 
								@RequestParam String password,
								@RequestParam String rePassword,
								@RequestParam String driverLicense, Model model) {
		
		// WARNING!!! the order of arguments must match order of inputs in <form> !!!
		
		System.out.println("username: " + username);
		System.out.println("pass: " + password);
		System.out.println("rePass: " + rePassword);
		System.out.println("email: " + email);
		System.out.println("name: " + name);
		System.out.println("telephone: " + telephone);
		System.out.println("birthYear: " + birthYear);
		System.out.println("driverLicense: " + driverLicense);
		
		
		// call DAO methods which check if user name and mail are free
		boolean usernameExists = true;
		boolean emailExists = false;
		boolean isUserAllwedToRegister = !usernameExists && !emailExists;
		
		// check password retype
		if(!password.equals(rePassword)) {
			model.addAttribute("rePassword_error_msg", "Password and Retype Password fields do not match!");
			return "RegisterForm";
		}
		
		if(isUserAllwedToRegister) {
			if(driverLicense.equalsIgnoreCase("No")) {
				// here call register method from DAO (it writes the new user in DB)
				model.addAttribute("reg_complete_msg", "Registration completed. Please, log in.");
				return "Welcome";
			} else { // driverLicense.equalsIgnoreCase("Yes")
				// ?????????
				
				
				return "Welcome";
			}
		} else { // username or email - taken
			if(emailExists) { // if mail taken
				model.addAttribute("email_taken_msg", "This email is already registered!");
				return "RegisterForm";
			} else if(usernameExists){ // if username taken
				model.addAttribute("username_taken_msg", "This username already taken!");
				return "RegisterForm";
			} 
			else return null;
		}

	}
	

//	@RequestMapping(value="/pages/RegisterFormDriver", method = RequestMethod.POST)
//	public String registerDriver(@RequestParam int numberOfTravels, 
//								@RequestParam boolean isSmoking,
//								@RequestParam String musicInTheCar) {
//		
//		// TODO: implement
//		
//		return null;
//	}
	
	
	
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
