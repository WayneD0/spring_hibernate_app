package com.tariq.practice.security.login.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tariq.practice.personalDetails.personalInfo.bean.Concept_Understanding_CURD_Bean;

@RequestMapping(value="/security/login")
@Controller
public class LoginController {
	
	/*@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView executeSecurity(ModelMap model, Principal principal ) {
		System.out.println("inside index..478!!");
		String name = principal.getName();
		model.addAttribute("author", name);
		model.addAttribute("message", "Welcome To Login Form Based Spring Security Example!!!");
		System.out.println("inside index 1111 42638910>....!!");
		return new ModelAndView("/security/login/index", model);
	}*/
	
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String executeSecurity(ModelMap model, Principal principal ) {
		System.out.println("inside index..478!!");
		String name = principal.getName();
		model.addAttribute("author", name);
		model.addAttribute("message", "Welcome To Login Form Based Spring Security Example!!!");
		System.out.println("inside index 1111 42638910>....!!");
		return "/security/login/index";
 
	}
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		System.out.println("inside login..(first method to take place)!!");
		return "login";
 
	}
 
	@RequestMapping(value="/fail2login", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		System.out.println("inside fail2login..!!");
		model.addAttribute("error", "true");
		System.out.println("inside fail2login 1...>>>>>..!!");
		return "login";
		
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		System.out.println("inside login..(logout)!!");
		return "/security/login/login";
 
	}
}
