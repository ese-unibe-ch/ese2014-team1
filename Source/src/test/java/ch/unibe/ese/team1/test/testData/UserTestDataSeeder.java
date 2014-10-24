package ch.unibe.ese.team1.test.testData;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.User;
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
		User user = new User();
		user.setUsername("john@doe.com");
		user.setPassword("password");
		user.setEmail("john@doe.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setEnabled(true);
		Set<UserRole> userRoles = new HashSet<>();
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRoles.add(role);
		user.setUserRoles(userRoles);
		userDao.save(user);
	}

}
