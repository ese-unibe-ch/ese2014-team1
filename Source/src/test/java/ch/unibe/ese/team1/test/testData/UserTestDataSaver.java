package ch.unibe.ese.team1.test.testData;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.Gender;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.UserPicture;
import ch.unibe.ese.team1.model.UserRole;
import ch.unibe.ese.team1.model.dao.UserDao;

/**
 * This inserts some user test data into the database.
 */
@Service
public class UserTestDataSaver implements TestDataSaver {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void saveTestData() throws Exception {
		// system account
		User system = createUser("System", "1234", "FlatFindr", "Admin",
				"/img/test/system.jpg", Gender.ADMIN);
		system.setAboutMe("We keep you off the streets.");
		userDao.save(system);

		// first user
		User john = createUser("jane@doe.com", "password", "Jane", "Doe",
				Gender.FEMALE);
		john.setAboutMe(getDummyText());
		userDao.save(john);

		// second user
		User tester = createUser("ese@unibe.ch", "ese", "Tester", "Muster",
				"/img/test/portrait.jpg", Gender.MALE);
		tester.setAboutMe(getDummyText());
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

	private String getDummyText() {
		return "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy "
				+ "eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam "
				+ "voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet "
				+ "clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. "
				+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
				+ "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ";
	}

}
