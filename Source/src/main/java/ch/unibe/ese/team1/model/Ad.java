package ch.unibe.ese.team1.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Ad {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String region;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date moveInDate;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date moveOutDate;
	
	@Column(nullable = false)
	private int prizePerMonth;
	
	@Column(nullable = false)
	private int squareFootage;
	
	// TODO: add further fields: text roommates...
	
	@Column(nullable = true)
	private boolean smoker;
	
	@Column(nullable = true)
	private boolean animals;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getMoveInDate() {
		return moveInDate;
	}

	public void setMoveInDate(Date moveInDate) {
		this.moveInDate = moveInDate;
	}

	public void setMoveOutDate(Date moveOutDate) {
		this.moveOutDate = moveOutDate;
	}

	public int getPrizePerMonth() {
		return prizePerMonth;
	}

	public void setPrizePerMonth(int prizePerMonth) {
		this.prizePerMonth = prizePerMonth;
	}

	public int getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}
	
	public boolean isSmoker() {
		return smoker;
	}
	
	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}
	
	public boolean isAnimals() {
		return animals;
	}
	
	public void setAnimals(boolean animals) {
		this.animals = animals;
	}
}
