package ch.unibe.ese.team1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.pojos.PlaceAdForm;




@Controller
public class PlaceAdController {

	@RequestMapping(value="/placeAd", method = RequestMethod.GET)
	public ModelAndView placeAd(){
		
		ModelAndView model = new ModelAndView("placeAd");
		model.addObject("placeAdForm", new PlaceAdForm());    
		return model;
	}
}