package jdbc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	private List<Addvertisment> searchAdvertisment(String from, String to,
			String date) {
		return jdbc
				.query("select ads.adId,ads.TownFrom, ads.TownTo , ads.freePlaces,ads.dateOfTravel,ads.timeOfTravel, profiles.email from ads inner join drivers on drivers.driverId=ads.driverId inner join profiles on drivers.profileId=profiles.profileId  where  TownFrom=? and TownTo=? and dateOfTravel=?",
						new Object[] { from, to, date },
						new AdvertismentDriverMapper());

	}
	
	public List<Addvertisment> showActiveAdvertisments(String from, String to,
			String date){
		List<Addvertisment> advertisments=searchAdvertisment(from, to, date);
		List<Addvertisment> activeAds= new ArrayList();
		for (Addvertisment addvertisment : advertisments) {
			String[] time=addvertisment.getTimeOfTravel().split("(:)");
			int hourAdd= Integer.parseInt(time[0]);
			
			int minutesAdd= Integer.parseInt(time[1]);	
			
			Calendar rightNow = Calendar.getInstance();
			int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
		
			int currentMinute= rightNow.get(Calendar.MINUTE);
		
			if(hourAdd-currentHour>0){
				activeAds.add(addvertisment);
			}
			else if (minutesAdd-currentMinute>0){
				activeAds.add(addvertisment);
				
			}
		}
		return activeAds;
	}

}
