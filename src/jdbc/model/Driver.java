package jdbc.model;

public class Driver extends Person {

	private int numberOfTravels;
	private int yearsInDriving;
	private boolean isSmoking;
	private String musicInTheCar;

	@Override
	public String getBirthYear() {
		// TODO Auto-generated method stub
		return super.getBirthYear();
	}

	@Override
	public void setBirthYear(String birthYear) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	@Override
	public int getRating() {
		// TODO Auto-generated method stub
		return super.getRating();
	}

	@Override
	public void setRating(int rating) {
		// TODO Auto-generated method stub
		super.setRating(rating);
	}

	@Override
	public String getTelephone() {
		// TODO Auto-generated method stub
		return super.getTelephone();
	}

	@Override
	public void setTelephone(String telephone) {
		// TODO Auto-generated method stub
		super.setTelephone(telephone);
	}

	@Override
	public Profile getProfile() {
		// TODO Auto-generated method stub
		return super.getProfile();
	}

	@Override
	public void setProfile(Profile profile) {
		// TODO Auto-generated method stub
		super.setProfile(profile);
	}

	public int getNumberOfTravels() {
		return numberOfTravels;
	}

	public void setNumberOfTravels(int numberOfTravels) {
		this.numberOfTravels = numberOfTravels;
	}

	public int getYearsInDriving() {
		return yearsInDriving;
	}

	public void setYearsInDriving(int yearsInDriving) {
		this.yearsInDriving = yearsInDriving;
	}

	public boolean getIsSmoking() {
		return isSmoking;
	}

	public void setIsSmoking(boolean isSmoking) {
		this.isSmoking = isSmoking;
	};

}
