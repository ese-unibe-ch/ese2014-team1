package ch.unibe.ese.team1.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.controller.pojos.PlaceAdForm;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.dao.AdDao;


@Service
public class PlaceAdService {

	@Autowired
	private AdDao adDao;

	/** Handles persisting a new user to the database. */
	public void saveFrom(PlaceAdForm placeAdForm) {
		Ad ad = new Ad();
		
		ad.setCity(placeAdForm.getCity());
		adDao.save(ad);
	}
}
