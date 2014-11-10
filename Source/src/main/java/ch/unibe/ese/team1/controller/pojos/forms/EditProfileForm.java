package ch.unibe.ese.team1.controller.pojos.forms;

import org.hibernate.validator.constraints.NotBlank;

import ch.unibe.ese.team1.model.Gender;
import ch.unibe.ese.team1.model.UserPicture;
// import ch.unibe.ese.team1.model.UserRole;

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

	// @JsonIgnore
	// @OneToOne(cascade = CascadeType.ALL)
	private UserPicture picture;
	
	// @Column(nullable = true)
	// @Lob
	private String aboutMe;

	private String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	private String getFirstName() {
		return firstName;
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private String getLastName() {
		return lastName;
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private Gender getGender() {
		return gender;
	}

	private void setGender(Gender gender) {
		this.gender = gender;
	}

	private UserPicture getPicture() {
		return picture;
	}

	private void setPicture(UserPicture picture) {
		this.picture = picture;
	}

	private String getAboutMe() {
		return aboutMe;
	}

	private void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}


}
