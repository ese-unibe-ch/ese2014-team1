package ch.unibe.ese.team1.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.VisitEnquiry;

public interface EnquiryDao extends CrudRepository<VisitEnquiry, Long> {
	public Iterable<VisitEnquiry> findByRecipient(User recipient);
}
