package ch.unibe.ese.team1.controller.service;

import java.util.Calendar;
import java.util.Date;

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
		ad.setRegion(placeAdForm.getRegion());
		
		Calendar calendar = Calendar.getInstance();
		//java.util.Calendar uses a month range of 0-11 instead of the XMLGregorianCalendar which uses 1-12
		calendar.set(placeAdForm.getYearMoveIn(), placeAdForm.getMonthMoveIn() - 1, placeAdForm.getDayMoveIn());
		ad.setMoveInDate(calendar.getTime());
		
		calendar.set(placeAdForm.getYearMoveOut(), placeAdForm.getMonthMoveOut() - 1, placeAdForm.getDayMoveOut());
		ad.setMoveOutDate(calendar.getTime());
		
		ad.setPrizePerMonth(placeAdForm.getPrize());
		ad.setSquareFootage(placeAdForm.getSquareFootage());
		
		adDao.save(ad);
	}
}

