package ch.unibe.ese.team1.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.ese.team1.controller.pojos.forms.PlaceAdForm;
import ch.unibe.ese.team1.controller.service.AdService;
import ch.unibe.ese.team1.controller.service.AlertService;
import ch.unibe.ese.team1.controller.service.EditAdService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.User;

/**
 * This controller handles all requests concerning editing ads.
 */
@Controller
public class EditAdController {

	@Autowired
	private AdService adService;
	
	@Autowired
	private EditAdService editAdService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AlertService alertService;

	/**
	 * Serves the page that allows the user to edit the ad with the given id.
	 */
	@RequestMapping(value = "/profile/editAd", method = RequestMethod.GET)
	public ModelAndView editAdPage(@RequestParam long id, Principal principal) {
		ModelAndView model = new ModelAndView("editAd");
		Ad ad = adService.getAdById(id);
		model.addObject("ad", ad);
		
		PlaceAdForm form = editAdService.fillForm(ad);
		
		model.addObject("placeAdForm", form);
		
		return model;
	}

	/**
	 * Processes the edit ad form and displays the result page to the user.
	 */
	@RequestMapping(value = "/profile/editAd", method = RequestMethod.POST)
	public ModelAndView editAdPageWithForm(@Valid PlaceAdForm placeAdForm,
			BindingResult result, Principal principal) {
		ModelAndView model = new ModelAndView("placeAd");
		if (!result.hasErrors()) {
			String username = principal.getName();
			User user = userService.findUserByUsername(username);

			//List<String> fileNames = pictureUploader.getFileNames();
			Ad ad = adService.saveFrom(placeAdForm, null, user);
			
			// triggers all alerts that match the placed ad
			alertService.triggerAlerts(ad);


			model = new ModelAndView("redirect:/ad?id=" + ad.getId());
		}
		
		return model;
	}
	
	/**
	 * Deletes the ad picture with the given id from the list of pictures from
	 * the ad, but not from the server.
	 */
	@RequestMapping(value = "/profile/editAd/deletePictureFromAd", method = RequestMethod.POST)
	public @ResponseBody void deletePictureFromAd(@RequestParam long adId, @RequestParam long pictureId) {
		editAdService.deletePictureFromAd(adId, pictureId);
	}
	
}
