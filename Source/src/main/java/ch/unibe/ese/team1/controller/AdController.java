package ch.unibe.ese.team1.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import ch.unibe.ese.team1.controller.pojos.PictureUploader;
import ch.unibe.ese.team1.controller.pojos.forms.PlaceAdForm;
import ch.unibe.ese.team1.controller.service.AdService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.PictureMeta;
import ch.unibe.ese.team1.model.User;

/**
 * This controllers handles all requests concerning placing ads.
 */
@Controller
public class AdController {

	public static final String IMAGE_DIRECTORY = "/img/ads";

	@Autowired
	private AdService adService;
	@Autowired
	private UserService userService;
	@Autowired
	private ServletContext servletContext;

	private PictureUploader pictureUploader;

	private PlaceAdForm placeAdForm;

	@RequestMapping(value = "/profile/placeAd", method = RequestMethod.GET)
	public ModelAndView placeAd() throws IOException {
		ModelAndView model = new ModelAndView("placeAd");

		String realPath = servletContext.getRealPath(IMAGE_DIRECTORY);
		pictureUploader = new PictureUploader(realPath, IMAGE_DIRECTORY);
		return model;
	}

	@RequestMapping(value = "/profile/placeAd/uploadPictures", method = RequestMethod.POST)
	public @ResponseBody List<PictureMeta> uploadPictures(MultipartHttpServletRequest request) {
		List<MultipartFile> pictures = new LinkedList<>();
		Iterator<String> iter = request.getFileNames();
		
		while(iter.hasNext()){
			pictures.add(request.getFile(iter.next()));
		}
		
		return pictureUploader.upload(pictures);
	}

	@RequestMapping(value = "/profile/placeAd", method = RequestMethod.POST)
	public ModelAndView create(@Valid PlaceAdForm placeAdForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Principal principal) {
		ModelAndView model = new ModelAndView("placeAd");
		if (!result.hasErrors()) {
			String username = principal.getName();
			User user = userService.findUserByUsername(username);

			// temp
			//List<String> fileNames = pictureUploader.getFileNames();
			List<String> fileNames = new LinkedList<>();
			Ad ad = adService.saveFrom(placeAdForm, fileNames, user);

			// reset the place ad form
			this.placeAdForm = null;

			model = new ModelAndView("redirect:/ad?id=" + ad.getId());
		} else {
			model = new ModelAndView("placeAd");
		}
		return model;
	}

	// @RequestMapping(value = "/profile/ad", method = RequestMethod.GET)
	// public ModelAndView ad(@RequestParam("adId") long id) {
	// ModelAndView model = new ModelAndView("adDescription");
	//
	// Ad ad = adService.getAdById(id);
	// model.addObject("shownAd", ad);
	//
	// return model;
	// }

	@RequestMapping(value = "/ad", method = RequestMethod.GET)
	public ModelAndView ad(@RequestParam("id") long id) {
		ModelAndView model = new ModelAndView("adDescription");

		Ad ad = adService.getAdById(id);
		model.addObject("shownAd", ad);

		return model;
	}
	
	@RequestMapping(value="/profile/placeAd/validateEmail", method= RequestMethod.POST)
	public @ResponseBody String validateEmail(@RequestParam String email){
		
		User user = userService.findUserByUsername(email);

		System.out.println(user.getFirstName());
		return user.getEmail();
	}
	

	@ModelAttribute("placeAdForm")
	public PlaceAdForm placeAdForm() {
		if (placeAdForm == null) {
			placeAdForm = new PlaceAdForm();
		}
		return placeAdForm;
	}
}