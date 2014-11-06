package ch.unibe.ese.team1.controller.service;

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

@Service
public class MessageService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private MessageDao messageDao;

	@Transactional
	public Iterable<Message> getInboxForUser(User user) {
		return messageDao.findByRecipient(user);
	}
	
	@Transactional
	public Iterable<Message> getSentForUser(User user) {
		return messageDao.findBySender(user);
	}

	@Transactional
	public Message getMessage(long id) {
		return messageDao.findOne(id);
	}
	
	@Transactional
	public Message saveFrom(MessageForm messageForm) {
		Message message = new Message();
		
		message.setRecipient(userDao.findByUsername(messageForm.getRecipient()));
		message.setSubject(messageForm.getSubject());
		message.setText(messageForm.getText());
		message.setState(MessageState.READ);
	
		// get logged in user as the sender
		org.springframework.security.core.userdetails.User securityUser = (org.springframework.security.core.userdetails.User)
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User loggedInUser = userDao.findByUsername(securityUser.getUsername());
		
		message.setSender(loggedInUser);
		
		messageDao.save(message);

		return message;
	}

}