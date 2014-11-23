package ch.unibe.ese.team1.test.testData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.Visit;
import ch.unibe.ese.team1.model.VisitEnquiry;
import ch.unibe.ese.team1.model.VisitEnquiryState;
import ch.unibe.ese.team1.model.dao.UserDao;
import ch.unibe.ese.team1.model.dao.VisitDao;
import ch.unibe.ese.team1.model.dao.VisitEnquiryDao;

@Service
public class VisitEnquiryTestDataSeeder implements InitializingBean {

	@Autowired
	private UserDao userDao;

	@Autowired
	private VisitEnquiryDao visitEnquiryDao;

	@Autowired
	private VisitDao visitDao;

	private User bernerBaer;
	private User testerMuster;

	private Visit visit1;
	private Visit visit2;
	private Visit visit3;

	@Override
	public void afterPropertiesSet() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");

		// load users
		bernerBaer = userDao.findOne(3L);
		testerMuster = userDao.findOne(2L);

		// TODO visits are not here yet at this time
		// load visits
		visit1 = visitDao.findOne(4L);
		visit2 = visitDao.findOne(5L);
		visit3 = visitDao.findOne(6L);

		// first enquiry
		VisitEnquiry enquiry = new VisitEnquiry();
		enquiry.setDateSent(dateFormat.parse("14:00 20.12.2014"));
		enquiry.setSender(testerMuster);
		enquiry.setState(VisitEnquiryState.OPEN);
		enquiry.setVisit(visit1);
		visitEnquiryDao.save(enquiry);

		// second enquiry
		enquiry = new VisitEnquiry();
		enquiry.setDateSent(dateFormat.parse("15:00 21.12.2014"));
		enquiry.setSender(testerMuster);
		enquiry.setState(VisitEnquiryState.ACCEPTED);
		enquiry.setVisit(visit2);
		visitEnquiryDao.save(enquiry);

		// third enquiry
		enquiry = new VisitEnquiry();
		enquiry.setDateSent(dateFormat.parse("16:24 21.12.2014"));
		enquiry.setSender(testerMuster);
		enquiry.setState(VisitEnquiryState.DECLINED);
		enquiry.setVisit(visit2);
		visitEnquiryDao.save(enquiry);

		// fourth enquiry
		enquiry = new VisitEnquiry();
		enquiry.setDateSent(dateFormat.parse("09:00 24.12.2014"));
		enquiry.setSender(bernerBaer);
		enquiry.setState(VisitEnquiryState.OPEN);
		enquiry.setVisit(visit3);
		visitEnquiryDao.save(enquiry);
	}

}
