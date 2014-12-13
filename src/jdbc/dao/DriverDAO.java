package jdbc.dao;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jdbc.model.*;
import jdbc.mappers.*;

public class DriverDAO implements IDriverDAO {
	private JdbcTemplate jdbc;
	private PlatformTransactionManager transactionManager;

	@Override
	public void deleteAdvertisment(Addvertisment ad) {
		String deleteAdvertisment = "delete from ads where adId=?";
		jdbc.update(deleteAdvertisment, ad.getAdvertismentId());

	}

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public void updateAdvertisment(Addvertisment ad) {
		String updateAdvertisment = "update ads set ads.TownFrom=?, ads.TownTo=?, ads.Date=?, ads.freePlaces=? where adId=?";
		jdbc.update(updateAdvertisment, ad.getTravelFrom(), ad.getTravelTo(),
				ad.getDate(), ad.getFreePlaces(), ad.getAdvertismentId());

	}

	@Override
	public Driver showProfile(String username) {
		String showProfilePersonByUsername = "select drivers.nameOfDriver,drivers.telephone,drivers.rating,drivers.yearsInDriving,drivers.travels, drivers.SmokeInTheCar, drivers.musicInTheCar, drivers.birthYear,profiles.username, profiles.email from  "
				+ "drivers inner join profiles on drivers.profileId =profiles.profileId where drivers.profileId="
				+ "(SELECT profileId FROM profiles where username like ?)";
		Driver profile = jdbc.queryForObject(showProfilePersonByUsername,
				new Object[] { username }, new ProfileDriverMapper());
		return profile;

	}

	@Override
	public void increaseTravels(String username) {
		String increaseTravelesOfDriver = "update drivers set drivers.travels=(travels+1) where profileId = "
				+ "  (SELECT profileId FROM profiles where username like ?)";
		jdbc.update(increaseTravelesOfDriver, username);

	}

	public void setDataSource(DataSource ds) {
		this.jdbc = new JdbcTemplate(ds);

	}

	@Override
	public Addvertisment openAdvertisment(String username, String from, String to,
			String date,String time, int freePlaces) {

		TripBetweenTowns ad = new TripBetweenTowns();
		ad.setDate(date);
		ad.setFreePlaces(freePlaces);
		ad.setTravelFrom(from);
		ad.setTravelTo(to);
String getDriverIdByUsername="select driverId from drivers where profileId like(select profileId from profiles where username like ?)";
int driverId = jdbc.queryForInt(getDriverIdByUsername, username);
System.out.println(driverId);
		String insertAdvertisment = "insert into ads (driverId,TownFrom, TownTo,dateOfTravel,timeOfTravel,freePlaces  ) values(?,?,?,?,?,?) ";
		jdbc.update(insertAdvertisment, driverId, from, to, date,time, freePlaces);
		return ad;
	}

	@Override
	public void changeProfile(String username, String name, String telephone,
			String musicInTheCar, boolean isSmoking, String birthYear) {
		int smoke = (isSmoking) ? 1 : 0;
		String updateProfiles = "update drivers set drivers.nameOfDriver=?, drivers.telephone=?, drivers.musicInTheCar=?, drivers.smokeInTheCar=?, drivers.birthYear=? where profileId = "
				+ "( select profileId from profiles where username like ? )";
		jdbc.update(updateProfiles, name, telephone, musicInTheCar, smoke,
				birthYear, username);

	}

	public void registerDriver(String username, String name, int birthYear,
			String telephone, int yearsInDriving, String musicInTheCar,
			boolean smokeInTheCar) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			String getProfileIdByUsername = "select profileId from profiles where username=?";
			@SuppressWarnings("deprecation")
			int profileId = jdbc.queryForInt(getProfileIdByUsername, username);
			String insertIntoDrivers = "Insert into drivers(profileId,nameOfDriver,telephone, rating, smokeInTheCar, travels,yearsInDriving,musicInTheCar,birthYear)  values(?,?,?,?,?,?,?,?,?) ";
			int smoke = (smokeInTheCar) ? 1 : 0;
			jdbc.update(insertIntoDrivers, profileId, name, telephone, 0,
					smoke, 0, yearsInDriving, musicInTheCar, birthYear);
			transactionManager.commit(status);
		} catch (DataAccessException e) {

			transactionManager.rollback(status);
		}

	}

}
