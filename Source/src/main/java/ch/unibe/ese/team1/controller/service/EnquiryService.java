package ch.unibe.ese.team1.controller.service;

import java.util.LinkedList;
import java.util.List;

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
	 * 
	 * @param recipient
	 *            the user to search for
	 * @return an Iterable of all matching enquiries
	 */
	@Transactional
	public Iterable<VisitEnquiry> getEnquiriesByRecipient(User recipient) {
		List<VisitEnquiry> enquiries = new LinkedList<VisitEnquiry>();
		for (VisitEnquiry enquiry : enquiryDao.findAll()) {
			if(enquiry.getVisit().getAd().getUser().getId() == recipient.getId()){
				enquiries.add(enquiry);
			}
		}
		return enquiries;
	}
	
	@Transactional
	public void saveVisitEnquiry(VisitEnquiry visitEnquiry){
		enquiryDao.save(visitEnquiry);
	}
}
