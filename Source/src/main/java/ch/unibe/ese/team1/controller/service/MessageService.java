package ch.unibe.ese.team1.controller.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.Message;
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

}
