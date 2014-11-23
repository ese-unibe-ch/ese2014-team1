package ch.unibe.ese.team1.test.testData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.Visit;
import ch.unibe.ese.team1.model.dao.AdDao;
import ch.unibe.ese.team1.model.dao.UserDao;
import ch.unibe.ese.team1.model.dao.VisitDao;

@Service
public class VisitTestDataSaver implements TestDataSaver {

	private User testerMuster;
	private User bernerBaer;
	private User janeDoe;

	private Ad ad1;
	private Ad ad2;
	private Ad ad3;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AdDao adDao;
	
	@Autowired
	private VisitDao visitDao;

	@Override
	public void saveTestData() throws Exception {
		// load users
		bernerBaer = userDao.findOne(3L);
		testerMuster = userDao.findOne(2L);
		janeDoe = userDao.findOne(1L);

		// load ads
		ad1 = adDao.findOne(1L);
		ad2 = adDao.findOne(2L);
		ad3 = adDao.findOne(3L);

		Visit visit;
		List<User> searchers;
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");

		// first visit
		visit = new Visit();
		visit.setAd(ad1);
		visit.setAdvertiser(bernerBaer);
		searchers = new LinkedList<>();
		searchers.add(testerMuster);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("14:00 26.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("16:00 26.12.2014"));
		visitDao.save(visit);

		// second visit
		visit = new Visit();
		visit.setAd(ad2);
		visit.setAdvertiser(testerMuster);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("09:00 20.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 20.12.2014"));
		visitDao.save(visit);


		// third visit
		visit = new Visit();
		visit.setAd(ad2);
		visit.setAdvertiser(testerMuster);
		visit.setSearchers(new LinkedList<User>());
		visit.setStartTimestamp(dateFormat.parse("12:00 20.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("14:00 20.12.2014"));
		visitDao.save(visit);


		// fourth visit
		visit = new Visit();
		visit.setAd(ad3);
		visit.setAdvertiser(janeDoe);
		searchers = new LinkedList<>();
		searchers.add(testerMuster);
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 21.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 21.12.2014"));
		visitDao.save(visit);


		// fifth visit
		visit = new Visit();
		visit.setAd(ad3);
		visit.setAdvertiser(janeDoe);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 22.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 22.12.2014"));
		visitDao.save(visit);


		// sixth visit
		visit = new Visit();
		visit.setAd(ad3);
		visit.setAdvertiser(janeDoe);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("14:00 23.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("16:00 23.12.2014"));
		visitDao.save(visit);


	}

}
