package ch.unibe.ese.team1.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.pojos.forms.EditProfileForm;
import ch.unibe.ese.team1.controller.pojos.forms.SignupForm;
import ch.unibe.ese.team1.controller.service.SignupService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.controller.service.UserUpdateService;
import ch.unibe.ese.team1.model.User;

/**
 * Handles all requests concerning user accounts.
 */
@Controller
public class AccountController {

	@Autowired
	private SignupService signupService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserUpdateService userUpdateService;

	/** Returns the login page. */
	@RequestMapping(value = "/login")
	public ModelAndView loginPage() {
		ModelAndView model = new ModelAndView("login");
		return model;
	}

	/** Returns the signup page. */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signupPage() {
		ModelAndView model = new ModelAndView("signup");
		model.addObject("signupForm", new SignupForm());
		return model;
	}

	/** Validates the signup form and on success persists the new user. */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signupResultPage(@Valid SignupForm signupForm,
			BindingResult bindingResult) {
		ModelAndView model;
		if (!bindingResult.hasErrors()) {
			signupService.saveFrom(signupForm);
			model = new ModelAndView("login");
			model.addObject("confirmationMessage", "Signup complete!");
		} else {
			model = new ModelAndView("signup");
			model.addObject("signupForm", signupForm);
		}
		return model;
	}

	/** Shows the edit profile page. */
	@RequestMapping(value = "/profile/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfilePage(Principal principal) {
		ModelAndView model = new ModelAndView("editProfile");
		String username = principal.getName();
		User user = userService.findUserByUsername(username);
		model.addObject("editProfileForm", new EditProfileForm());
		model.addObject("currentUser", user);
		// TODO remove test code
		model.addObject("tester", "That should be displayed");
		return model;
	}

	/** Handles the request for editing the user profile. */
	@RequestMapping(value = "/profile/editProfile", method = RequestMethod.POST)
	public ModelAndView editProfileResultPage(
			@Valid EditProfileForm editProfileForm, BindingResult bindingResult) {
		ModelAndView model;
		if (!bindingResult.hasErrors()) {
			userUpdateService.updateFrom(editProfileForm);
			model = new ModelAndView("updatedProfile");
			model.addObject("negativeMessage", "Update successful!");
			return model;
		} else {
			model = new ModelAndView("nonUpdatedProfile");
			model.addObject("negativeMessage", "Update successful!");
			return model;
		}
	}
	
	@RequestMapping(value = "/profile/publicProfile")
	public ModelAndView publicProfile(Principal principal) {
		ModelAndView model = new ModelAndView("publicProfile");
		String username = principal.getName();
		User user = userService.findUserByUsername(username);
		model.addObject("currentUser", user);
		return model;
	}
}
