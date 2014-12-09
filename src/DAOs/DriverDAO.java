import javax.sql.DataSource;


public interface DriverDAO{
public void setDataSource(DataSource ds);
public void openAdvertisment(String from , String to, String date, int freePlaces);
public void deleteAdvertisment(Addvertisment ad);
public void updateAdvertisment(Addvertisment ad);
public void changeProfile();
public void showProfile();
public void increaseTravels();

}
