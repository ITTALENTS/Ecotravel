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
		
		//Forces caches to obtain a new copy of the page from the origin server
		response.setHeader("Cache-Control","no-cache");
		//Directs caches not to store the page under any circumstance
		response.setHeader("Cache-Control","no-store");
		//Causes the proxy cache to see the page as "stale"
		response.setDateHeader("Expires", 0);
		//HTTP 1.0 backward compatibility
		response.setHeader("Pragma","no-cache"); 
		
		session.invalidate();
		
		return "redirect:Welcome";
	}
	
}

