package com.ecotravel.controllers;

import javax.servlet.http.HttpSession;

import jdbc.dao.DriverDAO;
import jdbc.dao.PassengerDAO;
import jdbc.dao.ProfileDAO;
import jdbc.model.Driver;
import jdbc.model.Passenger;
import jdbc.model.Person;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
	
	
	@RequestMapping(value = "Profile", method = RequestMethod.GET)
	public String viewPersonalProfile(HttpSession session, Model model) {
		
		Person p = (Person) session.getAttribute("loggedInUser");
		
		model.addAttribute("birthYear", p.getBirthYear());
		model.addAttribute("name", p.getName());
		model.addAttribute("email", p.getProfile().getEmail());
		model.addAttribute("username", p.getProfile().getUsername());
		model.addAttribute("rating", p.getRating());
		model.addAttribute("phone", p.getTelephone());
		
		if(p instanceof Driver) {
			model.addAttribute("isSmoking", ((Driver) p).getIsSmoking() ? "Yes" : "No");
			model.addAttribute("licenseYear", ((Driver) p).getLicenseYear());
			model.addAttribute("music", ((Driver) p).getMusicInTheCar());
			model.addAttribute("numberOfTravels", ((Driver) p).getNumberOfTravels());
			return "ProfilePageDriver";
		} else if (p instanceof Passenger) {
			return "ProfilePagePassenger";
		} else return null;
		
	}
	
	
	
	
	// when user wants o edit his profile
	@RequestMapping(value="EditProfile", method = RequestMethod.GET)
	public String goToProfileEditingPAge(HttpSession session, Model model) {
		return "ProfileEdit";
	}
	
	
	
	
	// after changes in profile data, user clicks SubmitNewProfile button to make changes
	@RequestMapping(value = "SubmitNewProfilePassenger", method = RequestMethod.POST)
	public String editProfilePassenger(@RequestParam String name,
										@RequestParam int birthYear,
										@RequestParam String telephone,
										@RequestParam String email,
										@RequestParam String username,
										@RequestParam String password,
										@RequestParam String rePassword,
										HttpSession session, Model model) {
		
		Person p = (Person)session.getAttribute("loggedInUser");
		
		if(!password.equals(rePassword)) {
			model.addAttribute("edit_error_msg", "Password and Retype Password fields do not match!");
			return "ProfileEdit";
		}
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		PassengerDAO passengerDAO = (PassengerDAO) context.getBean("passengerDAO");
		
		Person p = (Person) passengerDAO.changeProfile(name, telephone, birthYear, username, password);
		
		session.removeAttribute("loggedInUser");
		session.setAttribute("loggedInUser", p);
		
		return "redirect:ProfilePagePassenger";

	}

	
	
	
	// after changes in profile data, user clicks SubmitNewProfile button to make changes
	@RequestMapping(value = "SubmitNewProfileDriver", method = RequestMethod.POST)
	public String editProfileDriver(@RequestParam String name,
									@RequestParam int birthYear,
									@RequestParam String telephone,
									@RequestParam String email,
									@RequestParam String username,
									@RequestParam String password,
									@RequestParam String rePassword,
									@RequestParam String isSmoking,
									@RequestParam String musicInTheCar,
									HttpSession session, Model model) {
		
		Person p = (Person)session.getAttribute("loggedInUser");
		
		if(p.getProfile().getPassword().equals("")) {
			
		}
		
		// TODO: IMPLEMENT THIS analogichno na gorniq code
		
		
		return "redirect:ProfilePageDriver";
	}
	
	
	
	
	// this controller redirects Driver to ChooseForm when he clicks Home button
	// redirects Passenger to AdvertisementsPage when he clicks Home button
	// redirects user to Welcome if he isn't logged in
	@RequestMapping(value="Home", method = RequestMethod.GET)
	public String goToChooseForm(HttpSession session) {
		if(session.getAttribute("loggedInUser") instanceof  Driver){
			return "ChooseForm";
		}
		else if(session.getAttribute("loggedInUser") instanceof  Passenger){
			return "AdvertisementsPage";
		}
		else{
	    	return "redirect:Welcome";
		}
	}
	
	
	
	
	// this controller is for header's link AboutUs
	@RequestMapping(value="AboutUs", method = RequestMethod.GET)
	public String openAboutUsPage() {
		
		return "AboutUs";
	}
	
	
	
	
	// this controller is for header's link History
	@RequestMapping(value="History", method = RequestMethod.GET)
	public String openHistoryPage() {
		
		return "History";
	}
	
	
	
	
	// when passenger views advertisements from ChooseTrip and wants to see the
	// profile of the driver who has posted the advertisement:
	@RequestMapping(value="ViewDriversProfile", method = RequestMethod.GET)
	public String viewAnotherDriverProfile(@RequestParam String driverUsername, 
										HttpSession session, Model model) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DriverDAO driverDAO = (DriverDAO) context.getBean("driverDAO");
		Driver driver = driverDAO.showProfile(driverUsername);
		
		model.addAttribute("name", driver.getName());
		model.addAttribute("username", driver.getProfile().getUsername());
		model.addAttribute("birthYear", driver.getBirthYear());
		model.addAttribute("email", driver.getProfile().getEmail());
		model.addAttribute("phone", driver.getTelephone());
		model.addAttribute("licenseYear", driver.getLicenseYear());
		model.addAttribute("isSmoking", driver.getIsSmoking());
		model.addAttribute("music", driver.getMusicInTheCar());
		model.addAttribute("numberOfTravels", driver.getNumberOfTravels());
		model.addAttribute("rating", driver.getRating());
		
		return "ProfileOfAnotherDriver";
	}
	
	
	
	
}
