package ch.unibe.ese.team1.test.testData;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Saves test data to the database. Ensures that the test data is saved in the
 * correct order (regarding dependencies).
 */
@Service
public class TestDataSeeder implements InitializingBean {

	@Autowired
	private AdTestDataSaver adDataSaver;
	
	@Autowired
	private UserTestDataSaver userDataSaver;
	
	@Autowired
	private MessageTestDataSaver messageDataSaver;
	
	@Autowired
	private VisitEnquiryTestDataSaver visitEnquiryDataSaver;
	
	@Autowired
	private VisitTestDataSaver visitDataSaver;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// save test data in the correct order
		userDataSaver.saveTestData();
		messageDataSaver.saveTestData();
		adDataSaver.saveTestData();
		visitDataSaver.saveTestData();
		visitEnquiryDataSaver.saveTestData();
	}

}