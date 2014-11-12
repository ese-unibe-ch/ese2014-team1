package ch.unibe.ese.team1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** This controller provides a mapping for the home page. */
@Controller
public class IndexController {

	/** Displays the home page. */
	@RequestMapping(value="/")
	public ModelAndView index(){
		return new ModelAndView("index");
	}

}