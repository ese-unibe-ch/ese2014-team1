package ch.unibe.ese.team1.controller.service;

import org.springframework.stereotype.Service;

// TODO maybe move this into an already existing service?
@Service
public class CheckRoommateService {

	/**
	 * Checks if the email of a user is already contained in the given string.
	 * 
	 * @param email
	 *            the email string to search for
	 * @param alreadyAdded
	 *            the string of already added emails, which should be searched
	 *            in
	 * 
	 * @return true if the email has been added already, false otherwise
	 */
	public Boolean checkIfAlreadyAdded(String email, String alreadyAdded) {
		email = email.toLowerCase();
		alreadyAdded = alreadyAdded.replaceAll("\\s+", "").toLowerCase();
		String delimiter = "[:;]+";
		String[] toBeTested = alreadyAdded.split(delimiter);
		for (int i = 0; i < toBeTested.length; i++) {
			if (email.equals(toBeTested[i])) {
				return true;
			}
		}
		return false;
	}
}