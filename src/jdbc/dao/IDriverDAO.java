package jdbc.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.model.*;

public interface IDriverDAO {
	public void setDataSource(DataSource ds);

	public void openAdvertisment(String username, String from, String to,
			String date, String time, int freePlaces);

	public void deleteAdvertisment(String username, String date);

	public void updateAdvertisment(String username, String date, int freePlaces);

	public void changeProfile(String username, String name, String telephone,
			String musicInTheCar, boolean isSmoking, int birthYear);

	public Driver showProfile(String username);

	public void increaseTravels(String username);

	public List<Addvertisment> getActiveAdvertismentsForDriver(String username);

}
