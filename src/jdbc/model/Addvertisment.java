package jdbc.model;

public abstract class Addvertisment {
	
	private String travelFrom;
	private String travelTo;
	private int freePlaces;
	private String Date;
	private String timeOfTravel;
	private Driver driver;
	
	public String getTimeOfTravel() {
		return timeOfTravel;
	}

	public void setTimeOfTravel(String timeOfTravel) {
		this.timeOfTravel = timeOfTravel;
	}

	

	
	public String getTravelFrom() {
		return travelFrom;
	}

	public void setTravelFrom(String travelFrom) {
		this.travelFrom = travelFrom;
	}

	public String getTravelTo() {
		return travelTo;
	}

	public void setTravelTo(String travelTo) {
		this.travelTo = travelTo;
	}

	public int getFreePlaces() {
		return freePlaces;
	}

	public void setFreePlaces(int freePlaces) {
		this.freePlaces = freePlaces;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}
