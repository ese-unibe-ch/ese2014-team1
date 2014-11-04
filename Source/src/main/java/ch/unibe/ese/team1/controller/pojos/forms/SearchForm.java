package ch.unibe.ese.team1.controller.pojos.forms;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class SearchForm {
	
	private boolean filtered;
	
	//studio: true, room: false
	private boolean studio;

	@NotBlank(message = "Required")
	@Pattern(regexp = "^[0-9]{4} - [-\\w\\s]*", message = "Please pick a city from the list")
	private String city;

	@Min(value = 0, message = "Please enter a positive distance")
	private int radius;

	@Min(value = 0, message = "Don't think you will find something for that kind of money")
	private int prize;
	
	@AssertFalse(message = "Please select either or both types")
	private boolean noRoomNoStudio;
	
	private boolean bothRoomAndStudio;

	///////////////////////
	//Getters and setters//
	///////////////////////
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

	public boolean getStudio() {
		return studio;
	}
	
	public void setStudio(boolean studio) {
		this.studio = studio;
	}

	public boolean getNoRoomNoStudio() {
		return noRoomNoStudio;
	}

	public void setNoRoomNoStudio(boolean noRoomNoStudio) {
		this.noRoomNoStudio = noRoomNoStudio;
	}

	public boolean getBothRoomAndStudio() {
		return bothRoomAndStudio;
	}

	public void setBothRoomAndStudio(boolean bothRoomAndStudio) {
		this.bothRoomAndStudio = bothRoomAndStudio;
	}
	
	////////////////////
	//Filtered results//
	////////////////////
	
	public boolean getFiltered() {
		return filtered;
	}
	
	public void setFiltered(boolean filtered) {
		this.filtered = filtered;
	}
	
	private Date earliestMoveInDate;
	private Date latestMoveInDate;
	private Date earliestMoveOutDate;
	private Date latestMoveOutDate;
	
	private boolean smokers;
	private boolean animals;
	private boolean garden;
	private boolean balcony;
	private boolean cellar;
	private boolean furnished;
	private boolean cable;
	private boolean garage;
	private String food;
	
	public boolean getSmokers() {
		return smokers;
	}

	public void setSmokers(boolean smokers) {
		this.smokers = smokers;
	}

	public boolean getAnimals() {
		return animals;
	}

	public void setAnimals(boolean animals) {
		this.animals = animals;
	}

	public boolean getGarden() {
		return garden;
	}

	public void setGarden(boolean garden) {
		this.garden = garden;
	}

	public boolean getBalcony() {
		return balcony;
	}

	public void setBalcony(boolean balcony) {
		this.balcony = balcony;
	}

	public boolean getCellar() {
		return cellar;
	}

	public void setCellar(boolean cellar) {
		this.cellar = cellar;
	}

	public boolean getFurnished() {
		return furnished;
	}

	public void setFurnished(boolean furnished) {
		this.furnished = furnished;
	}

	public boolean getCable() {
		return cable;
	}

	public void setCable(boolean cable) {
		this.cable = cable;
	}

	public boolean getGarage() {
		return garage;
	}

	public void setGarage(boolean garage) {
		this.garage = garage;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		assert(food.equals("Everything") || food.equals("Vegetarian") || food.equals("Vegan"));
		this.food = food;
	}

	public Date getEarliestMoveInDate() {
		return earliestMoveInDate;
	}

	public void setEarliestMoveInDate(Date earliestMoveInDate) {
		this.earliestMoveInDate = earliestMoveInDate;
	}

	public Date getLatestMoveInDate() {
		return latestMoveInDate;
	}

	public void setLatestMoveInDate(Date latestMoveInDate) {
		this.latestMoveInDate = latestMoveInDate;
	}

	public Date getEarliestMoveOutDate() {
		return earliestMoveOutDate;
	}

	public void setEarliestMoveOutDate(Date earliestMoveOutDate) {
		this.earliestMoveOutDate = earliestMoveOutDate;
	}

	public Date getLatestMoveOutDate() {
		return latestMoveOutDate;
	}

	public void setLatestMoveOutDate(Date latestMoveOutDate) {
		this.latestMoveOutDate = latestMoveOutDate;
	}

	//the ugly stuff
	private boolean typeHelper;

	public boolean getTypeHelper() {
		return typeHelper;
	}

	public void setTypeHelper(boolean helper) {
		this.typeHelper = helper;
	}
}