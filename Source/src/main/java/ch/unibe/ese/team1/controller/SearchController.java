package ch.unibe.ese.team1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.pojos.forms.ResultForm;
import ch.unibe.ese.team1.controller.pojos.forms.SearchForm;

@Controller
public class SearchController
{
	@RequestMapping(value="/searchAd", method = RequestMethod.GET)
	public ModelAndView searchAd()
	{
		ModelAndView model = new ModelAndView("searchAd");
		model.addObject("searchForm", new SearchForm());
		return model;
	}
	
	@RequestMapping(value="/results", method = RequestMethod.GET)
	public ModelAndView results()
	{
		ModelAndView model = new ModelAndView("results");
		model.addObject("resultForm", new ResultForm());
		return model;
	}
}