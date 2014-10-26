package ch.unibe.ese.team1.controller.pojos.forms;

public class ResultForm 
{
	private boolean room;
	private boolean studio;
	private String city;
	private int radius;
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