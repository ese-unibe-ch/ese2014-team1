package ch.unibe.ese.team1.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.controller.pojos.forms.AlertForm;
import ch.unibe.ese.team1.model.Alert;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.AlertDao;

@Service
public class AlertService {

	@Autowired
	AlertDao alertDao;
	
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
		System.out.println("AlertService");
		alertDao.save(alert);
	}
		
	@Transactional
	public Iterable<Alert> getAlertsByUser(User user) {
		return alertDao.findAlertsByUser(user);
	}
}