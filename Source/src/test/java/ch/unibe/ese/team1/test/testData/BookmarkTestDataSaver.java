package ch.unibe.ese.team1.test.testData;

import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.controller.service.AdService;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.UserDao;

/**
 * This inserts some bookmark test data into the database.
 */
@Service
public class BookmarkTestDataSaver implements TestDataSaver {

	@Autowired
	private UserDao userDao;
	@Autowired
	private AdService adService;

	@Override
	@Transactional
	public void saveTestData() throws Exception {
		User ese = userDao.findByUsername("ese@unibe.ch");
		User jane = userDao.findByUsername("jane@doe.com");
		User bernerBaer = userDao.findByUsername("user@bern.com");
		User oprah = userDao.findByUsername("oprah@winfrey.com");

		// 5 bookmarks for Ese test-user
		ArrayList<Long> bookmarkedAds = new ArrayList<>();
		bookmarkedAds.add(1L);
		bookmarkedAds.add(3L);
		bookmarkedAds.add(5L);
		bookmarkedAds.add(7L);
		bookmarkedAds.add(8L);
		ese.setBookmarkedAds(bookmarkedAds);
		LinkedList<Ad> bookmarkedAdsLinkedList = new LinkedList<>();
		bookmarkedAdsLinkedList.add(adService.getAdById(1));
		bookmarkedAdsLinkedList.add(adService.getAdById(3));
		bookmarkedAdsLinkedList.add(adService.getAdById(5));
		bookmarkedAdsLinkedList.add(adService.getAdById(7));
		bookmarkedAdsLinkedList.add(adService.getAdById(8));
		ese.setBookmarkedAdvertisementIterable(bookmarkedAdsLinkedList);
		
		userDao.save(ese);

		// 4 bookmarks for Jane Doe
		bookmarkedAds = new ArrayList<>();
		bookmarkedAds.add(6L);
		bookmarkedAds.add(9L);
		bookmarkedAds.add(10L);
		bookmarkedAds.add(11L);
		jane.setBookmarkedAds(bookmarkedAds);
		userDao.save(jane);

		// 5 bookmarks for user berner bear
		bookmarkedAds = new ArrayList<>();
		bookmarkedAds.add(2L);
		bookmarkedAds.add(4L);
		bookmarkedAds.add(6L);
		bookmarkedAds.add(8L);
		bookmarkedAds.add(12L);
		bernerBaer.setBookmarkedAds(bookmarkedAds);
		userDao.save(bernerBaer);

		// 4 bookmarks for Oprah
		bookmarkedAds = new ArrayList<>();
		bookmarkedAds.add(2L);
		bookmarkedAds.add(3L);
		bookmarkedAds.add(6L);
		bookmarkedAds.add(12L);
		oprah.setBookmarkedAds(bookmarkedAds);
		userDao.save(oprah);
	}

}
