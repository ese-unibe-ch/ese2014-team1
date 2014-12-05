package ch.unibe.ese.team1.controller.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.controller.pojos.forms.PlaceAdForm;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.AdPicture;

@Service
public class EditAdService {
	
	@Autowired
	private AdService adService;

	/**
	 * Removes the picture with the given id from the list of pictures in the ad
	 * with the given id.
	 */
	public void deletePictureFromAd(long adId, long pictureId) {
		Ad ad = adService.getAdById(adId);
		Iterator<AdPicture> iter = ad.getPictures().iterator();
		while(iter.hasNext()){
			AdPicture picture = iter.next();
			
			if(picture.getId() == pictureId){
				iter.remove();
				break;
			}
		}
	}
	
	/**
	 * Fills a Form with the data of an ad.
	 */
	public PlaceAdForm fillForm(Ad ad) {
		PlaceAdForm adForm = new PlaceAdForm();

		adForm.setRoomDescription(ad.getRoomDescription());
		adForm.setPreferences(ad.getPreferences());
		adForm.setRoommates(ad.getRoommates());
		
		return adForm;
	}

}
