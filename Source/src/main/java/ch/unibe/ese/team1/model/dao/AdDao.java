package ch.unibe.ese.team1.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.team1.model.Ad;

public interface AdDao extends CrudRepository<Ad, Long> {
	
	//this will be used if only rooms or studios are searched
	public Iterable<Ad> findByTypeAndPrizePerMonthLessThan (String type, int prize);
	
	//this will be used if both rooms AND studios are searched
	public Iterable<Ad> findByPrizePerMonthLessThan (int prize);
}