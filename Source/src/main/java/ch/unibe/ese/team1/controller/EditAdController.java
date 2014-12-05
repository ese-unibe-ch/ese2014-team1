package ch.unibe.ese.team1.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.service.AdService;
import ch.unibe.ese.team1.model.Ad;

/**
 * This controller handles all requests concerning editing ads.
 */
@Controller
public class EditAdController {
	
	@Autowired
	private AdService adService;

	/** 
	 * Serves the page that allows the user to edit the ad with the given id.
	 */
	@RequestMapping(value ="/profile/editAd", method = RequestMethod.GET)
	public ModelAndView editAdPage(@RequestParam long id, Principal principal){
		ModelAndView model = new ModelAndView("editAd");
		Ad ad = adService.getAdById(id);
		model.addObject("ad", ad);
		
		return model;
	}
	
	/** 
	 * Processes the edit ad form and displays the result page to the user.
	 */
	@RequestMapping(value ="/profile/editAd", method = RequestMethod.POST)
	public ModelAndView editAdPageWithForm(){
		// TODO add form handling here
		return new ModelAndView("index");
	}
	
}
