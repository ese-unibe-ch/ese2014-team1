package ch.unibe.ese.team1.test.testData;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.AdPicture;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.UserPicture;
import ch.unibe.ese.team1.model.UserRole;
import ch.unibe.ese.team1.model.dao.UserDao;

/**
 * This inserts some user test data into the database.
 */
@Service
public class UserTestDataSeeder implements InitializingBean {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public void afterPropertiesSet() throws Exception {
		createUser("john@doe.com", "password", "John", "Doe");
	}
	
	public User createUser(String email, String password, String firstName, String lastName){
		User user = new User();
		user.setUsername(email);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEnabled(true);
		Set<UserRole> userRoles = new HashSet<>();
		UserRole role = new UserRole();
		Set<UserPicture> pictures = new HashSet<>();
		pictures.add(createPicture(user, "/img/user/userJohn.jpg"));
		user.setPictures(pictures);
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRoles.add(role);
		user.setUserRoles(userRoles);
		userDao.save(user);
		return user;
	}
	
	private UserPicture createPicture(User user, String filePath) {
		UserPicture picture = new UserPicture();
		picture.setUser(user);
		picture.setFilePath(filePath);
		return picture;
	}

}
