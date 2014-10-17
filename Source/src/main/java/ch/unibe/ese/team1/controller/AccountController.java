package ch.unibe.ese.team1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.pojos.LoginForm;
import ch.unibe.ese.team1.controller.pojos.SignupForm;

/**
 * Handles all requests concerning user accounts.
 */
@Controller
public class AccountController {
	
	@RequestMapping(value="/login")
	public ModelAndView loginPage(){
		ModelAndView model = new ModelAndView("login");
		model.addObject("loginForm", new LoginForm());
		return model;
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public ModelAndView signupPage(){
		ModelAndView model = new ModelAndView("signup");
		model.addObject("signupForm", new SignupForm());
		return model;
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ModelAndView signupResultPage(){
		ModelAndView model = new ModelAndView("signup");
		model.addObject("message", "Signup complete!");
		return model;
	}

}
