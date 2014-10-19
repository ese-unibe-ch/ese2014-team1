package ch.unibe.ese.team1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.ese.team1.controller.pojos.PlaceAdForm;
import ch.unibe.ese.team1.controller.service.PlaceAdService;
import ch.unibe.ese.team1.controller.service.SignupService;

/**
 * Controller for PlaceAd and SearchAd? Other suggestions?
 */
@Controller
public class AdController {
	
	@Autowired
	private PlaceAdService placeAdService;

	@RequestMapping(value="/placeAd", method = RequestMethod.GET)
	public ModelAndView placeAd(){
		
		ModelAndView model = new ModelAndView("placeAd");
		model.addObject("placeAdForm", new PlaceAdForm());    
		return model;
	}
	
	@RequestMapping(value = "/AdDescription", method = RequestMethod.POST)
    public ModelAndView create(@Valid PlaceAdForm placeAdForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            try {
            	placeAdService.saveFrom(placeAdForm);
            	model = new ModelAndView("AdDescription");
            } catch (Exception e) {
            	model = new ModelAndView("AdDescription");
            	//model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("AdDescription");
        }   	
    	return model;
    }
}