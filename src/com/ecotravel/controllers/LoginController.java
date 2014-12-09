package com.ecotravel.controllers;

import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value="/welcome", method = RequestMethod.POST)
	public String handleLogin(HttpSession session)
	{
//		request
//		String username = session.getAttribute("user")
		if(true) //user is in Db
		{
			
		}
		return "ChooseForm";
	}
	
}
