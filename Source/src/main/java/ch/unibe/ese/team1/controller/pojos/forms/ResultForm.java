package ch.unibe.ese.team1.controller.pojos.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class ResultForm 
{
	private boolean room;
	private boolean studio;
	
	@NotBlank(message = "Required")
	@Pattern(regexp = "[A-Za-z]*", message = "Can only contain letters")
	private String city;
	
	private int radius;
	
	@Min(value = 1, message = "Don't think you will find something for that kind of money")
	private int prize;
	
	///////////////////////
	//Getters and setters//
	///////////////////////
	public boolean isRoom() { return room; }

	public void setRoom(boolean room) { this.room = room; }

	public boolean isStudio() { return studio; }

	public void setStudio(boolean studio) { this.studio = studio; }

	public String getCity() { return city; }
	
	public void setCity(String city) { this.city = city; }
	
	public int getRadius() { return radius; }

	public void setRadius(int radius) { this.radius = radius; }
	
	public int getPrize() { return prize; }
	
	public void setPrize(int prize) { this.prize = prize; }
}