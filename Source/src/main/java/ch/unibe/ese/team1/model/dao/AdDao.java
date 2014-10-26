package ch.unibe.ese.team1.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.team1.model.Ad;

public interface AdDao extends CrudRepository<Ad, Long> {
	
	//this will be used if only rooms or studios are searched
	public Iterable<Ad> findByTypeAndCityAndPrizePerMonthLessThan (String type, String city, int prize);
	
	//this will be used if both rooms AND studios are searched
	public Iterable<Ad> findByCityAndPrizePerMonthLessThan (String city, int prize);
}