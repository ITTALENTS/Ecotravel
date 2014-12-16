package com.ecotravel.controllers;




import jdbc.dao.ProfileDAO;
import jdbc.model.Person;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import utils.MailSender;

@Controller
@RequestMapping("ForgottenPassword")
public class ForgottenPasswordController {
	
	@RequestMapping(method = RequestMethod.POST)
	public String sendNewPassword(@RequestParam("username") String username, Model model) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		//MailSender emailer = (MailSender) context.getBean("mailSender");
		MailSender emailer = new MailSender();
		try{
			if(!emailer.sendMessage(username)){
				model.addAttribute("generatingPasswordStatus", "Invalid username!");
				return null;
			}
			model.addAttribute("generatingPasswordStatus", "Check your email");
		}
		catch(RuntimeException e){
			model.addAttribute("generatingPasswordStatus", "Something went wrong! Please try again");
		}
		return "ForgottenPassword";
	}
}
