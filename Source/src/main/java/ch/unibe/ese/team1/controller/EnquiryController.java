package ch.unibe.ese.team1.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.service.EnquiryService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.controller.service.VisitService;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.Visit;
import ch.unibe.ese.team1.model.VisitEnquiry;
import ch.unibe.ese.team1.model.VisitEnquiryState;

/**
 * Handles all requests concerning enquiries of type
 * {@link ch.unibe.ese.team1.model.VisitEnquiry VisitEnquiry} between users.
 */
@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private VisitService visitService;

	@RequestMapping(value = "/profile/enquiries")
	public ModelAndView enquiriesPage(Principal principal) {
		ModelAndView model = new ModelAndView("enquiries");
		User user = userService.findUserByUsername(principal.getName());
		model.addObject("enquiries",
				enquiryService.getEnquiriesByRecipient(user));
		return model;
	}

	@RequestMapping(value = "/profile/enquiries/sendEnquiryForVisit")
	public @ResponseBody void sendEnquiryForVisit(@RequestParam("id") long id, Principal principal) {
		Visit visit = visitService.getVisitById(id);
		User user = userService.findUserByUsername(principal.getName());
		
		VisitEnquiry visitEnquiry = new VisitEnquiry();
		visitEnquiry.setDateSent(new Date());
		visitEnquiry.setSender(user);
		visitEnquiry.setState(VisitEnquiryState.OPEN);
		visitEnquiry.setVisit(visit);
		
		enquiryService.saveVisitEnquiry(visitEnquiry);
	}
	
}
