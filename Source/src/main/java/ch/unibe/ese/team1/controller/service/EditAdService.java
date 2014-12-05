package ch.unibe.ese.team1.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.controller.pojos.forms.PlaceAdForm;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.AdPicture;
import ch.unibe.ese.team1.model.dao.AdDao;
import ch.unibe.ese.team1.model.dao.AdPictureDao;

@Service
public class EditAdService {
	
	@Autowired
	private AdService adService;
	
	@Autowired
	private AdDao adDao;
	
	@Autowired
	private AdPictureDao adPictureDao;

	/**
	 * Removes the picture with the given id from the list of pictures in the ad
	 * with the given id.
	 */
	@Transactional
	public void deletePictureFromAd(long adId, long pictureId) {
		Ad ad = adService.getAdById(adId);
		List<AdPicture> pictures = ad.getPictures();
		AdPicture picture = adPictureDao.findOne(pictureId);
		pictures.remove(picture);
		ad.setPictures(pictures);
		adDao.save(ad);
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
