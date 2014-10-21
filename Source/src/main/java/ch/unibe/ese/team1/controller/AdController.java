package ch.unibe.ese.team1.controller;

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
import ch.unibe.ese.team1.controller.service.PlaceAdService;

/**
 * This controllers handles all requests concerning placing ads.
 */
@Controller
public class AdController {

	public static final String IMAGE_DIRECTORY = "/img/ads";

	@Autowired
	private PlaceAdService placeAdService;
	@Autowired
	private ServletContext servletContext;

	private PlaceAdForm placeAdForm;

	@RequestMapping(value = "/placeAd", method = RequestMethod.GET)
	public ModelAndView placeAd() {
		ModelAndView model = new ModelAndView("placeAd");
		return model;
	}

	@RequestMapping(value = "/placeAd", method = RequestMethod.POST)
	public ModelAndView create(@Valid PlaceAdForm placeAdForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("placeAd");
		if (!result.hasErrors()) {
			
			// Upload the pictures
			String realPath = servletContext.getRealPath(IMAGE_DIRECTORY);
			PictureUploader pictureUploader = new PictureUploader(realPath, IMAGE_DIRECTORY);
			List<String> fileNames = pictureUploader.upload(placeAdForm.getPictures());
			
			placeAdService.saveFrom(placeAdForm, fileNames);
			// reset the place ad form
			placeAdForm= null;
			model = new ModelAndView("adDescription");
		} else {
			model = new ModelAndView("placeAd");
		}
		return model;
	}
	
	@ModelAttribute("placeAdForm")
	public PlaceAdForm placeAdForm(){
		if(placeAdForm == null){
			placeAdForm = new PlaceAdForm();
		}
		return placeAdForm;
	}

	
}