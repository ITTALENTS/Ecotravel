package jdbc.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import jdbc.mappers.AdvertismentDriverMapper;
import jdbc.model.Addvertisment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class TripBetweenTownsDAO implements IAddvertismentDAO{
	private JdbcTemplate jdbc;
	
	public void setDataSource(DataSource ds) {
		this.jdbc = new JdbcTemplate(ds);

	}
	
	public List<Addvertisment> searchAdvertisment(String from, String to,
			String date) {
	/*	Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    	int currentTime=(sdf.HOUR0_FIELD);
    	System.out.println(currentTime);
		String getTimeOfAdvertisment= "select ads.timeOfTravel where ads.TownFrom=? and ads.TownTo=? and ads.dateOfTravel=?";
int timeOfAdvertisment= jdbc.queryForInt(getTimeOfAdvertisment, from,to,date);
System.out.println(timeOfAdvertisment);*/
		return jdbc
				.query("select ads.adId,ads.TownFrom, ads.TownTo , ads.freePlaces,ads.dateOfTravel,ads.timeOfTravel, profiles.email from ads inner join drivers on drivers.driverId=ads.driverId inner join profiles on drivers.profileId=profiles.profileId  where  townFrom=? and townTo=? and dateOfTravel=?",
						new Object[] { from, to, date },
						new AdvertismentDriverMapper());

	}
}
