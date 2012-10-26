package com.goodvibes.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goodvibes.model.GoodVibeUserDetails;
import com.goodvibes.service.UserServiceImpl;

@Controller
public class WebController {

	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping("/404")
	public String error404(Model model) {
		return "errorpages/404";
	}
	
	@RequestMapping("/403")
	public String error403(Model model) {
		return "errorpages/403";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping("/account/*")
	public String userAccount(Model model, Principal principal) {
		String currentUsername = principal.getName();
		
		GoodVibeUserDetails user = userService.getUser(currentUsername);
		
		model.addAttribute("currentUser", user);
				
		return "useraccount/welcome";
	}
		
	@RequestMapping({"/index", "/"})
	public String index(Model model, Principal principal) {
		
		if(principal != null){
			String currentUsername = principal.getName();
			
			GoodVibeUserDetails user = userService.getUser(currentUsername);
			
			model.addAttribute("currentUser", user);
		}
		
		return "index";
	}
	
	
}
