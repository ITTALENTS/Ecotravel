package com.ecotravel.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import jdbc.dao.DriverDAO;
import jdbc.dao.PassengerDAO;
import jdbc.dao.ProfileDAO;
import jdbc.model.Driver;
import jdbc.model.Passenger;
import jdbc.model.Person;
import jdbc.model.Profile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class RegistrationController {
	
	@RequestMapping(value="RegisterForm", method = RequestMethod.GET)
	public String showRegistrationPage() {
		return "RegisterForm";
	}
	
	
	
	@RequestMapping(value="RegisterForm", method = RequestMethod.POST)
	// WARNING!!! the order of arguments must match order of inputs in <form> !!!
	public String registerNewUser(@RequestParam String name,
								@RequestParam int birthYear,
								@RequestParam String telephone,
								@RequestParam String email,
								@RequestParam String username, 
								@RequestParam String password,
								@RequestParam String rePassword,
								@RequestParam String driverLicense, Model model, 
								HttpSession session, HttpServletResponse response) {
		
		System.out.println("username: " + username);
		System.out.println("pass: " + password);
		System.out.println("rePass: " + rePassword);
		System.out.println("email: " + email);
		System.out.println("name: " + name);
		System.out.println("telephone: " + telephone);
		System.out.println("birthYear: " + birthYear);
		System.out.println("driverLicense: " + driverLicense);
		
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		session.setAttribute("email", email);
		session.setAttribute("name", name);
		session.setAttribute("telephone", telephone);
		session.setAttribute("birthYear", birthYear);
		
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
		
//		// these variables are jsut for the test
//		boolean usernameExists = false;
//		boolean emailExists = false;
//		boolean isUserAllwedToRegister = !usernameExists && !emailExists;
		
		
		if(isUserAllwedToRegister) {
			if(driverLicense.equalsIgnoreCase("No")) {
				// here call register method from DAO (it writes the new user in DB)
				profileDAO.createProfile(username, email, password);
				// we register as passenger
				PassengerDAO passengerDao = (PassengerDAO) context.getBean("passengerDAO");
				passengerDao.registerPassenger(username, name, birthYear, telephone);
				
				model.addAttribute("reg_complete_msg", "Registration completed. Please, log in.");
				session.invalidate();
				return "Welcome";
			} else { // driverLicense.equalsIgnoreCase("Yes")
				// we have saved entered values in the session, so we can get them from other controller
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
											@RequestParam String musicInTheCar, Model model, HttpSession session) {
		
		boolean smokingAllowed;
		if(isSmoking.equalsIgnoreCase("Yes"))
			smokingAllowed = true;
		else
			smokingAllowed = false;
		
		System.out.println();
		System.out.println("License year: " + licensePeriodYear);
		System.out.println("Smoking: " + smokingAllowed);
		System.out.println("Music: " + musicInTheCar);
		System.out.println();
		// get model attributes from previous page and print them in console
		System.out.println("username: " + session.getAttribute("username"));
		System.out.println("password: " + session.getAttribute("password"));
		System.out.println("email: " + session.getAttribute("email"));
		System.out.println("name: " + session.getAttribute("name"));
		System.out.println("telephone: " + session.getAttribute("telephone"));
		System.out.println("birthYear: " + session.getAttribute("birthYear"));
		
		// here call register method from DAO (it writes the new user in DB)
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ProfileDAO profileDAO = (ProfileDAO) context.getBean("profileDAO");
		
		profileDAO.createProfile((String)session.getAttribute("username"), (String)session.getAttribute("email"), (String)session.getAttribute("password"));
		// we register as driver
		DriverDAO driverDAO = (DriverDAO) context.getBean("driverDAO");
		driverDAO.registerDriver((String)session.getAttribute("username"), (String)session.getAttribute("name"), 
				(int)session.getAttribute("birthYear"), (String)session.getAttribute("telephone"), licensePeriodYear,
				musicInTheCar, smokingAllowed);
		
		model.addAttribute("reg_complete_msg", "Registration completed. Please, log in.");
		session.invalidate();
		return "Welcome";
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
//	}

	
}
