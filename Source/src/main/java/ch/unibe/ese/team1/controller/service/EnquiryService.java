package ch.unibe.ese.team1.controller.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.VisitEnquiry;
import ch.unibe.ese.team1.model.dao.VisitEnquiryDao;

/** Provides access to enquiries saved in the database. */
@Service
public class EnquiryService {
	
	@Autowired
	private VisitEnquiryDao enquiryDao;
	
	/** 
	 * Returns all enquiries that were sent to the given user.
	 * @param recipient the user to search for
	 * @return an Iterable of all matching enquiries
	 */
	@Transactional
	public Iterable<VisitEnquiry> getEnquiriesByRecipient(User recipient){
		// TODO complete
		return null;
	}
}
