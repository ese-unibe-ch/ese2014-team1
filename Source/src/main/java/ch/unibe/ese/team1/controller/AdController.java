package ch.unibe.ese.team1.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

	@RequestMapping(value = "/placeAd", method = RequestMethod.GET)
	public ModelAndView placeAd() {

		ModelAndView model = new ModelAndView("placeAd");
		model.addObject("placeAdForm", new PlaceAdForm());
		return model;
	}

	@RequestMapping(value = "/placeAd", method = RequestMethod.POST)
	public ModelAndView create(@Valid PlaceAdForm placeAdForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("placeAd");
		if (!result.hasErrors()) {
			String realPath = servletContext.getRealPath(IMAGE_DIRECTORY);
			System.out.println("real Path: " + realPath);

			// Upload the pictures
			PictureUploader pictureUploader = new PictureUploader(realPath, IMAGE_DIRECTORY);
			List<String> fileNames = pictureUploader.upload(placeAdForm.getPictures());
			
			placeAdService.saveFrom(placeAdForm, fileNames);
			model = new ModelAndView("adDescription");
		} else {
			model = new ModelAndView("placeAd");
			model.addObject("placeAdForm", placeAdForm);
		}
		return model;
	}

	
}