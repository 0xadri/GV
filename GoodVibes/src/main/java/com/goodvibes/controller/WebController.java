package com.goodvibes.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.goodvibes.model.GoodVibeUserDetails;
import com.goodvibes.model.validation.GoodVibeUserDetailsValidator;
import com.goodvibes.service.UserServiceImpl;

@Controller
public class WebController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private GoodVibeUserDetailsValidator goodVibeUserDetailsValidator;
	
	@RequestMapping("/404")
	public String error404(Model model) {
		return "error-pages/404";
	}
	
	@RequestMapping("/403")
	public String error403(Model model) {
		return "error-pages/403";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login/login-logout";
	}
	
	
	@RequestMapping(value = "/login/create-account", method = RequestMethod.GET)
	public String createAccountForm(Model model) {

		model.addAttribute("user", new GoodVibeUserDetails());
		return "login/create-account";
	}
	
	@RequestMapping(value = "/login/create-account", method = RequestMethod.POST)
	public String createAccount(@ModelAttribute("user") GoodVibeUserDetails user, BindingResult result, Model model, SessionStatus status) {
		
		goodVibeUserDetailsValidator.validate(user, result);
		
		if(result.hasErrors()){
			model.addAttribute("user", user);

			// Print the errors to the console - FIXME: log error instead!
	        System.out.println("Validation errors:");
	        for (FieldError error : result.getFieldErrors()) {
	            System.out.println(error.getField() + " - " + error.getDefaultMessage());
	        }

			return "login/create-account";
		}
		else{
				userService.registerUser(user);				
				return "redirect:/login/create-account-success";
		}
		
	}

	@RequestMapping(value = "/login/create-account-success", method = RequestMethod.GET)
	public String createAccountSuccess(Model model, Principal principal) {
	
		return "login/create-account-success";
	}
	
	
	@RequestMapping("/account/*")
	public String userAccount(Model model, Principal principal) {
		String currentUsername = principal.getName();
		
		GoodVibeUserDetails user = userService.getUser(currentUsername);
		
		model.addAttribute("currentUser", user);
				
		return "user-account/welcome";
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
