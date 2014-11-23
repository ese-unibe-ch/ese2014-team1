package ch.unibe.ese.team1.test.testData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.AdPicture;
import ch.unibe.ese.team1.model.Gender;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.UserPicture;
import ch.unibe.ese.team1.model.dao.AdDao;
import ch.unibe.ese.team1.model.dao.UserDao;

/** This inserts six ad elements into the database. */
@Service
public class AdTestDataSaver implements TestDataSaver {

	@Autowired
	private AdDao adDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserTestDataSaver userSeeder;

	@Override
	@Transactional
	public void saveTestData() throws Exception {
		User user = userSeeder.createUser("user@bern.com", "password",
				"Berner", "Bär", Gender.MALE);
		UserPicture picture = new UserPicture();
		picture.setFilePath("/img/test/berner_baer.png");
		picture.setUser(user);
		user.setPicture(picture);
		user.setAboutMe(getDummyText());
		userDao.save(user);
		
		String date1str = "01.11.2014";
		String date2str = "01.12.2014";
		String date3str = "01.10.2013";
		String date4str = "01.11.2012";
		String date5str = "06.06.2010";
		String date6str = "01.01.1999";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		Date date1 = formatter.parse(date1str);
		Date date2 = formatter.parse(date2str);
		Date date3 = formatter.parse(date3str);
		Date date4 = formatter.parse(date4str);
		Date date5 = formatter.parse(date5str);
		Date date6 = formatter.parse(date6str);

		Ad adBern = new Ad();
		adBern.setZipcode(3011);
		adBern.setMoveInDate(date1);
		adBern.setCreationDate(date4);
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
		adBern.setStreet("Kramgasse 22");
		adBern.setCity("Bern");
		adBern.setGarden(true);
		adBern.setBalcony(true);
		adBern.setCellar(true);
		adBern.setFurnished(true);
		adBern.setCable(true);
		adBern.setGarage(true);
		adBern.setInternet(true);
		List<AdPicture> pictures = new ArrayList<>();
		pictures.add(createPicture(adBern, "/img/test/ad1_1.jpg"));
		pictures.add(createPicture(adBern, "/img/test/ad1_2.jpg"));
		pictures.add(createPicture(adBern, "/img/test/ad1_3.jpg"));
		adBern.setPictures(pictures);
		adDao.save(adBern);

		Ad adBern2 = new Ad();
		adBern2.setZipcode(3012);
		adBern2.setMoveInDate(date2);
		adBern2.setCreationDate(date5);
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
		adBern2.setStreet("Längassstr. 40");
		adBern2.setCity("Bern");
		adBern2.setGarden(false);
		adBern2.setBalcony(false);
		adBern2.setCellar(false);
		adBern2.setFurnished(false);
		adBern2.setCable(false);
		adBern2.setGarage(false);
		adBern2.setInternet(true);
		pictures = new ArrayList<>();
		pictures.add(createPicture(adBern2, "/img/test/ad2_1.jpg"));
		pictures.add(createPicture(adBern2, "/img/test/ad2_2.jpg"));
		pictures.add(createPicture(adBern2, "/img/test/ad2_3.jpg"));
		adBern2.setPictures(pictures);
		adDao.save(adBern2);

		Ad adBasel = new Ad();
		adBasel.setZipcode(4051);
		adBasel.setMoveInDate(date3);
		adBasel.setCreationDate(date6);
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
		adBasel.setStreet("Leimenstr. 16");
		adBasel.setCity("Basel");
		adBasel.setGarden(false);
		adBasel.setBalcony(false);
		adBasel.setCellar(false);
		adBasel.setFurnished(false);
		adBasel.setCable(false);
		adBasel.setGarage(false);
		adBasel.setInternet(false);
		pictures = new ArrayList<>();
		pictures.add(createPicture(adBasel, "/img/test/ad3_1.jpg"));
		pictures.add(createPicture(adBasel, "/img/test/ad3_2.jpg"));
		pictures.add(createPicture(adBasel, "/img/test/ad3_3.jpg"));
		adBasel.setPictures(pictures);
		adDao.save(adBasel);
		
		
		Ad adOlten = new Ad();
		adOlten.setZipcode(4600);
		adOlten.setMoveInDate(date4);
		adOlten.setCreationDate(date3);
		adOlten.setPrizePerMonth(430);
		adOlten.setSquareFootage(60);
		adOlten.setStudio(false);
		adOlten.setSmokers(true);
		adOlten.setAnimals(false);
		adOlten.setRoomDescription(getDummyText());
		adOlten.setPreferences(getDummyText());
		adOlten.setRoommates("One roommate");
		adOlten.setUser(user);
		adOlten.setTitle("Roommate wanted in Olten City");
		adOlten.setStreet("Zehnderweg 5");
		adOlten.setCity("Olten");
		adOlten.setGarden(false);
		adOlten.setBalcony(true);
		adOlten.setCellar(true);
		adOlten.setFurnished(true);
		adOlten.setCable(true);
		adOlten.setGarage(false);
		adOlten.setInternet(false);
		pictures = new ArrayList<>();
		pictures.add(createPicture(adOlten, "/img/test/ad1_1.jpg"));
		pictures.add(createPicture(adOlten, "/img/test/ad1_2.jpg"));
		pictures.add(createPicture(adOlten, "/img/test/ad1_3.jpg"));
		adOlten.setPictures(pictures);
		adDao.save(adOlten);

		Ad adNeuchâtel = new Ad();
		adNeuchâtel.setZipcode(2000);
		adNeuchâtel.setMoveInDate(date5);
		adNeuchâtel.setCreationDate(date1);
		adNeuchâtel.setPrizePerMonth(410);
		adNeuchâtel.setSquareFootage(40);
		adNeuchâtel.setStudio(true);
		adNeuchâtel.setSmokers(true);
		adNeuchâtel.setAnimals(false);
		adNeuchâtel.setRoomDescription(getDummyText());
		adNeuchâtel.setPreferences(getDummyText());
		adNeuchâtel.setRoommates("None");
		adNeuchâtel.setUser(user);
		adNeuchâtel.setTitle("Studio extrèmement bon marché à Neuchâtel");
		adNeuchâtel.setStreet("Rue de l'Hôpital 11");
		adNeuchâtel.setCity("Neuchâtel");
		adNeuchâtel.setGarden(true);
		adNeuchâtel.setBalcony(false);
		adNeuchâtel.setCellar(true);
		adNeuchâtel.setFurnished(true);
		adNeuchâtel.setCable(false);
		adNeuchâtel.setGarage(false);
		adNeuchâtel.setInternet(true);
		pictures = new ArrayList<>();
		pictures.add(createPicture(adNeuchâtel, "/img/test/ad2_1.jpg"));
		pictures.add(createPicture(adNeuchâtel, "/img/test/ad2_2.jpg"));
		pictures.add(createPicture(adNeuchâtel, "/img/test/ad2_3.jpg"));
		adNeuchâtel.setPictures(pictures);
		adDao.save(adNeuchâtel);

		Ad adBiel = new Ad();
		adBiel.setZipcode(2503);
		adBiel.setMoveInDate(date6);
		adBiel.setCreationDate(date2);
		adBiel.setPrizePerMonth(480);
		adBiel.setSquareFootage(10);
		adBiel.setStudio(true);
		adBiel.setSmokers(true);
		adBiel.setAnimals(false);
		adBiel.setRoomDescription(getDummyText());
		adBiel.setPreferences(getDummyText());
		adBiel.setRoommates("None");
		adBiel.setUser(user);
		adBiel.setTitle("Direkt am Quai: hübsches Studio");
		adBiel.setStreet("Oberer Quai 12");
		adBiel.setCity("Biel/Bienne");
		adBiel.setGarden(false);
		adBiel.setBalcony(false);
		adBiel.setCellar(false);
		adBiel.setFurnished(false);
		adBiel.setCable(false);
		adBiel.setGarage(false);
		adBiel.setInternet(false);
		pictures = new ArrayList<>();
		pictures.add(createPicture(adBiel, "/img/test/ad3_1.jpg"));
		pictures.add(createPicture(adBiel, "/img/test/ad3_2.jpg"));
		pictures.add(createPicture(adBiel, "/img/test/ad3_3.jpg"));
		adBiel.setPictures(pictures);
		adDao.save(adBiel);
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