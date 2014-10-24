package ch.unibe.ese.team1.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.ese.team1.controller.pojos.PictureUploader;
import ch.unibe.ese.team1.controller.pojos.forms.PlaceAdForm;
import ch.unibe.ese.team1.controller.service.AdService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.model.User;

/**
 * This controllers handles all requests concerning placing ads.
 */
@Controller
public class AdController {

	public static final String IMAGE_DIRECTORY = "/img/ads";

	@Autowired
	private AdService placeAdService;
	@Autowired
	private UserService userService;
	@Autowired
	private ServletContext servletContext;

	private PlaceAdForm placeAdForm;

	@RequestMapping(value = "/profile/placeAd", method = RequestMethod.GET)
	public ModelAndView placeAd() {
		ModelAndView model = new ModelAndView("placeAd");
		return model;
	}

	@RequestMapping(value = "/profile/placeAd", method = RequestMethod.POST)
	public ModelAndView create(@Valid PlaceAdForm placeAdForm,
			BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
		ModelAndView model = new ModelAndView("placeAd");
		if (!result.hasErrors()) {
			String username = principal.getName();
			User user = userService.findUserByUsername(username);
			
			// Upload the pictures
			String realPath = servletContext.getRealPath(IMAGE_DIRECTORY);
			PictureUploader pictureUploader = new PictureUploader(realPath, IMAGE_DIRECTORY);
			List<String> fileNames = pictureUploader.upload(placeAdForm.getPictures());
			
			placeAdService.saveFrom(placeAdForm, fileNames, user);
			// reset the place ad form
			this.placeAdForm = null;
			model = new ModelAndView("adDescription");
			//
		} else {
			model = new ModelAndView("placeAd");
		}
		return model;
	}
	
//	@RequestMapping(value = "/profile/ad", method = RequestMethod.GET)
//	public ModelAndView ad() {
//		ModelAndView model = new ModelAndView("adDescription");
//		return model;
//	}
	
	@ModelAttribute("placeAdForm")
	public PlaceAdForm placeAdForm(){
		if(placeAdForm == null){
			placeAdForm = new PlaceAdForm();
		}
		return placeAdForm;
	}

	
}