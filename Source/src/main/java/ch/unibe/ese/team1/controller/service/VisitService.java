package ch.unibe.ese.team1.controller.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.Visit;
import ch.unibe.ese.team1.model.dao.VisitDao;

@Service
public class VisitService {
	
	@Autowired
	private VisitDao visitDao;
	
	/** 
	 * Returns all possible visits of an advertisement.
	 * @return an Iterable of all matching visits
	 */
	@Transactional
	public Iterable<Visit> getVisitsByAd(Ad ad){
		return visitDao.findByAd(ad);
	}
	
	@Transactional
	public Visit getVisitById(long id){
		return visitDao.findOne(id);
	}

}
