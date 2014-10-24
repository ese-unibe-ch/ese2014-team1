package ch.unibe.ese.team1.controller.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.controller.pojos.forms.PlaceAdForm;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.AdPicture;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.AdDao;


@Service
public class AdService {
	
	@Autowired
	private AdDao adDao;

	/** Handles persisting a new ad to the database.
	 * 
	 * @param placeAdForm the form to take the data from
	 * @param a list of the file paths the pictures are saved under
	 * @param the currently logged in user
	 */
	@Transactional
	public void saveFrom(PlaceAdForm placeAdForm, List<String> filePaths, User user) {
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
		for(String filePath : filePaths){
			AdPicture picture = new AdPicture();
			picture.setFilePath(filePath);
			picture.setAd(ad);
			pictures.add(picture);
		}
		ad.setPictures(pictures);
		
		ad.setUser(user);
		
		adDao.save(ad);
	}
	
	
	
}

