package jdbc.templates;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import jdbc.model.*;
import jdbc.mappers.*;
import jdbc.dao.*;

public class DriverJDBCTemplate implements DriverDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbc;

	@Override
	public void deleteAdvertisment(Addvertisment ad) {
		String sql = "delete from ads where adId=?";
		jdbc.update(sql, ad.getAdvertismentId());

	}

	@Override
	public void updateAdvertisment(Addvertisment ad) {
		String sql = "update ads set ads.TownFrom=?, ads.TownTo=?, ads.Date=?, ads.freePlaces=? where adId=?";
		jdbc.update(sql, ad.getTravelFrom(), ad.getTravelTo(), ad.getDate(), ad.getFreePlaces(), ad.getAdvertismentId());

	}

	

	@Override
	public ProfileDriv showProfile(String username) {
		String sql = "select drivers.nameOfDriver,drivers.telephone,drivers.rating,drivers.yearsInDriving,drivers.travels, drivers.SmokeInTheCar, drivers.musicInTheCar,profiles.username, profiles.email from  "
				+ "drivers inner join profiles on drivers.profileId =profiles.profileId where drivers.profileId="
				+ "(SELECT driverId FROM profiles where username like ?)";
		ProfileDriv profile = jdbc.queryForObject(sql,
				new Object[] { username }, new ProfileDriverMapper());
		return profile;

	}

	@Override
	public void increaseTravels(String username) {
		String SQL = "update drivers set drivers.travels=(travels+1) where profileId = "
				+ "  (SELECT profileId FROM profiles where username like ?)";
		jdbc.update(SQL, username);

	}

	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbc = new JdbcTemplate(ds);

	}

	@Override
	public Addvertisment openAdvertisment(int driverId, String from, String to,
			String date, int freePlaces) {

		Addvertisment ad = new Addvertisment();
		ad.setDate(date);
		ad.setFreePlaces(freePlaces);
		ad.setTravelFrom(from);
		ad.setTravelTo(to);

		String sql = "insert into ads (driverId,TownFrom, TownTo,dateOfTravel,freePlaces ) values(?,?,?,?,?) ";
		jdbc.update(sql, driverId, from, to, date, freePlaces);
return ad;
	}

	@Override
	public void changeProfile(String username, String name, String telephone,
			String musicInTheCar, boolean isSmoking) {
		int smoke=(isSmoking)?1:0;
		String SQL = "update drivers set drivers.nameOfDriver=?, drivers.telephone=?, drivers.musicInTheCar=?, drivers.smokeInTheCar=? where profileId = "
				+ "( select profileId from profiles where username like ? )";
		jdbc.update(SQL, name, telephone,musicInTheCar,smoke, username);

		
	}

}
