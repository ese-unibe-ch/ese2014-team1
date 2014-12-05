package ch.unibe.ese.team1.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.service.AdService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.controller.service.VisitService;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.Visit;

/** This controller provides a mapping for the home page. */
@Controller
public class IndexController {
	
	@Autowired
	private AdService adService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VisitService visitService;
	
	/** Displays the home page. */
	@RequestMapping(value="/")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	/** Displays the about us page. */
	@RequestMapping(value="/about")
	public ModelAndView about(){
		return new ModelAndView("about");
	}
	
	/** Displays the disclaimer page. */
	@RequestMapping(value="/disclaimer")
	public ModelAndView disclaimer(){
		return new ModelAndView("disclaimer");
	}
	
	@RequestMapping(value = "/profile/schedule", method = RequestMethod.GET)
	public ModelAndView schedule(@RequestParam("user") long id) {
		ModelAndView model = new ModelAndView("schedule");
		User user = userService.findUserById(id);
		
		//visits, i.e. when the user sees someone else's property
		Iterable<Visit> visits = visitService.getVisitsForUser(user);
		model.addObject("visits", visits);
		
		//presentations, i.e. when the user presents a property
		Iterable<Ad> usersAds = adService.getAdsByUser(user);
		ArrayList<Visit> usersPresentations = new ArrayList<Visit>();
		
		for(Ad ad: usersAds){
			try{
				usersPresentations.addAll((ArrayList<Visit>)visitService.getVisitsByAd(ad));
			}
			catch(Exception e) {}
		}

		model.addObject("presentations", usersPresentations);
		return model;
	}
	
	@RequestMapping(value= "/profile/visitors", method = RequestMethod.GET)
	public ModelAndView visitors(@RequestParam("visit") long id) {
		ModelAndView model = new ModelAndView("visitors");
		Visit visit = visitService.getVisitById(id);
		Iterable<User> visitors = visit.getSearchers();
		
		//due to some strange bug every visitor appeared 3 times. So we hack it.
		ArrayList<User> distinctVisitors = new ArrayList<User>();
		for(User visitor: visitors) {
			if(!distinctVisitors.contains(visitor))
				distinctVisitors.add(visitor);
		}
		model.addObject("visitors", distinctVisitors);
		
		Ad ad = visit.getAd();
		model.addObject("ad", ad);
		return model;
	}	
}