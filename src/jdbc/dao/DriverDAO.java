package jdbc.dao;

import javax.sql.DataSource;
import jdbc.model.*;

public interface DriverDAO {
	public void setDataSource(DataSource ds);

	public Addvertisment openAdvertisment(int driverId, String from, String to,
			String date, int freePlaces);

	public void deleteAdvertisment(Addvertisment ad);

	public void updateAdvertisment(Addvertisment ad);

	public void changeProfile(String username, String name, String telephone, String musicInTheCar, boolean isSmoking);

	public ProfileDriv showProfile(String username);

	public void increaseTravels(String username);

}
