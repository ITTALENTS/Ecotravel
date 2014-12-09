package DAOInterfaces;

import java.util.List;

import javax.sql.DataSource;

import classes.Driver;
import classes.Passenger;
import classes.ProfilePass;


public interface IPassengerDAO{
	public void setDataSource(DataSource ds);	
	public List<defaults.AdvertismentDtls> searchAdvertisment(String from, String to, String date);
	public void changeProfile(String name, String telephone, String username);
	public ProfilePass showProfile(Passenger p, String username);
	public void voteForDriver(Driver d, int vote);

}
