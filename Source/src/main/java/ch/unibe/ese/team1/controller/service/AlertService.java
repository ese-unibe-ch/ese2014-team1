package ch.unibe.ese.team1.controller.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.controller.pojos.forms.AlertForm;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.Alert;
import ch.unibe.ese.team1.model.Location;
import ch.unibe.ese.team1.model.Message;
import ch.unibe.ese.team1.model.MessageState;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.AlertDao;
import ch.unibe.ese.team1.model.dao.MessageDao;
import ch.unibe.ese.team1.model.dao.UserDao;

@Service
public class AlertService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	AlertDao alertDao;
	
	@Autowired
	MessageDao messageDao;
	
	@Autowired
	private GeoDataService geoDataService;

	
	@Transactional
	public void saveFrom(AlertForm alertForm, User user) {
		Alert alert = new Alert();
		
		String zip = alertForm.getCity().substring(0, 4);
		alert.setZipcode(Integer.parseInt(zip));
		alert.setCity(alertForm.getCity().substring(7));
		
		alert.setPrice(alertForm.getPrice());
		alert.setRadius(alertForm.getRadius());
		alert.setRoom(alertForm.getRoom());
		alert.setStudio(alertForm.getStudio());
		alert.setBothRoomAndStudio(alertForm.getBothRoomAndStudio());
		alert.setUser(user);
		alertDao.save(alert);
	}
		
	@Transactional
	public Iterable<Alert> getAlertsByUser(User user) {
		return alertDao.findByUser(user);
	}
	
	@Transactional
	public void triggerAlerts(Ad ad) {
		int adPrice = ad.getPrizePerMonth();
		Iterable<Alert> alerts = alertDao.findByPriceGreaterThan(adPrice - 1);

		//loop through all ads with matching city and price range, throw out mismatches
		Iterator<Alert> alertIterator = alerts.iterator();
		while(alertIterator.hasNext()) {
			Alert alert = alertIterator.next();
			if(typeMismatchWith(ad, alert) || radiusMismatchWith(ad, alert) || ad.getUser().equals(alert.getUser()))
				alertIterator.remove();
		}
		
		//send only one message per user, no matter how many alerts were triggered
		List<User> users = new ArrayList<User>();
		for(Alert alert: alerts) {
			User user = alert.getUser();
			if(!users.contains(user)) {
				users.add(user);
			}
		}
		
		//send messages to all users with matching alerts
		for(User user: users) {
			Date now = new Date();
			Message message = new Message();
			message.setSubject("It's a match!");
			message.setText(getAlertText(ad));
			message.setSender(userDao.findByUsername("System"));
			message.setRecipient(user);
			message.setState(MessageState.UNREAD);
			message.setDateSent(now);
			messageDao.save(message);	
		}
	}
	
	//returns an alert message
	private String getAlertText(Ad ad) {
		return "Dear user,<br>good news. A new ad matching one of your alerts has been " +
				"entered into our system. You can visit it here:<br><br>" +
				"<u><a href=/ad?id=" + ad.getId() + ">" + ad.getTitle() + "</a></u><br><br>" +
				"Good luck and enjoy,<br>" +
				"Your FlatFindr crew";
	}
	
	//checks if an ad is conforming to the criteria in an alert.
	private boolean typeMismatchWith(Ad ad, Alert alert) {
		boolean mismatch = false;
		if(!alert.getBothRoomAndStudio() && ad.getStudio() != alert.getStudio())
			mismatch = true;
		return mismatch;
	}
	
	//Checks whether an ad is for a place too far away from the alert
	//true = too far, false = close enough
	private boolean radiusMismatchWith(Ad ad, Alert alert) {
		final int earthRadiusKm = 6380;
		Location adLocation = geoDataService.getLocationsByCity(ad.getCity()).get(0);
		Location alertLocation = geoDataService.getLocationsByCity(alert.getCity()).get(0);
		
		double radSinLat = Math.sin(Math.toRadians(adLocation.getLatitude()));
		double radCosLat = Math.cos(Math.toRadians(adLocation.getLatitude()));
		double radLong = Math.toRadians(adLocation.getLongitude());
		double radLongitude = Math.toRadians(alertLocation.getLongitude());
		double radLatitude = Math.toRadians(alertLocation.getLatitude());
		double distance = Math.acos(radSinLat
				* Math.sin(radLatitude) + radCosLat
				* Math.cos(radLatitude)
				* Math.cos(radLong - radLongitude))
				* earthRadiusKm;
		return (distance > alert.getRadius());
	}
}