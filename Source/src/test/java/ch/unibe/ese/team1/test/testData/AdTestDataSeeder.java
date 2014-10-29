package ch.unibe.ese.team1.test.testData;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	Date date = new Date();

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

		Ad adBern = new Ad();
		adBern.setZipcode(3005);
		adBern.setMoveInDate(date);
		adBern.setPrizePerMonth(500);
		adBern.setSquareFootage(50);
		adBern.setSmoker(false);
		adBern.setAnimals(true);
		adBern.setRoomDescription(getDummyText());
		adBern.setPreferences(getDummyText());
		adBern.setRoommates("One roommate");
		adBern.setUser(user);
		adBern.setTitle("Roommate wanted in Bern");
		adBern.setStreet("Es promeniert 1");
		adBern.setCity("Bern");
		adBern.setType("Room");
		Set<AdPicture> pictures = new HashSet<>();
		pictures.add(createPicture(adBern, "/img/test/ad1_1.jpg"));
		pictures.add(createPicture(adBern, "/img/test/ad1_2.jpg"));
		pictures.add(createPicture(adBern, "/img/test/ad1_3.jpg"));
		adBern.setPictures(pictures);
		adDao.save(adBern);

		Ad adBern2 = new Ad();
		adBern2.setZipcode(3006);
		adBern2.setMoveInDate(date);
		adBern2.setPrizePerMonth(600);
		adBern2.setSquareFootage(60);
		adBern2.setSmoker(false);
		adBern2.setAnimals(true);
		adBern2.setRoomDescription(getDummyText());
		adBern2.setPreferences(getDummyText());
		adBern2.setRoommates("None");
		adBern2.setUser(user);
		adBern2.setTitle("Cheap studio in Bern!");
		adBern2.setStreet("Es promeniert 2");
		adBern2.setCity("Bern");
		adBern2.setType("Studio");
		pictures = new HashSet<>();
		pictures.add(createPicture(adBern2, "/img/test/ad2_1.jpg"));
		pictures.add(createPicture(adBern2, "/img/test/ad2_2.jpg"));
		pictures.add(createPicture(adBern2, "/img/test/ad2_3.jpg"));
		adBern2.setPictures(pictures);
		adDao.save(adBern2);

		Ad adBasel = new Ad();
		adBasel.setZipcode(5001);
		adBasel.setMoveInDate(date);
		adBasel.setPrizePerMonth(1000);
		adBasel.setSquareFootage(10);
		adBasel.setSmoker(true);
		adBasel.setAnimals(false);
		adBasel.setRoomDescription(getDummyText());
		adBasel.setPreferences(getDummyText());
		adBasel.setRoommates("None");
		adBasel.setUser(user);
		adBasel.setTitle("Nice, bright studio in the center of Zürich");
		adBasel.setStreet("Bahnhof");
		adBasel.setCity("Basel");
		adBasel.setType("Studio");
		pictures = new HashSet<>();
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