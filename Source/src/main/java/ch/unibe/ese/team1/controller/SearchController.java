package ch.unibe.ese.team1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.pojos.forms.SearchForm;
import ch.unibe.ese.team1.controller.service.AdService;

@Controller
public class SearchController
{
	@Autowired
	AdService adservice;
	
	//ok
	@RequestMapping(value="/searchAd", method = RequestMethod.GET)
	public ModelAndView searchAd()
	{
		ModelAndView model = new ModelAndView("searchAd");
		model.addObject("searchForm", new SearchForm());
		return model;
	}
	
	@RequestMapping(value="/results", method = RequestMethod.POST)
	public ModelAndView results(SearchForm searchForm)
	{
		ModelAndView model = new ModelAndView("results");
		model.addObject("allAds", adservice.getAllAds());
		model.addObject(searchForm);
		return model;
	}
	
	/*private ModelAndView evaluate(SearchForm searchForm)
	{
		ModelAndView model = new ModelAndView("evaluate");
		model.addObject("allAds", adservice.getAllAds());
		return model;
	}*/
}