package ch.unibe.ese.team1.controller.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.controller.AdController;
import ch.unibe.ese.team1.controller.pojos.PlaceAdForm;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.AdPicture;
import ch.unibe.ese.team1.model.dao.AdDao;


@Service
public class PlaceAdService {

	
	
	@Autowired
	private AdDao adDao;


	/** Handles persisting a new ad to the database.
	 * 
	 * @param placeAdForm the form to take the data from
	 * @param firstPictureIndex the index of the first picture, determines the file name of the pictures that are persisted
	 */
	public void saveFrom(PlaceAdForm placeAdForm, int firstPictureIndex) {
		Ad ad = new Ad();
		
		ad.setCity(placeAdForm.getCity());
		ad.setRegion(placeAdForm.getRegion());
		
		Calendar calendar = Calendar.getInstance();
		//java.util.Calendar uses a month range of 0-11 instead of the XMLGregorianCalendar which uses 1-12
		calendar.set(placeAdForm.getYearMoveIn(), placeAdForm.getMonthMoveIn() - 1, placeAdForm.getDayMoveIn());
		ad.setMoveInDate(calendar.getTime());
		
		calendar.set(placeAdForm.getYearMoveOut(), placeAdForm.getMonthMoveOut() - 1, placeAdForm.getDayMoveOut());
		ad.setMoveOutDate(calendar.getTime());
		
		ad.setPrizePerMonth(placeAdForm.getPrize());
		ad.setSquareFootage(placeAdForm.getSquareFootage());
		
		ad.setRoomDescription(placeAdForm.getRoomDescription());
		ad.setPreferences(placeAdForm.getPreferences());
		ad.setRoommates(placeAdForm.getRoommates());
		
		ad.setSmoker(placeAdForm.isSmoker());
		ad.setAnimals(placeAdForm.isAnimals());
		
		/* 
		 * Save the paths to the picture files,
		 * the pictures are assumed to be uploaded at this point!
		 */
		Set<AdPicture> pictures = new HashSet<>();
		for(int i = 0; i< placeAdForm.getPictures().size(); i++){
			AdPicture picture = new AdPicture();
			picture.setFilePath(AdController.IMAGE_DIRECTORY + (firstPictureIndex + i));
			pictures.add(picture);
		}
		ad.setPictures(pictures);
		
		adDao.save(ad);
	}
	
}

