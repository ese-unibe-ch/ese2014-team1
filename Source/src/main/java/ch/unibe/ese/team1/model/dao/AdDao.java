package ch.unibe.ese.team1.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.team1.model.Ad;

public interface AdDao extends CrudRepository<Ad, Long> {
	
}
