package ch.unibe.ese.team1.controller.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.controller.pojos.forms.PlaceAdForm;
import ch.unibe.ese.team1.controller.pojos.forms.SearchForm;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.AdPicture;
import ch.unibe.ese.team1.model.Location;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.AdDao;

@Service
public class AdService {

	@Autowired
	private AdDao adDao;

	@Autowired
	private GeoDataService geoDataService;

	/**
	 * Handles persisting a new ad to the database.
	 * 
	 * @param placeAdForm
	 *            the form to take the data from
	 * @param a
	 *            list of the file paths the pictures are saved under
	 * @param the
	 *            currently logged in user
	 */
	@Transactional
	public Ad saveFrom(PlaceAdForm placeAdForm, List<String> filePaths,
			User user) {
		Ad ad = new Ad();

		ad.setTitle(placeAdForm.getTitle());
//		ad.setType(placeAdForm.getType());	---we do it with booleans now anyways

		ad.setStreet(placeAdForm.getStreet());

		// take the zipcode - first four digits
		String zip = placeAdForm.getCity().substring(0, 4);
		ad.setZipcode(Integer.parseInt(zip));
		ad.setCity(placeAdForm.getCity().substring(7));

		Calendar calendar = Calendar.getInstance();
		// java.util.Calendar uses a month range of 0-11 instead of the
		// XMLGregorianCalendar which uses 1-12
		try {
			if (placeAdForm.getMoveInDate().length() >= 1) {
				int dayMoveIn = Integer.parseInt(placeAdForm.getMoveInDate()
						.substring(0, 2));
				int monthMoveIn = Integer.parseInt(placeAdForm.getMoveInDate()
						.substring(3, 5));
				int yearMoveIn = Integer.parseInt(placeAdForm.getMoveInDate()
						.substring(6, 10));
				calendar.set(yearMoveIn, monthMoveIn - 1, dayMoveIn);
				ad.setMoveInDate(calendar.getTime());
			}

			if (placeAdForm.getMoveOutDate().length() >= 1) {
				int dayMoveOut = Integer.parseInt(placeAdForm.getMoveOutDate()
						.substring(0, 2));
				int monthMoveOut = Integer.parseInt(placeAdForm
						.getMoveOutDate().substring(3, 5));
				int yearMoveOut = Integer.parseInt(placeAdForm.getMoveOutDate()
						.substring(6, 10));
				calendar.set(yearMoveOut, monthMoveOut - 1, dayMoveOut);
				ad.setMoveOutDate(calendar.getTime());
			}
		} catch (NumberFormatException e) {
		}

		ad.setPrizePerMonth(placeAdForm.getPrize());
		ad.setSquareFootage(placeAdForm.getSquareFootage());

		ad.setRoomDescription(placeAdForm.getRoomDescription());
		ad.setPreferences(placeAdForm.getPreferences());
		ad.setRoommates(placeAdForm.getRoommates());

		//ad description values
		ad.setSmokers(placeAdForm.isSmokers());
		ad.setAnimals(placeAdForm.isAnimals());
		ad.setGarden(placeAdForm.getGarden());
		ad.setBalcony(placeAdForm.getBalcony());
		ad.setCellar(placeAdForm.getCellar());
		ad.setFurnished(placeAdForm.isFurnished());
		ad.setCable(placeAdForm.getCable());
		ad.setGarage(placeAdForm.getGarage());
		ad.setFood(placeAdForm.getFood());
		
//		ad.setType(placeAdForm.getType());

		/*
		 * Save the paths to the picture files, the pictures are assumed to be
		 * uploaded at this point!
		 */
		List<AdPicture> pictures = new ArrayList<>();
		for (String filePath : filePaths) {
			AdPicture picture = new AdPicture();
			picture.setFilePath(filePath);
			picture.setAd(ad);
			pictures.add(picture);
		}
		ad.setPictures(pictures);

		ad.setUser(user);

		adDao.save(ad);

		return ad;
	}

	/**
	 * Gets the ad that has the given id.
	 * 
	 * @param id
	 *            the id that should be searched for
	 * @return the found ad or null, if no ad with this id exists
	 */
	@Transactional
	public Ad getAdById(long id) {
		return adDao.findOne(id);
	}

	/** Returns all ads in the database */
	@Transactional
	public Iterable<Ad> getAllAds() {
		return adDao.findAll();
	}

	/**
	 * Returns all ads that match the parameters given by the form. This list
	 * can possibly be empty.
	 * 
	 * @param searchForm
	 *            the form to take the search parameters from
	 * @return an Iterable of all search results
	 */
	@Transactional
	public Iterable<Ad> queryResults(SearchForm searchForm) {
		Iterable<Ad> results = null;

		results = adDao.findAll();
		// we use this method if we are looking for rooms AND studios
//		if (searchForm.getType().equals("both")) {
//			results = adDao
//					.findByPrizePerMonthLessThan(searchForm.getPrize() + 1);
//		}
		// we use this method if we are looking EITHER for rooms OR for studios
//		else {
//			results = adDao.findByTypeAndPrizePerMonthLessThan(
//					searchForm.getType(), searchForm.getPrize() + 1);
//		}

		// get the location that the user searched for and take the one with the
		// lowest zip code
		Location searchedLocation = geoDataService.getLocationsByCity(
				searchForm.getCity()).get(0);

		// create a list of the results and of their locations
		List<Ad> locatedResults = new ArrayList<>();

		//results with correct place now called locatedResults to avoid confusion
		//vis-à-vis filteredResults
		for (Ad ad : results) {
			locatedResults.add(ad); 
		}

		final int earthRadiusKm = 6380;
		List<Location> locations = geoDataService.getAllLocations();
		double radSinLat = Math.sin(Math.toRadians(searchedLocation
				.getLatitude()));
		double radCosLat = Math.cos(Math.toRadians(searchedLocation
				.getLatitude()));
		double radLong = Math.toRadians(searchedLocation.getLongitude());

		/*
		 * calculate the distances (Java 8) and collect all matching zipcodes.
		 * The distance is calculated using the law of cosines.
		 * http://www.movable-type.co.uk/scripts/latlong.html
		 */
		List<Integer> zipcodes = locations
				.parallelStream()
				.filter(location -> {
					double radLongitude = Math.toRadians(location
							.getLongitude());
					double radLatitude = Math.toRadians(location.getLatitude());
					double distance = Math.acos(radSinLat
							* Math.sin(radLatitude) + radCosLat
							* Math.cos(radLatitude)
							* Math.cos(radLong - radLongitude))
							* earthRadiusKm;
					return distance < searchForm.getRadius();
				}).map(location -> location.getZip())
				.collect(Collectors.toList());

		locatedResults = locatedResults.stream()
				.filter(ad -> zipcodes.contains(ad.getZipcode()))
				.collect(Collectors.toList());
		return locatedResults;
	}
}
