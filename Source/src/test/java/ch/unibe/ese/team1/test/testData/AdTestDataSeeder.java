package ch.unibe.ese.team1.test.testData;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.UserRole;
import ch.unibe.ese.team1.model.dao.AdDao;
import ch.unibe.ese.team1.model.dao.UserDao;

//This inserts two ad elements into the database. 
@Service
public class AdTestDataSeeder implements InitializingBean
{
	Date date = new Date();
	
	@Autowired
	private AdDao adDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public void afterPropertiesSet() throws Exception 
	{
		//first, create a user
		User user = new User();
		user.setUsername("user@bern.com");
		user.setPassword("password");
		user.setEmail("user@bern.com");
		user.setFirstName("Berner");
		user.setLastName("Bär");
		user.setEnabled(true);
		Set<UserRole> userRoles = new HashSet<>();
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRoles.add(role);
		user.setUserRoles(userRoles);
		userDao.save(user);
		
		Ad adBern = new Ad();
		adBern.setZipcode(3005);
		adBern.setMoveInDate(date);
		adBern.setPrizePerMonth(500);
		adBern.setSquareFootage(50);
		adBern.setSmoker(false);
		adBern.setAnimals(true);
		adBern.setRoomDescription("blahblah");
		adBern.setPreferences("blah");
		adBern.setRoommates("blah");
		adBern.setUser(user);
		adBern.setTitle("adBern");
		adBern.setStreet("Es promeniert 1");
		adBern.setCity("Bern");
		adBern.setRoom();
		adDao.save(adBern);
		
		Ad adBern2 = new Ad();
		adBern2.setZipcode(3006);
		adBern2.setMoveInDate(date);
		adBern2.setPrizePerMonth(600);
		adBern2.setSquareFootage(60);
		adBern2.setSmoker(false);
		adBern2.setAnimals(true);
		adBern2.setRoomDescription("blahblah");
		adBern2.setPreferences("blah");
		adBern2.setRoommates("blah");
		adBern2.setUser(user);
		adBern2.setTitle("adBern2");
		adBern2.setStreet("Es promeniert 2");
		adBern2.setCity("Bern");
		adBern2.setStudio();
		adDao.save(adBern2);
		
		Ad adZuri = new Ad();
		adZuri.setZipcode(5001);
		adZuri.setMoveInDate(date);
		adZuri.setPrizePerMonth(1000);
		adZuri.setSquareFootage(10);
		adZuri.setSmoker(true);
		adZuri.setAnimals(false);
		adZuri.setRoomDescription("blahblah");
		adZuri.setPreferences("blah");
		adZuri.setRoommates("blah");
		adZuri.setUser(user);
		adZuri.setTitle("adZuri");
		adZuri.setStreet("Hönggeberg");
		adZuri.setCity("Zuri");
		adZuri.setStudio();
		adDao.save(adZuri);
	}
}