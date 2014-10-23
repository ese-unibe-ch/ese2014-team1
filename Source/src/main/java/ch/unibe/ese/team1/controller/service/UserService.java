package ch.unibe.ese.team1.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.UserDao;

/**
 * Handles all database actions concerning users.
 */
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	@Transactional
	public User findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
