package com.ecotravel.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("Logout")
public class LogoutController {
	 
	@RequestMapping(method = RequestMethod.POST)
	public String logout(HttpServletResponse response, HttpSession session, Model model) {
		
		session.removeAttribute("loggedInUser");
		
//		response.setHeader("pragma", "no-cache");              
//		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
//		response.setHeader("Expires", "0");
		
		session.invalidate();
		
		return "redirect:Welcome";
	}
	
}

