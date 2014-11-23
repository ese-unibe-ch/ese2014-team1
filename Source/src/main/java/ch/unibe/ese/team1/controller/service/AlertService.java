package ch.unibe.ese.team1.controller.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.controller.pojos.forms.AlertForm;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.Alert;
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
		String adCity = ad.getCity();
		int adPrice = ad.getPrizePerMonth();
		Iterable<Alert> alerts = alertDao.findByCityAndPriceGreaterThan(adCity, adPrice - 1);

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
				"<a href=/ad?id=" + ad.getId() + ">" + ad.getTitle() + "</a><br><br>" +
				"Good luck and enjoy,<br>" +
				"Your FlatFindr crew";
	}
	
	//checks if an ad is conform to the criteria in an alert.
	private boolean typeMismatchWith(Ad ad, Alert alert) {
		boolean mismatch = false;
		if(!alert.getBothRoomAndStudio() && ad.getStudio() != alert.getStudio())
			mismatch = true;
		return mismatch;
	}
	
	//STUB. So far, we don't check for radius mismatches
	private boolean radiusMismatchWith(Ad ad, Alert alert) {
		return false;
	}
}