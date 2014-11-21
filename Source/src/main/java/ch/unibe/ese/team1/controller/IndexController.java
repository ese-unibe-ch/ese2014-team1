package ch.unibe.ese.team1.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.pojos.forms.AlertForm;
import ch.unibe.ese.team1.controller.service.AlertService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.model.Alert;
import ch.unibe.ese.team1.model.User;

/** This controller provides a mapping for the home page. */
@Controller
public class IndexController {

	@Autowired
	UserService userService = new UserService();
	
	@Autowired
	AlertService alertService = new AlertService();
	
	/** Displays the home page. */
	@RequestMapping(value="/")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/profile/alerts", method = RequestMethod.GET)
	public ModelAndView alerts(Principal principal) {
		return prepareAlertPage(principal, false, new AlertForm());
	}
	
	@RequestMapping(value = "/profile/alerts", method = RequestMethod.POST)
	public ModelAndView savedAlert(Principal principal, AlertForm alertForm) {
		return prepareAlertPage(principal, true, alertForm);
	}
	
	private ModelAndView prepareAlertPage(Principal principal, boolean alreadySet, AlertForm alertForm) {
		String username = principal.getName();
		User user = userService.findUserByUsername(username);
		if(alreadySet)
			alertService.saveFrom(alertForm, user);
		ModelAndView model = new ModelAndView("alerts");
		Iterable<Alert> alerts = alertService.getAlertsByUser(user);
		model.addObject("user", user);
		model.addObject("alertForm", alertForm);
		model.addObject("alerts", alerts);
		return model;
	}
}