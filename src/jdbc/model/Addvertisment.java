package jdbc.model;

public class Addvertisment {
	private int AdvertismentId;
	private String travelFrom;
	private String travelTo;
	private int freePlaces;
	private String Date;
	private Driver driver;
	
	public int getAdvertismentId() {
		return AdvertismentId;
	}

	public void setAdvertismentId(int advertismentId) {
		AdvertismentId = advertismentId;
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
