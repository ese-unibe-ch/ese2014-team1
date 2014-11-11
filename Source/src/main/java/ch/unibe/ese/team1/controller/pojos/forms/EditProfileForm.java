package ch.unibe.ese.team1.controller.pojos.forms;

import org.hibernate.validator.constraints.NotBlank;

import ch.unibe.ese.team1.model.Gender;
import ch.unibe.ese.team1.model.UserPicture;

// import com.fasterxml.jackson.annotation.JsonIgnore;

public class EditProfileForm {

	@NotBlank(message = "Required")
	private String password;

	@NotBlank(message = "Required")
	private String firstName;

	@NotBlank(message = "Required")
	private String lastName;

	@NotBlank(message = "Required")
	private Gender gender;

	private UserPicture picture;
	
	private String aboutMe;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public UserPicture getPicture() {
		return picture;
	}

	public void setPicture(UserPicture picture) {
		this.picture = picture;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
}
