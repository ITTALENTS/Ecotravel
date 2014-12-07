package jdbc.dao;
import javax.sql.DataSource;
import jdbc.model.*;

public interface DriverDAO{
public void setDataSource(DataSource ds);
public void openAdvertisment(int driverId,String from , String to, String date, int freePlaces);
public void deleteAdvertisment(Addvertisment ad);
public void updateAdvertisment(Addvertisment ad);
public void changeProfile();
public ProfileDriv showProfile( String username);
public void increaseTravels();

}
