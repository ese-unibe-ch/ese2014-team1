package ch.unibe.ese.team1.controller.pojos.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class SearchForm
{
	@NotBlank(message = "Required")
	@Pattern(regexp = "[A-Za-z]*", message = "Can only contain letters")
	private String city;
	
	@Min(value = 1, message = "Don't think you will find something for that kind of money")
	private int prize;
	
	private boolean smoker;
	private boolean animals;
	private boolean room;
	private boolean studio;

	
	///////////////////////
	//Getters and setters//
	///////////////////////
	public boolean isRoom() { return room; }

	public void setRoom(boolean room) { this.room = room; }

	public boolean isStudio() { return studio; }

	public void setStudio(boolean studio) { this.studio = studio; }

	public boolean isSmoker() { return smoker; }

	public void setSmoker(boolean smoker) { this.smoker = smoker; }

	public boolean isAnimals() { return animals; }

	public void setAnimals(boolean animals) { this.animals = animals; }
}