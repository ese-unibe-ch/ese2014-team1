package ch.unibe.ese.team1.controller.pojos.forms;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class SearchForm {
	
	//studio: true, room: false
	private boolean studio;

	@NotBlank(message = "Required")
	@Pattern(regexp = "[-\\w\\s]*", message = "Please pick a city from the list")
	private String city;

	@Min(value = 0, message = "Please enter a positive distance")
	private int radius;

	@Min(value = 0, message = "Don't think you will find something for that kind of money")
	private int prize;
	
	@AssertFalse(message = "Please select either or both types")
	private boolean noRoomNoStudio;
	
	private boolean bothRoomAndStudio;

	// /////////////////////
	// Getters and setters//
	// /////////////////////
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
	
	//the ugly stuff
	private boolean typeHelper;

	public boolean getTypeHelper() {
		return typeHelper;
	}

	public void setTypeHelper(boolean helper) {
		this.typeHelper = helper;
	}
}