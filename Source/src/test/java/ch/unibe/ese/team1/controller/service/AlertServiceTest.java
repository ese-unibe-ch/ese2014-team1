package ch.unibe.ese.team1.controller.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.Alert;
import ch.unibe.ese.team1.model.Gender;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.UserRole;
import ch.unibe.ese.team1.model.dao.AdDao;
import ch.unibe.ese.team1.model.dao.AlertDao;
import ch.unibe.ese.team1.model.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/main/webapp/WEB-INF/config/springData.xml",
		"file:src/main/webapp/WEB-INF/config/springSecurity.xml"})
@WebAppConfiguration
public class AlertServiceTest {
	
	@Autowired
	AdDao adDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AlertDao alertDao;
	
	@Autowired
	AlertService alertService;
	
	private Iterable<Alert> alerts;
	private List<Alert> alertList;
	
	@Test
	public void createAlerts() {
		// Create user Adolf Ogi
		User adolfOgi = createUser("adolf@ogi.ch", "password", "Adolf", "Ogi",
				Gender.MALE);
		userDao.save(adolfOgi);
		
		// Create 2 alerts for Adolf Ogi
		Alert alert = new Alert();
		alert.setUser(adolfOgi);
		alert.setBothRoomAndStudio(false);
		alert.setRoom(false);
		alert.setStudio(true);
		alert.setCity("Bern");
		alert.setZipcode(3000);
		alert.setPrice(1500);
		alert.setRadius(100);
		alertDao.save(alert);
		
		alert = new Alert();
		alert.setUser(adolfOgi);
		alert.setBothRoomAndStudio(true);
		alert.setRoom(true);
		alert.setStudio(true);
		alert.setCity("Bern");
		alert.setZipcode(3002);
		alert.setPrice(1000);
		alert.setRadius(5);
		alertDao.save(alert);
		
		//copy alerts to a list
		alerts = alertService.getAlertsByUser(adolfOgi);
		for(Alert returnedAlert: alerts)
			alertList.add(returnedAlert);
		
		//begin the actual testing
		assertEquals(2, alertList.size());
		assertEquals(adolfOgi, alertList.get(0).getUser());
		assertEquals("Bern", alertList.get(1).getCity());
		assertTrue(alertList.get(0).getRadius() > alertList.get(1).getRadius());
	}
	
	@Test
	public void mismatchChecks() {
		//prepare the List<Alert>
		alerts = alertService.getAlertsByUser(userDao.findByUsername("adolf@ogi.ch"));
		for(Alert returnedAlert: alerts)
			alertList.add(returnedAlert);
		
		//save an ad
		Date date = new Date();
		Ad oltenResidence= new Ad();
		oltenResidence.setZipcode(4600);
		oltenResidence.setMoveInDate(date);
		oltenResidence.setCreationDate(date);
		oltenResidence.setPrizePerMonth(1200);
		oltenResidence.setSquareFootage(42);
		oltenResidence.setStudio(false);
		oltenResidence.setSmokers(true);
		oltenResidence.setAnimals(false);
		oltenResidence.setRoomDescription("blah");
		oltenResidence.setPreferences("blah");
		oltenResidence.setRoommates("None");
		oltenResidence.setUser(userDao.findByUsername("adolf@ogi.ch"));
		oltenResidence.setTitle("Olten Residence");
		oltenResidence.setStreet("Florastr. 100");
		oltenResidence.setCity("Olten");
		oltenResidence.setGarden(false);
		oltenResidence.setBalcony(false);
		oltenResidence.setCellar(false);
		oltenResidence.setFurnished(false);
		oltenResidence.setCable(false);
		oltenResidence.setGarage(false);
		oltenResidence.setInternet(false);
		adDao.save(oltenResidence);
		
		assertFalse(alertService.radiusMismatch(oltenResidence, alertList.get(0)));
		assertTrue(alertService.radiusMismatch(oltenResidence, alertList.get(1)));
		assertTrue(alertService.typeMismatch(oltenResidence, alertList.get(0)));
		assertFalse(alertService.typeMismatch(oltenResidence, alertList.get(1)));
	}
	
	//Lean user constructor
	User createUser(String email, String password, String firstName,
			String lastName, Gender gender) {
		User user = new User();
		user.setUsername(email);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEnabled(true);
		user.setGender(gender);
		Set<UserRole> userRoles = new HashSet<>();
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRoles.add(role);
		user.setUserRoles(userRoles);
		return user;
	}
}