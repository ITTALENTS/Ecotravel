package jdbc.dao;

import javax.sql.DataSource;
import jdbc.model.*;

public interface IPassengerDAO {
	public void setDataSource(DataSource ds);

	public Passenger changeProfile(String name, String telephone, int birthYear,
			String username, String password);

	public Passenger showProfile(String username);

	public void voteForDriver(String username, int vote);

}
