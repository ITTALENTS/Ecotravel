package jdbc.model;

import javax.validation.constraints.Size;

public abstract class Person {

	//@Size(min=2, message="Name can't be shorter than {min} symbols.")
	private String name;
	private int rating;
	// validation for telephone ??
	private String telephone;
	private int birthYear;
	
	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	private Profile profile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
	
/*	@Override
	public String toString() {
		return "Person [name=" + name + ", rating=" + rating + ", telephone="
				+ telephone + ", birthYear=" + birthYear + ", profile="
				+ profile + "]";
	}
*/
}
