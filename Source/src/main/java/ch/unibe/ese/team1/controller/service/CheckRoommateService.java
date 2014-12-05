package ch.unibe.ese.team1.controller.service;

import org.springframework.stereotype.Service;

/** Checks if the user has already been added into to form, if it has been
 * added already false is returned. True otherwise.
 */
@Service
public class CheckRoommateService {
	
	public Boolean checkIfAlreadyAdded(String email, String alreadyAdded) {
		email = email.toLowerCase();
		alreadyAdded = alreadyAdded.replaceAll("\\s+", "").toLowerCase();
		String delimiter = "[:;]+";
		String[] toBeTested = alreadyAdded.split(delimiter);
		for(int i = 0; i < toBeTested.length; i++) {
			if(email.equals(toBeTested[i])) {
				return true;
			}
		}
		return false;
	}
}