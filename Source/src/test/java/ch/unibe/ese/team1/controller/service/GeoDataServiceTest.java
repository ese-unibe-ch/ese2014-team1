package ch.unibe.ese.team1.controller.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ch.unibe.ese.team1.model.Location;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/main/webapp/WEB-INF/config/springData.xml",
		"file:src/main/webapp/WEB-INF/config/springSecurity.xml"})
@WebAppConfiguration
public class GeoDataServiceTest {

	@Autowired
	private GeoDataService geoDataService;

	@Test
	public void getAllLocations() {
		List<Location> locations = geoDataService.getAllLocations();
		assertEquals(5000, locations.get(0).getZip());
		assertEquals(3418, locations.size());
	}
}
