import java.util.List;

import javax.sql.DataSource;


public interface PassengerDAO{
public void setDataSource(DataSource ds);	
public List<AdvertismentDtls> searchAdvertisment(String from, String to, String date);
public void changeProfile(String name, String telephone, String username);
public ProfilePass showProfile(Passenger p, String username);

public void voteForDriver(Driver d, int vote);


}
