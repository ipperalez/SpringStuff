package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Account;
import com.example.service.AccountService;

@Controller
public class LoginController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
//		Account account = new Account();
//		modelAndView.addObject("account", account);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewAccount(@RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password) {
		Account account = new Account(email, username, password);
		ModelAndView modelAndView = new ModelAndView();
//		Account accountExists = accountService.findUserByEmail(account.getEmail());
		accountService.saveAccount(account);
		modelAndView.addObject("successMessage", "account has been registered successfully");
		modelAndView.addObject("account", account);
		modelAndView.setViewName("registration");
		return modelAndView;
		
	}
//	public ModelAndView createNewAccount(@Valid Account account, BindingResult bindingResult) {
//		ModelAndView modelAndView = new ModelAndView();
//		Account accountExists = accountService.findUserByEmail(account.getEmail());
//		if (accountExists != null) {
//			bindingResult
//					.rejectValue("email", "error.account",
//							"There is already a account registered with the email provided");
//		}
//		if (bindingResult.hasErrors()) {
//			modelAndView.setViewName("registration");
//		} else {
//			accountService.saveAccount(account);
//			modelAndView.addObject("successMessage", "account has been registered successfully");
//			modelAndView.addObject("account", new Account());
//			modelAndView.setViewName("registration");
//			
//		}
//		return modelAndView;
//	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = accountService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + account.getUsername() + " (" + account.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	

}
