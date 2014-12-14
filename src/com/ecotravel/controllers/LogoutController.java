package com.ecotravel.controllers;

import java.io.IOException;

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
		// session.removeAttribute("loggedInUser");
		session.invalidate();
		return "redirect:Welcome";
	}
	
}

