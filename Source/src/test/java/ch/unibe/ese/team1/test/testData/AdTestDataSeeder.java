package ch.unibe.ese.team1.test.testData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.AdPicture;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.UserPicture;
import ch.unibe.ese.team1.model.dao.AdDao;
import ch.unibe.ese.team1.model.dao.UserDao;

/** This inserts two ad elements into the database. */
@Service
public class AdTestDataSeeder implements InitializingBean {

	@Autowired
	private AdDao adDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserTestDataSeeder userSeeder;

	@Override
	@Transactional
	public void afterPropertiesSet() throws Exception {
		User user = userSeeder.createUser("user@bern.com", "password",
				"Berner", "Bär");
		UserPicture picture = new UserPicture();
		picture.setFilePath("/img/test/berner_baer.png");
		picture.setUser(user);
		user.setPicture(picture);
		userDao.save(user);
		
		String date1str = "01.11.2014";
		String date2str = "01.12.2014";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		Date date1 = formatter.parse(date1str);
		Date date2 = formatter.parse(date2str);

		Ad adBern = new Ad();
		adBern.setZipcode(3000);
		adBern.setMoveInDate(date1);
		adBern.setPrizePerMonth(400);
		adBern.setSquareFootage(50);
		adBern.setStudio(false);
		adBern.setSmokers(false);
		adBern.setAnimals(true);
		adBern.setRoomDescription(getDummyText());
		adBern.setPreferences(getDummyText());
		adBern.setRoommates("One roommate");
		adBern.setUser(user);
		adBern.setTitle("Roommate wanted in Bern");
		adBern.setStreet("Es promeniert 1");
		adBern.setCity("Bern");
		adBern.setGarden(true);
		adBern.setBalcony(true);
		adBern.setCellar(true);
		adBern.setFurnished(true);
		adBern.setCable(true);
		adBern.setGarage(true);
		adBern.setFood("Everything");
		List<AdPicture> pictures = new ArrayList<>();
		pictures.add(createPicture(adBern, "/img/test/ad1_1.jpg"));
		pictures.add(createPicture(adBern, "/img/test/ad1_2.jpg"));
		pictures.add(createPicture(adBern, "/img/test/ad1_3.jpg"));
		adBern.setPictures(pictures);
		adDao.save(adBern);

		Ad adBern2 = new Ad();
		adBern2.setZipcode(3000);
		adBern2.setMoveInDate(date2);
		adBern2.setPrizePerMonth(450);
		adBern2.setSquareFootage(60);
		adBern2.setStudio(true);
		adBern2.setSmokers(false);
		adBern2.setAnimals(true);
		adBern2.setRoomDescription(getDummyText());
		adBern2.setPreferences(getDummyText());
		adBern2.setRoommates("None");
		adBern2.setUser(user);
		adBern2.setTitle("Cheap studio in Bern!");
		adBern2.setStreet("Es promeniert 2");
		adBern2.setCity("Bern");
		adBern2.setGarden(false);
		adBern2.setBalcony(false);
		adBern2.setCellar(false);
		adBern2.setFurnished(false);
		adBern2.setCable(false);
		adBern2.setGarage(false);
		adBern2.setFood("Vegetarian");
		pictures = new ArrayList<>();
		pictures.add(createPicture(adBern2, "/img/test/ad2_1.jpg"));
		pictures.add(createPicture(adBern2, "/img/test/ad2_2.jpg"));
		pictures.add(createPicture(adBern2, "/img/test/ad2_3.jpg"));
		adBern2.setPictures(pictures);
		adDao.save(adBern2);

		Ad adBasel = new Ad();
		adBasel.setZipcode(4000);
		adBasel.setMoveInDate(date2);
		adBasel.setPrizePerMonth(480);
		adBasel.setSquareFootage(10);
		adBasel.setStudio(true);
		adBasel.setSmokers(true);
		adBasel.setAnimals(false);
		adBasel.setRoomDescription(getDummyText());
		adBasel.setPreferences(getDummyText());
		adBasel.setRoommates("None");
		adBasel.setUser(user);
		adBasel.setTitle("Nice, bright studio in the center of Basel");
		adBasel.setStreet("Bahnhof");
		adBasel.setCity("Basel");
		adBasel.setGarden(false);
		adBasel.setBalcony(false);
		adBasel.setCellar(false);
		adBasel.setFurnished(false);
		adBasel.setCable(false);
		adBasel.setGarage(false);
		adBasel.setFood("Vegan");
		pictures = new ArrayList<>();
		pictures.add(createPicture(adBasel, "/img/test/ad3_1.jpg"));
		pictures.add(createPicture(adBasel, "/img/test/ad3_2.jpg"));
		pictures.add(createPicture(adBasel, "/img/test/ad3_3.jpg"));
		adBasel.setPictures(pictures);
		adDao.save(adBasel);
	}

	private AdPicture createPicture(Ad ad, String filePath) {
		AdPicture picture = new AdPicture();
		picture.setAd(ad);
		picture.setFilePath(filePath);
		return picture;
	}

	private String getDummyText() {
		return "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy "
				+ "eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam "
				+ "voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet "
				+ "clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. "
				+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
				+ "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ";
	}

}