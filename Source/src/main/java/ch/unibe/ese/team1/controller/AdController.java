package ch.unibe.ese.team1.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.ese.team1.controller.pojos.PlaceAdForm;
import ch.unibe.ese.team1.controller.service.PlaceAdService;

/**
 * This controllers handles all requests concerning placing ads.
 */
@Controller
public class AdController {

	public static final String IMAGE_DIRECTORY = "/img/ads/";

	@Autowired
	private PlaceAdService placeAdService;

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
			File imgDirectory = new File(IMAGE_DIRECTORY);
			
			// create the directory if it does not exist yet
			if (!imgDirectory.exists()) {
				imgDirectory.mkdirs();
			}
			
			int fileIndex = findHighestIndexedPicture(imgDirectory);
			for (MultipartFile file : placeAdForm.getPictures()) {
				int currentIndex = fileIndex;
				if (!file.isEmpty()) {
					try {
						byte[] bytes = file.getBytes();
						BufferedOutputStream outStream = new BufferedOutputStream(
								new FileOutputStream(new File(IMAGE_DIRECTORY
										+ currentIndex)));
						outStream.write(bytes);
						outStream.close();
						currentIndex++;
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}

			placeAdService.saveFrom(placeAdForm, fileIndex);
			model = new ModelAndView("adDescription");
		} else {
			model = new ModelAndView("placeAd");
			model.addObject("placeAdForm", placeAdForm);
		}
		return model;
	}

	/**
	 * Finds the picture with the highest index in the directory given by
	 * directory. This method assumes that the pictures are stored as with
	 * one-digit names in ascending order.
	 * 
	 * @param directory
	 *            the directory to search through
	 * @return the index of the highest-indexed picture
	 */
	private int findHighestIndexedPicture(File directory) {
		String[] files = directory.list();

		int max = 1;
		for (int i = 0; i < files.length; i++) {
			try {
				int index = Integer.parseInt(files[i]);
				if (max < index) {
					max = index;
				}
			} catch (NumberFormatException ex) {
				// ignore, we don't care about these files anyway
			}
		}

		return max;
	}
}