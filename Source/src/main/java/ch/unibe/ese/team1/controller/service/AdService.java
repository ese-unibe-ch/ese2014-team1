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
	public Ad saveFrom(PlaceAdForm placeAdForm, List<String> filePaths, User user) {
		System.out.println("called");
		Ad ad = new Ad();
		
		ad.setTitle(placeAdForm.getTitle());
		ad.setStreet(placeAdForm.getStreet());
		
		// take the zipcode - first four digits
		String zip = placeAdForm.getCity().substring(0, 4);
		ad.setZipcode(Integer.parseInt(zip));
		
		Calendar calendar = Calendar.getInstance();
		//java.util.Calendar uses a month range of 0-11 instead of the XMLGregorianCalendar which uses 1-12
		try {
			if (placeAdForm.getMoveInDate().length() >= 1) {
				int dayMoveIn = Integer.parseInt(placeAdForm.getMoveInDate().substring(0, 2));
				int monthMoveIn = Integer.parseInt(placeAdForm.getMoveInDate().substring(3, 5));
				int yearMoveIn = Integer.parseInt(placeAdForm.getMoveInDate().substring(6, 10));
				calendar.set(yearMoveIn, monthMoveIn - 1, dayMoveIn);
				ad.setMoveInDate(calendar.getTime());
			}
			
			if (placeAdForm.getMoveOutDate().length() >= 1) {
				int dayMoveOut = Integer.parseInt(placeAdForm.getMoveOutDate().substring(0, 2));
				int monthMoveOut = Integer.parseInt(placeAdForm.getMoveOutDate().substring(3, 5));
				int yearMoveOut = Integer.parseInt(placeAdForm.getMoveOutDate().substring(6, 10));
				calendar.set(yearMoveOut, monthMoveOut - 1, dayMoveOut);
				ad.setMoveOutDate(calendar.getTime());
			}
		} catch (NumberFormatException e) {
		}

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
		
		return ad;
	}
	
	
	@Transactional
    public Ad getAdById(long id) {
    	return adDao.findOne(id);
    }
}

