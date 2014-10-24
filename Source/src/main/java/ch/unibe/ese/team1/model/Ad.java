package ch.unibe.ese.team1.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column(nullable = false)
	private String roomDescription;
	
	@Column(nullable = false)
	private String preferences;
	
	@Column(nullable = false)
	private String roommates;
	
	@Column(nullable = false)
	private boolean smoker;
	
	@Column(nullable = false)
	private boolean animals;
	
	@OneToMany(mappedBy="ad",cascade = CascadeType.ALL)
	private Set<AdPicture> pictures;
	
	@ManyToOne(optional= false)
	private User user;
	
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
	
	public String getRoomDescription() {
		return roomDescription;
	}
	
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	
	public String getPreferences() {
		return preferences;
	}
	
	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}
	
	public String getRoommates() {
		return roommates;
	}
	
	public void setRoommates(String roommates) {
		this.roommates = roommates;
	}

	public Set<AdPicture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<AdPicture> pictures) {
		this.pictures = pictures;
	}

	public Date getMoveOutDate() {
		return moveOutDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}