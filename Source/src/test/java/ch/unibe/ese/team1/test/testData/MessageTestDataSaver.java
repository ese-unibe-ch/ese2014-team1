package ch.unibe.ese.team1.test.testData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.Message;
import ch.unibe.ese.team1.model.MessageState;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.MessageDao;
import ch.unibe.ese.team1.model.dao.UserDao;

@Service
public class MessageTestDataSaver implements TestDataSaver {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessageDao messageDao;
	
	private User bernerBaer;
	private User testerMuster;

	@Override
	public void saveTestData() throws Exception {
		// load users
		bernerBaer = userDao.findByUsername("user@bern.com");
		testerMuster = userDao.findByUsername("ese@unibe.ch");
		
		Message message;
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
		
		// first message
		message = new Message();
		message.setSubject("Cool ad");
		message.setText("Hello Mr. Muster\n " + getDummyText());
		message.setSender(bernerBaer);
		message.setRecipient(testerMuster);
		message.setState(MessageState.UNREAD);
		message.setDateSent(dateFormat.parse("12:02 24.02.2014"));
		
		messageDao.save(message);
		
		// second message
		message = new Message();
		message.setSubject("I agree");
		message.setText("Hello Mr. Bär\n " + getDummyText());
		message.setSender(bernerBaer);
		message.setRecipient(testerMuster);
		message.setState(MessageState.UNREAD);
		message.setDateSent(dateFormat.parse("12:30 24.02.2014"));
		
		messageDao.save(message);
		
		// third message
		message = new Message();
		message.setSubject("Check this out");
		message.setText("Hello Mr. Bär\n " + getDummyText());
		message.setSender(testerMuster);
		message.setRecipient(bernerBaer);
		message.setState(MessageState.READ);
		message.setDateSent(dateFormat.parse("11:30 24.02.2014"));
		
		messageDao.save(message);
	}
	
	private String getDummyText() {
		return "Hey \n "
				+ "eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam "
				+ "voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet "
				+ "clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. "
				+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
				+ "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ";
	}
	
}
