package ch.unibe.ese.team1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.pojos.forms.MessageForm;
import ch.unibe.ese.team1.controller.pojos.forms.SearchForm;
import ch.unibe.ese.team1.controller.service.AdService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.model.User;

@Controller
public class SearchController {
	
	@Autowired
	private AdService adService;
	
	@Autowired
	private UserService userService;

	private SearchForm searchForm;

	@RequestMapping(value = "/searchAd", method = RequestMethod.GET)
	public ModelAndView searchAd() {
		ModelAndView model = new ModelAndView("searchAd");
		return model;
	}

	@RequestMapping(value = "/results", method = RequestMethod.POST)
	public ModelAndView results(@Valid SearchForm searchForm,
			BindingResult result) {
		
		if (!result.hasErrors()) {
			ModelAndView model = new ModelAndView("results");
			model.addObject("results", adService.queryResults(searchForm));
			return model;
		} else {
			// go back
			return searchAd();
		}
	}
	
	@RequestMapping(value = "/profile/user", method = RequestMethod.GET)
	public ModelAndView user(@RequestParam("id") long id) {
		ModelAndView model = new ModelAndView("user");

		User user = userService.findUserById(id);
		model.addObject("user", user);
		model.addObject("messageForm", new MessageForm());

		return model;
	}

	@ModelAttribute
	public SearchForm getSearchForm() {
		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		return searchForm;
	}
}