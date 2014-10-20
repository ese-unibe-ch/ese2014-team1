package ch.unibe.ese.team1.controller.pojos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PlaceAdForm {
	private Long id;
	private String city;
	private int dayMoveIn;
	private int monthMoveIn;
	private int yearMoveIn;
	private int dayMoveOut;
	private int monthMoveOut;
	private int yearMoveOut;
	private int prize;
	private int squareFootage;
	private String region;
	private String roomDescription;
	private String preferences;
	private String roommates;
	private boolean smoker;
	private boolean animals;
	private List<MultipartFile> pictures;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getDayMoveIn() {
		return dayMoveIn;
	}

	public void setDayMoveIn(int dayMoveIn) {
		this.dayMoveIn = dayMoveIn;
	}

	public int getMonthMoveIn() {
		return monthMoveIn;
	}

	public void setMonthMoveIn(int monthMoveIn) {
		this.monthMoveIn = monthMoveIn;
	}

	public int getYearMoveIn() {
		return yearMoveIn;
	}

	public void setYearMoveIn(int yearMoveIn) {
		this.yearMoveIn = yearMoveIn;
	}

	public int getDayMoveOut() {
		return dayMoveOut;
	}

	public void setDayMoveOut(int dayMoveOut) {
		this.dayMoveOut = dayMoveOut;
	}

	public int getMonthMoveOut() {
		return monthMoveOut;
	}

	public void setMonthMoveOut(int monthMoveOut) {
		this.monthMoveOut = monthMoveOut;
	}

	public int getYearMoveOut() {
		return yearMoveOut;
	}

	public void setYearMoveOut(int yearMoveOut) {
		this.yearMoveOut = yearMoveOut;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public int getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}

	public String getRoommates() {
		return roommates;
	}

	public void setRoommates(String roommates) {
		this.roommates = roommates;
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

	public List<MultipartFile> getPictures() {
		return pictures;
	}

	public void setPictures(List<MultipartFile> pictures) {
		this.pictures = pictures;
	}

}
