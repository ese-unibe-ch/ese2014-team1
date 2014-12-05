package ch.unibe.ese.team1.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

import ch.unibe.ese.team1.controller.pojos.PictureUploader;
import ch.unibe.ese.team1.controller.pojos.forms.MessageForm;
import ch.unibe.ese.team1.controller.pojos.forms.PlaceAdForm;
import ch.unibe.ese.team1.controller.service.AdService;
import ch.unibe.ese.team1.controller.service.AlertService;
import ch.unibe.ese.team1.controller.service.BookmarkService;
import ch.unibe.ese.team1.controller.service.MessageService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.controller.service.VisitService;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.PictureMeta;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.UserDao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This controller handles all requests concerning placing ads and displaying ads.
 */
@Controller
public class AdController {

	public static final String IMAGE_DIRECTORY = "/img/ads";

	@Autowired
	private AdService adService;
	@Autowired
	private UserService userService;
	@Autowired
	private AlertService alertService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private MessageService messageService;
	@Autowired
	private VisitService visitService;
	@Autowired
	private BookmarkService bookmarkService;

	@Autowired
	private UserDao userDao;
	

	/** Used for generating a JSON representation of a given object. */
	private ObjectMapper objectMapper;

	/**
	 * Used for uploading ad pictures. As long as the user did not place the ad
	 * completely, the same picture uploader is used. Once the ad was placed,
	 * this uploader is renewed.
	 */
	private PictureUploader pictureUploader;

	/**
	 * The place ad form that is shared between several requests, so that the
	 * user only has to enter the data once. If an ad is placed, this form is
	 * reset.
	 */
	private PlaceAdForm placeAdForm;

	/** Shows the place ad form. */
	@RequestMapping(value = "/profile/placeAd", method = RequestMethod.GET)
	public ModelAndView placeAd() throws IOException {
		ModelAndView model = new ModelAndView("placeAd");

		String realPath = servletContext.getRealPath(IMAGE_DIRECTORY);
		if (pictureUploader == null) {
			pictureUploader = new PictureUploader(realPath, IMAGE_DIRECTORY);
		}
		return model;
	}

	/**
	 * Uploads the pictures that are attached as multipart files to the request.
	 * The JSON representation, that is returned, is generated manually because
	 * the jQuery Fileupload plugin requires this special format.
	 * 
	 * @return A JSON representation of the uploaded files
	 */
	@RequestMapping(value = "/profile/placeAd/uploadPictures", method = RequestMethod.POST)
	public @ResponseBody String uploadPictures(
			MultipartHttpServletRequest request) {
		List<MultipartFile> pictures = new LinkedList<>();
		Iterator<String> iter = request.getFileNames();

		while (iter.hasNext()) {
			pictures.add(request.getFile(iter.next()));
		}

		List<PictureMeta> uploadedPicturesMeta = pictureUploader
				.upload(pictures);

		objectMapper = new ObjectMapper();
		String jsonResponse = "{\"files\": ";
		try {
			jsonResponse += objectMapper
					.writeValueAsString(uploadedPicturesMeta);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		jsonResponse += "}";
		return jsonResponse;
	}

	/**
	 * Gets the descriptions for the pictures that were uploaded with the
	 * current picture uploader.
	 * 
	 * @return a list of picture descriptions or null if no pictures were
	 *         uploaded
	 */
	@RequestMapping(value = "/profile/placeAd/getUploadedPictures", method = RequestMethod.POST)
	public @ResponseBody List<PictureMeta> getUploadedPictures() {
		if (pictureUploader == null) {
			return null;
		}
		return pictureUploader.getUploadedPictureMetas();
	}

	/**
	 * Deletes the uploaded picture at the given relative url (relative to the
	 * webapp folder).
	 */
	@RequestMapping(value = "/profile/placeAd/deletePicture", method = RequestMethod.POST)
	public @ResponseBody void deleteUploadedPicture(@RequestParam String url) {
		if (pictureUploader != null) {
			String realPath = servletContext.getRealPath(url);
			pictureUploader.deletePicture(url, realPath);
		}
	}

	/**
	 * Validates the place ad form and persists the ad if successful. On
	 * success, a redirect to the ad description page of the just created ad is
	 * issued. If there were validation errors, the place ad form is displayed
	 * again.
	 */
	@RequestMapping(value = "/profile/placeAd", method = RequestMethod.POST)
	public ModelAndView create(@Valid PlaceAdForm placeAdForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Principal principal) {
		ModelAndView model = new ModelAndView("placeAd");
		if (!result.hasErrors()) {
			String username = principal.getName();
			User user = userService.findUserByUsername(username);

			List<String> fileNames = pictureUploader.getFileNames();
			Ad ad = adService.saveFrom(placeAdForm, fileNames, user);

			// triggers all alerts that match the placed ad
			alertService.triggerAlerts(ad);

			// reset the place ad form
			this.placeAdForm = null;
			// reset the picture uploader
			this.pictureUploader = null;

			model = new ModelAndView("redirect:/ad?id=" + ad.getId());
			redirectAttributes.addFlashAttribute("confirmationMessage",
					"Ad placed successfully. You can take a look at it below.");
		} else {
			model = new ModelAndView("placeAd");
		}
		return model;
	}

	/** Gets the ad description page for the ad with the given id. */
	@RequestMapping(value = "/ad", method = RequestMethod.GET)
	public ModelAndView ad(@RequestParam("id") long id, Principal principal) {
		ModelAndView model = new ModelAndView("adDescription");
		Ad ad = adService.getAdById(id);
		model.addObject("shownAd", ad);
		model.addObject("messageForm", new MessageForm());

		String loggedInUserEmail = (principal == null) ? "" : principal
				.getName();
		model.addObject("loggedInUserEmail", loggedInUserEmail);

		model.addObject("visits", visitService.getVisitsByAd(ad));

		return model;
	}

	/**
	 * Gets the ad description page for the ad with the given id and also
	 * validates and persists the message passed as post data.
	 */
	@RequestMapping(value = "/ad", method = RequestMethod.POST)
	public ModelAndView messageSent(@RequestParam("id") long id,
			@Valid MessageForm messageForm, BindingResult bindingResult) {

		ModelAndView model = new ModelAndView("adDescription");
		Ad ad = adService.getAdById(id);
		model.addObject("shownAd", ad);
		model.addObject("messageForm", new MessageForm());

		if (!bindingResult.hasErrors()) {
			messageService.saveFrom(messageForm);
		}
		return model;
	}
	
	/** 
	 * Serves the page that allows the user to edit the ad with the given id.
	 */
	@RequestMapping(value ="/profile/editAd", method = RequestMethod.GET)
	public ModelAndView editAdPage(@RequestParam long id){
		ModelAndView model = new ModelAndView("editAd");
		Ad ad = adService.getAdById(id);
		model.addObject("ad", ad);
		
		return model;
	}
	
	/** 
	 * Processes the edit ad form and displays the result page to the user.
	 */
	@RequestMapping(value ="/profile/editAd", method = RequestMethod.POST)
	public ModelAndView editAdPageWithForm(){
		// TODO add form handling here
		return new ModelAndView("index");
	}

	/**
	 * Checks if the email passed as post parameter is a valid email. In case it
	 * is valid, the email address is returned. If it is not, a error message is
	 * returned.
	 */
	@RequestMapping(value = "/profile/placeAd/validateEmail", method = RequestMethod.POST)
	@ResponseBody
	public String validateEmail(@RequestParam String email) {
		User user = userService.findUserByUsername(email);
		if (user == null) {
			return "This user does not exist, did your roommate register?";
		} else {
			return user.getEmail();
		}
	}

	@ModelAttribute("placeAdForm")
	public PlaceAdForm placeAdForm() {
		if (placeAdForm == null) {
			placeAdForm = new PlaceAdForm();
		}
		return placeAdForm;
	}

	/**
	 * Checks if the adID passed as post parameter is already inside users
	 * arrayList bookmarkedAds. In case it is present, true is returned changing
	 * the "Bookmark Me" button to "Bookmarked". If it is not present it is
	 * added to the arrayList bookmarkedAds.
	 * 
	 * @return 0 and 1 for errors; 3 to update the button to bookmarked 3 and 2
	 *         for bookmarking or undo bookmarking respectively 4 for removing
	 *         button completly (because its the users ad)
	 */
	@RequestMapping(value = "/bookmark", method = RequestMethod.POST)
	@Transactional
	@ResponseBody
	public int isBookmarked(@RequestParam("id") long id,
			@RequestParam("screening") boolean screening,
			@RequestParam("bookmarked") boolean bookmarked, Principal principal) {
		// should never happen since no bookmark button when not logged in
		if (principal == null) {
			return 0;
		}
		String username = principal.getName();
		User user = userService.findUserByUsername(username);
		if (user == null) {
			// that should not happen...
			System.out
					.println("ERROR: Principal does exist but could not be found in the DB");
			return 1;
		}
		List<Ad> bookmarkedAdsIterable = user
				.getBookmarkedAdvertisementIterable();
		if (screening) {
			for (Ad ownAdIterable : adService.getAdsByUser(user)) {
				if (ownAdIterable.getId() == id) {
					return 4;
				}
			}
			for (Ad adIterable : bookmarkedAdsIterable) {
				if (adIterable.getId() == id) {
					return 3;
				}
			}
			return 2;
		}

		Ad ad = adService.getAdById(id);

		return bookmarkService.getBookmarkStatus(ad, bookmarked, user);
	}

	/**
	 * Fetches information about bookmarked rooms and own ads and attaches this
	 * information to the myRooms page in order to be displayed.
	 */
	@RequestMapping(value = "/profile/myRooms", method = RequestMethod.GET)
	public ModelAndView myRooms(Principal principal) {
		ModelAndView model;
		User user;
		if (principal != null) {
			model = new ModelAndView("myRooms");
			String username = principal.getName();
			user = userService.findUserByUsername(username);

			Iterable<Ad> ownAds = adService.getAdsByUser(user);

			model.addObject("bookmarkedAdvertisements",
					user.getBookmarkedAdvertisementIterable());
			model.addObject("ownAdvertisements", ownAds);
			return model;
		} else {
			model = new ModelAndView("home");
		}

		return model;
	}
	
}