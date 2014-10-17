package ch.unibe.ese.team1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}