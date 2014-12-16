package jdbc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class Profile {

	private int profileId;

	//@Size(min=3, message="Username must contain at least {min} characters.")
	//@Pattern(regexp="^\\w{3,}$", message="Username must contain only characters, numbers and the underscore character.")
	private String username;
	
	//@Email(message="This is not a valid email address.")
	//@Pattern(regexp=".*\\@.*\\..*")
	private String email;
	
	//@Size(min=8, message="Password must contain at least {min} characters.")
	private String password;

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*@Override
	public String toString() {
		return "Profile [profileId=" + profileId + ", username=" + username
				+ ", email=" + email + ", password=" + password + "]";
	}*/

}
