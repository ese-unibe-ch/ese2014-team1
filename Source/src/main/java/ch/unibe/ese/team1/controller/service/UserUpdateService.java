package ch.unibe.ese.team1.controller.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.controller.pojos.forms.EditProfileForm;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.UserPicture;
import ch.unibe.ese.team1.model.dao.UserDao;

@Service
public class UserUpdateService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	/** Handles persisting a new user to the database. */
	@Transactional
	public void updateFrom(EditProfileForm editProfileForm) {
		
		User currentUser = userService.findUserByUsername(editProfileForm.getUsername());
		
		currentUser.setFirstName(editProfileForm.getFirstName());
		currentUser.setLastName(editProfileForm.getLastName());
		currentUser.setPassword(editProfileForm.getPassword());
		currentUser.setAboutMe(editProfileForm.getAboutMe());
		// Problem with the Picture ID which is set to NULL
		// Therefore losing the current picture.
		
		// UserPicture userPicture = currentUser.getPicture();
		// userPicture.setFilePath(editProfileForm.getUserPicture()); String instead of picture....
		

		userDao.save(currentUser);
	}

	
	
}