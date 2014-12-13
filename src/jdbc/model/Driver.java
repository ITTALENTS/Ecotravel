package jdbc.model;

public class Driver extends Person {

	private int numberOfTravels;	
	private int licenseYear;
	private boolean isSmoking;
	private String musicInTheCar;
	
	
	public int getLicenseYear() {
		return licenseYear;
	}

	public void setLicenseYear(int licenseYear) {
		this.licenseYear = licenseYear;
	}

	public void setSmoking(boolean isSmoking) {
		this.isSmoking = isSmoking;
	}

	@Override
	public int getBirthYear() {
		return super.getBirthYear();
	}
	
	@Override
	public void setBirthYear(int birthYear) {
		super.setBirthYear(birthYear);
	}

	public String getMusicInTheCar() {
		return musicInTheCar;
	}

	public void setMusicInTheCar(String musicInTheCar) {
		this.musicInTheCar = musicInTheCar;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public int getRating() {
		return super.getRating();
	}

	@Override
	public void setRating(int rating) {
		super.setRating(rating);
	}

	@Override
	public String getTelephone() {
		return super.getTelephone();
	}

	@Override
	public void setTelephone(String telephone) {
		super.setTelephone(telephone);
	}

	@Override
	public Profile getProfile() {
		return super.getProfile();
	}

	@Override
	public void setProfile(Profile profile) {
		super.setProfile(profile);
	}

	public int getNumberOfTravels() {
		return numberOfTravels;
	}

	public void setNumberOfTravels(int numberOfTravels) {
		this.numberOfTravels = numberOfTravels;
	}



	public boolean getIsSmoking() {
		return isSmoking;
	}

	public void setIsSmoking(boolean isSmoking) {
		this.isSmoking = isSmoking;
	};

}
