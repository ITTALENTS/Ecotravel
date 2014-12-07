package jdbc.templates;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import jdbc.model.*;
import jdbc.mappers.*;
import jdbc.dao.*;
public class DriverJDBCTemplate implements DriverDAO{
	private	DataSource dataSource;
	private	JdbcTemplate jdbc;

	
	

	@Override
	public void deleteAdvertisment(Addvertisment ad) {
		String sql="delete from ecotravels.ads where adId=?";
		
	}

	@Override
	public void updateAdvertisment(Addvertisment ad) {
		String sql="update ecotravels.ads set ads.TownFrom=?, ads.TownTo=?, ads.Date=?, ads.freePlaces=? where adId=?";
		
	}

	@Override
	public void changeProfile() {
		
		
	}

	@Override
	public ProfileDriv showProfile( String username) {
		String sql = "select drivers.nameOfDriver,drivers.telephone,drivers.rating,drivers.yearsInDriving,drivers.travels, drivers.SmokeInTheCar, drivers.musicInTheCar,profiles.username, profiles.email from  "
				+ "drivers inner join profiles on drivers.profileId =profiles.profileId where drivers.profileId="
				+ "(SELECT driverId FROM profiles where username like ?)";
		ProfileDriv profile = jdbc.queryForObject(sql,
				new Object[] { username }, new ProfileDriverMapper());
		return profile;
		
	}

	@Override
	public void increaseTravels() {
		
		
	}

	
	public void setDataSource(DataSource ds) {
		this.dataSource=ds;
		this.jdbc= new JdbcTemplate(ds);
		
	}

	@Override
	public void openAdvertisment(int driverId, String from, String to,
			String date, int freePlaces) {
		

	Addvertisment ad= new Addvertisment();
	ad.setDate(date);
	ad.setFreePlaces(freePlaces);
	ad.setTravelFrom(from);
	ad.setTravelTo(to);


	String sql= "insert into ecotravel.ads (driverId,TownFrom, TownTo,dateOfTravel, ) values(?,?,?,?) ";
	jdbc.update(sql,driverId, from, to,date, freePlaces);
			
		
	
	}



}
