package ch.unibe.ese.team1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.pojos.LoginForm;
import ch.unibe.ese.team1.controller.pojos.PlaceAdForm;
import ch.unibe.ese.team1.controller.pojos.SignupForm;

@Controller
public class IndexController {

	@RequestMapping(value="/")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	// TODO: To be deleted after header and footer work
	@RequestMapping(value="/hf-test")
	public ModelAndView testerObject(){
		return new ModelAndView("hf-test");
	}
	
	// TODO: To be deleted after header and footer work
	@RequestMapping(value="/search")
	public ModelAndView privateSearchObject(){
		return new ModelAndView("search");
	}
	
	// TODO: To be deleted after header and footer work
	@RequestMapping(value="/ll")
	public ModelAndView privateLLObject(){
		return new ModelAndView("LLtest");
	}
	
	@RequestMapping(value="/placeAd", method = RequestMethod.GET)
	public ModelAndView placeAd(){
		
		ModelAndView model = new ModelAndView("placeAd");
		model.addObject("placeAdForm", new PlaceAdForm());    
		return model;
	}
}