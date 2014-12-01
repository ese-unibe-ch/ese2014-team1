package ch.unibe.ese.team1.controller.service;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.controller.pojos.forms.MessageForm;
import ch.unibe.ese.team1.model.Message;
import ch.unibe.ese.team1.model.MessageState;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.MessageDao;
import ch.unibe.ese.team1.model.dao.UserDao;

/** Handles all persistence operations concerning messaging. */
@Service
public class MessageService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private MessageDao messageDao;

	/** Gets all messages in the inbox of the given user. */
	@Transactional
	public Iterable<Message> getInboxForUser(User user) {
		return messageDao.findByRecipient(user);
	}

	/** Gets all messages in the sent folder for the given user. */
	@Transactional
	public Iterable<Message> getSentForUser(User user) {
		return messageDao.findBySender(user);
	}

	/** Gets the message with the given id. */
	@Transactional
	public Message getMessage(long id) {
		return messageDao.findOne(id);
	}

	/**
	 * Handles persisting a new message to the database.
	 * 
	 * @param messageForm
	 *            the form to take the data from
	 */
	@Transactional
	public Message saveFrom(MessageForm messageForm) {
		Message message = new Message();

		message.setRecipient(userDao.findByUsername(messageForm.getRecipient()));
		message.setSubject(messageForm.getSubject());
		message.setText(messageForm.getText());
		message.setState(MessageState.UNREAD);

		// get logged in user as the sender
		org.springframework.security.core.userdetails.User securityUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		User loggedInUser = userDao.findByUsername(securityUser.getUsername());

		message.setSender(loggedInUser);

		Calendar calendar = Calendar.getInstance();
		// java.util.Calendar uses a month range of 0-11 instead of the
		// XMLGregorianCalendar which uses 1-12
		calendar.setTimeInMillis(System.currentTimeMillis());
		message.setDateSent(calendar.getTime());

		messageDao.save(message);

		return message;
	}

	/** Saves a new message with the given parameters in the DB.
	 * 
	 * @param sender the user who sends the message
	 * @param recipient the user who should receive the message
	 * @param subject the subject of the message
	 * @param text the text of the message
	 */
	public void sendMessage(User sender, User recipient, String subject,
			String text) {
		Message message = new Message();
		message.setDateSent(new Date());
		message.setSender(sender);
		message.setRecipient(recipient);
		message.setSubject(subject);
		message.setText(text);
		message.setState(MessageState.UNREAD);
		
		messageDao.save(message);
	}

}
