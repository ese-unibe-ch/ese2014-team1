package ch.unibe.ese.team1.controller.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




//import ch.unibe.ese.team1.controller.pojos.forms.ASearchForm;
import ch.unibe.ese.team1.controller.pojos.forms.PlaceAdForm;
import ch.unibe.ese.team1.controller.pojos.forms.SearchForm;
//import ch.unibe.ese.team1.controller.pojos.forms.SearchForm;
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

		ad.setStreet(placeAdForm.getStreet());

		ad.setStudio(placeAdForm.getStudio());
		
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

		// ad description values
		ad.setSmokers(placeAdForm.isSmokers());
		ad.setAnimals(placeAdForm.isAnimals());
		ad.setGarden(placeAdForm.getGarden());
		ad.setBalcony(placeAdForm.getBalcony());
		ad.setCellar(placeAdForm.getCellar());
		ad.setFurnished(placeAdForm.isFurnished());
		ad.setCable(placeAdForm.getCable());
		ad.setGarage(placeAdForm.getGarage());

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

		//we use this method if we are looking for rooms AND studios
		if (searchForm.getBothRoomAndStudio()) {
			results = adDao
					.findByPrizePerMonthLessThan(searchForm.getPrize() + 1);
 		}
		// we use this method if we are looking EITHER for rooms OR for studios
		else {
			results = adDao.findByStudioAndPrizePerMonthLessThan(
					searchForm.getStudio(), searchForm.getPrize() + 1);
		}
		
		//filter out zipcode
		String city = searchForm.getCity().substring(7);

		// get the location that the user searched for and take the one with the
		// lowest zip code
		Location searchedLocation = geoDataService.getLocationsByCity(city)
				.get(0);

		// create a list of the results and of their locations
		List<Ad> locatedResults = new ArrayList<>();

		// results with correct place now called locatedResults to avoid
		// confusion
		// vis-à-vis filteredResults
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
		
		//filter for additional criteria
		if(searchForm.getFiltered())
		{
			//prepare date filtering - by far the most difficult filter
			Date earliestInDate = null;	
			Date latestInDate = null;
			Date earliestOutDate = null;
			Date latestOutDate = null;
							
			//parse move-in and move-out dates
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			try {
		        earliestInDate = formatter.parse(searchForm.getEarliestMoveInDate());
			}
			catch (Exception e) {}
			try {
				latestInDate = formatter.parse(searchForm.getLatestMoveInDate());
			}
			catch (Exception e) {}
			try {
				earliestOutDate = formatter.parse(searchForm.getEarliestMoveOutDate());
			}
			catch (Exception e) {}
			try {
				latestOutDate = formatter.parse(searchForm.getLatestMoveOutDate());
			}
			catch (Exception e) {}
						
			//filtering by dates
			locatedResults = validateDate(locatedResults, true, earliestInDate, latestInDate);
			locatedResults = validateDate(locatedResults, false, earliestOutDate, latestOutDate);
						
			//filtering for the rest
			//smokers
			if(searchForm.getSmokers()) {
				Iterator<Ad> iterator = locatedResults.iterator();
				while(iterator.hasNext()) {
					Ad ad = iterator.next();
					if(!ad.getSmokers())
						iterator.remove();
				}
			}
			
			//animals
			if(searchForm.getAnimals()) {
				Iterator<Ad> iterator = locatedResults.iterator();
				while(iterator.hasNext()) {
					Ad ad = iterator.next();
					if(!ad.getAnimals())
						iterator.remove();
				}
			}
			
			//garden
			if(searchForm.getGarden()) {
				Iterator<Ad> iterator = locatedResults.iterator();
				while(iterator.hasNext()) {
					Ad ad = iterator.next();
					if(!ad.getGarden())
						iterator.remove();
				}
			}
			
			//balcony
			if(searchForm.getBalcony()) {
				Iterator<Ad> iterator = locatedResults.iterator();
				while(iterator.hasNext()) {
					Ad ad = iterator.next();
					if(!ad.getBalcony())
						iterator.remove();
				}
			}
			
			//cellar
			if(searchForm.getCellar()) {
				Iterator<Ad> iterator = locatedResults.iterator();
				while(iterator.hasNext()) {
					Ad ad = iterator.next();
					if(!ad.getCellar())
						iterator.remove();
				}
			}
			
			//furnished
			if(searchForm.getFurnished()) {
				Iterator<Ad> iterator = locatedResults.iterator();
				while(iterator.hasNext()) {
					Ad ad = iterator.next();
					if(!ad.getFurnished())
						iterator.remove();
				}
			}
			
			//cable
			if(searchForm.getCable()) {
				Iterator<Ad> iterator = locatedResults.iterator();
				while(iterator.hasNext()) {
					Ad ad = iterator.next();
					if(!ad.getCable())
						iterator.remove();
				}
			}
			
			//garage
			if(searchForm.getGarage()) {
				Iterator<Ad> iterator = locatedResults.iterator();
				while(iterator.hasNext()) {
					Ad ad = iterator.next();
					if(!ad.getGarage())
						iterator.remove();
				}
			}
		}
		return locatedResults;
	}
		
	private List<Ad> validateDate(List<Ad> ads, boolean inOrOut, Date earliestDate, Date latestDate) {			
		if(ads.size() > 0)
		{
			//Move-in dates
			//Both an earliest AND a latest date to compare to
			if(earliestDate != null) {
				if(latestDate != null) {
					Iterator<Ad> iterator = ads.iterator();
					while(iterator.hasNext()) {
						Ad ad = iterator.next();
						if(ad.getDate(inOrOut).compareTo(earliestDate) < 0 ||
								ad.getDate(inOrOut).compareTo(latestDate) > 0) {
							iterator.remove();
						}
					}
				}
				//only an earliest date
				else {
					Iterator<Ad> iterator = ads.iterator();
					while(iterator.hasNext()) {
						Ad ad = iterator.next();
						if(ad.getDate(inOrOut).compareTo(earliestDate) < 0)
							iterator.remove();
						}
				}
			}	
			//only a latest date
			else if(latestDate != null && earliestDate == null) {
				Iterator<Ad> iterator = ads.iterator();
				while(iterator.hasNext()) {
					Ad ad = iterator.next();
					if(ad.getDate(inOrOut).compareTo(latestDate) > 0)
						iterator.remove();
				}
			}
			else {
			}
		}
		return ads;
	}
}
