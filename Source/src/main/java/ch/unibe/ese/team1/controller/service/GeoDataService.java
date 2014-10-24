package ch.unibe.ese.team1.controller.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.Zipcode;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * Provides read access to the geo db. Performs the reading operations manually,
 * no ORM is involved.
 */
@Service
public class GeoDataService {

	private final static String SQL_GET_ALL_ZIPCODES = "SELECT zip.zip , zip.location, zip.lat, zip.lon FROM `zipcodes` zip";

	@Autowired
	private BoneCPDataSource mainDataSource;

	/** Returns a list of all zipcodes in the database. */
	public List<Zipcode> getAllZipcodes() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Zipcode> zipCodeList = new ArrayList<>();
		try {
			connection = mainDataSource.getConnection();
			statement = connection.createStatement();

			resultSet = statement.executeQuery(SQL_GET_ALL_ZIPCODES);
			zipCodeList = writeResultSet(resultSet, zipCodeList);

		} catch (SQLException ex) {
			System.out.println("Could not read zipcodes from database.");
			ex.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException ex) {
				// ignore
			}
		}

		return zipCodeList;
	}

	/**
	 * Fills the given list with the results from resultSet.
	 */
	private List<Zipcode> writeResultSet(ResultSet resultSet,
			List<Zipcode> zipCodeList) throws SQLException {
		while (resultSet.next()) {
			int zip = resultSet.getInt("zip");
			String city = resultSet.getString("location");
			double latitude = resultSet.getDouble("lat");
			double longitude = resultSet.getDouble("lon");

			Zipcode zipcode = new Zipcode();
			zipcode.setZip(zip);
			zipcode.setCity(city);
			zipcode.setLatitude(latitude);
			zipcode.setLongitude(longitude);

			zipCodeList.add(zipcode);
		}

		return zipCodeList;
	}
}
