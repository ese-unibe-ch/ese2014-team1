package ch.unibe.ese.team1.controller.pojos.forms;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class SearchForm {
	
	private boolean studio;

	@NotBlank(message = "Required")
	@Pattern(regexp = "[-\\w\\s]*", message = "Please pick a city from the list")
	private String city;

	@Min(value = 0, message = "Please enter a positive distance")
	@Max(value = 50, message = "You can search only up to 50km")
	private int radius;

	@Min(value = 0, message = "Don't think you will find something for that kind of money")
	private int prize;

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

	public boolean isStudio() {
		return studio;
	}
	
	public void setStudio(boolean studio) {
		this.studio = studio;
	}
}