package ch.unibe.ese.team1.test.testData;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.dao.UserDao;
// import ch.unibe.ese.team1.model.Ad;
// import ch.unibe.ese.team1.model.AdPicture;
import ch.unibe.ese.team1.model.Gender;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.UserPicture;
import ch.unibe.ese.team1.model.UserRole;

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
		User john = createUser("jane@doe.com", "password", "Jane", "Doe",
				Gender.FEMALE);
		userDao.save(john);

		User tester = createUser("ese@unibe.ch", "ese", "Tester",
				"Muster", "/img/user/portrait.jpg", Gender.MALE);
		userDao.save(tester);
	}

	public User createUser(String email, String password, String firstName,
			String lastName, Gender gender) {
		User user = new User();
		user.setUsername(email);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEnabled(true);
		user.setGender(gender);
		Set<UserRole> userRoles = new HashSet<>();
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRoles.add(role);
		user.setUserRoles(userRoles);
		return user;
	}

	public User createUser(String email, String password, String firstName,
			String lastName, String picPath, Gender gender) {
		User user = new User();
		user.setUsername(email);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEnabled(true);
		user.setGender(gender);
		Set<UserRole> userRoles = new HashSet<>();
		UserRole role = new UserRole();
		UserPicture picture = new UserPicture();
		picture.setUser(user);
		picture.setFilePath(picPath);
		user.setPicture(picture);
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRoles.add(role);
		user.setUserRoles(userRoles);
		return user;
	}

}
