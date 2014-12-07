
public class AdvertismentDtls {
private String townFrom;
private String townTo;
private int freePlaces;
private String date;
private String email;

public AdvertismentDtls(String townFrom, String townTo, int freePlaces, String date, String email) {
	this.townFrom=townFrom;
	this.townTo=townTo;
	this.freePlaces=freePlaces;
	this.date=date;
	this.email=email;
}
public String getTownFrom() {
	return townFrom;
}
public void setTownFrom(String townFrom) {
	this.townFrom = townFrom;
}
public String getTownTo() {
	return townTo;
}
public void setTownTo(String townTo) {
	this.townTo = townTo;
}
public int getFreePlaces() {
	return freePlaces;
}
public void setFreePlaces(int freePlaces) {
	this.freePlaces = freePlaces;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}
