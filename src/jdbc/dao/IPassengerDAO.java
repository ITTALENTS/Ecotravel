package jdbc.dao;

import java.util.List;

import javax.sql.DataSource;
import jdbc.model.*;

public interface IPassengerDAO {
	public void setDataSource(DataSource ds);

	public List<Addvertisment> searchAdvertisment(String from, String to,
			String date);

	public void changeProfile(String name, String telephone,String birthYear, String username);

	public Passenger showProfile(String username);

	public void voteForDriver(String username, int vote);

}
