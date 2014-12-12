package com.ecotravel.controllers;

import javax.servlet.http.HttpSession;

import jdbc.dao.DriverDAO;
import jdbc.dao.PassengerDAO;
import jdbc.dao.ProfileDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/") //@RequestMapping("pages/RegisterForm")
public class RegistrationController {
	
	@RequestMapping(value="RegisterForm", method = RequestMethod.GET)
	public String showRegistrationPage() {
		return "RegisterForm";
	}
	
	@RequestMapping(value="RegisterForm", method = RequestMethod.POST)
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
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ProfileDAO profileDAO = (ProfileDAO) context.getBean("profileDAO");
		
		// check password retype
		if(!password.equals(rePassword)) {
			model.addAttribute("rePassword_error_msg", "Password and Retype Password fields do not match!");
			return "RegisterForm";
		}
		
		// call DAO methods which check if user name and mail are free
		boolean usernameExists = profileDAO.usernameExist(username);
		boolean emailExists = profileDAO.emailExist(email);
		// boolean isUserAllwedToRegister = !usernameExists && !emailExists;
		boolean isUserAllwedToRegister = profileDAO.isRegistrationAllowed(email, username);
		
		if(isUserAllwedToRegister) {
			if(driverLicense.equalsIgnoreCase("No")) {
				// here call register method from DAO (it writes the new user in DB)
				profileDAO.createProfile(username, email, password);
				// we register as passenger
				PassengerDAO passengerDao = (PassengerDAO) context.getBean("passengerDAO");
				passengerDao.registerPassenger(username, name, birthYear, telephone);
				
				model.addAttribute("reg_complete_msg", "Registration completed. Please, log in.");
				return "Welcome";
			} else { // driverLicense.equalsIgnoreCase("Yes")
				model.addAttribute("username", username);
				model.addAttribute("password", password);
				model.addAttribute("rePassword", rePassword);
				model.addAttribute("email", email);
				model.addAttribute("name", name);
				model.addAttribute("telephone", telephone);
				model.addAttribute("birthYear", birthYear);
				model.addAttribute("driverLicense", driverLicense);
				// TODO: you should return the whole model with this data !!!
				
				return "RegisterFormDriver";
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
	
	
	@RequestMapping(value="RegisterFormDriver", method = RequestMethod.GET)
	public String showRegistrationPageForDriver() {
		return "RegisterFormDriver";
	}
	
	
	@RequestMapping(value="RegisterFormDriver", method = RequestMethod.POST)
	public String finishRegistrationOfDriver(@RequestParam int licensePeriodYear,
												@RequestParam String isSmoking,
												@RequestParam String musicInTheCar, Model model) {
		
		System.out.println("License year: " + licensePeriodYear);
		System.out.println("Smoking: " + isSmoking);
		System.out.println("Music: " + musicInTheCar);
		
		// get model attributes from previous page and print them in console
		
		// here call register method from DAO (it writes the new user in DB)
//		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//		ProfileDAO profileDAO = (ProfileDAO) context.getBean("profileDAO");
//		
//		profileDAO.createProfile((String)session.getAttribute("username"), (String)session.getAttribute("email"), (String)session.getAttribute("password"));
//		// we register as passenger
//		DriverDAO passengerDao = (DriverDAO) context.getBean("driverDAO");
//		passengerDao.registerDriver(username, name, birthYear, telephone);
		
		model.addAttribute("reg_complete_msg", "Registration completed. Please, log in.");
		return "Welcome";
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
