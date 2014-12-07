package jdbc.model;

public class ProfilePass extends Person{

private String username;
private String email;







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

}
