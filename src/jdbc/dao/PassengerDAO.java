package jdbc.dao;
import java.util.List;

import javax.sql.DataSource;
import jdbc.model.*;

public interface PassengerDAO{
public void setDataSource(DataSource ds);	
public List<AdvertismentDtls> searchAdvertisment(String from, String to, String date);
public void changeProfile(String name, String telephone, String username);
public ProfilePass showProfile(String username);

public void voteForDriver(String username, int vote);


}
