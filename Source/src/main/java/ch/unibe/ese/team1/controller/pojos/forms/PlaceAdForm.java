package ch.unibe.ese.team1.controller.pojos.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class PlaceAdForm {
	
	@NotBlank(message = "Required")
	private String title;
	
	@NotBlank(message = "RequiredYOLO")
	private String street;
	
	@Pattern(regexp = "^[0-9]{4} - [-\\w\\s]*", message = "Please pick a city from the list")
	private String city;
	
	@NotBlank(message = "Required")
	private String moveInDate;
	
	private String moveOutDate;

	@Min(value = 1, message = "Has to be equal to 1 or more")
	private int prize;

	@Min(value = 1, message = "Has to be equal to 1 or more")
	private int squareFootage;

	@NotBlank(message = "Required")
	private String roomDescription;

	private String preferences;

	@NotBlank(message = "Required")
	private String roommates;
	
	// optional
	private String room_friends;

	private boolean studio;
	
	private boolean smokers;
	private boolean animals;
	private boolean garden;
	private boolean balcony;
	private boolean cellar;
	private boolean furnished;
	private boolean cable;
	private boolean garage;
	private String food;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
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

	public boolean isSmokers() {
		return smokers;
	}

	public void setSmokers(boolean smoker) {
		this.smokers = smoker;
	}

	public boolean isAnimals() {
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
	
	public boolean isFurnished() {
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
		assert(food.equals("Vegan") || food.equals("Vegetarian") || food.equals("Everything"));
		this.food = food;
	}

	public String getMoveInDate() {
		return moveInDate;
	}

	public void setMoveInDate(String moveInDate) {
		this.moveInDate = moveInDate;
	}

	public String getMoveOutDate() {
		return moveOutDate;
	}

	public void setMoveOutDate(String moveOutDate) {
		this.moveOutDate = moveOutDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getRoom_friends() {
		return room_friends;
	}

	public void setRoom_friends(String room_friends) {
		this.room_friends = room_friends;
	}
	
	public boolean isStudio() {
		return studio;
	}
	
	public void setStudio(boolean studio) {
		this.studio = studio;
	}
}
