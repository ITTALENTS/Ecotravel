package jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jdbc.model.*;
import jdbc.mappers.*;

public class DriverDAO implements IDriverDAO {

	private JdbcTemplate jdbc;
	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setDataSource(DataSource ds) {
		this.jdbc = new JdbcTemplate(ds);

	}

	@Override
	public void deleteAdvertisment(String username, String date) {
		String deleteAdvertisment = "delete from ads where driverId=(select driverId from drivers where profileId= (select profileId from profiles where username =?)) and dateOfTravel=?";
		jdbc.update(deleteAdvertisment, username, date);

	}

	@Override
	public void updateAdvertisment(String username, String date, int freePlaces) {
		String updateAdvertisment = "update ads set  ads.freePlaces=?  where driverId=(select driverId from drivers where profileId= (select profileId from profiles where username =?)) and dateOfTravel=?";
		jdbc.update(updateAdvertisment, freePlaces, username, date);

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
	public void receivedMails(String username) {
		String increaseTravelesOfDriver = "update drivers set drivers.travels=(travels+1) where profileId = "
				+ "  (SELECT profileId FROM profiles where username like ?)";
		jdbc.update(increaseTravelesOfDriver, username);

	}

	@Override
	public void openAdvertisment(String username, String from, String to,
			String date, String time, int freePlaces) {

		/*
		 * TripBetweenTowns ad = new TripBetweenTowns(); ad.setDate(date);
		 * ad.setFreePlaces(freePlaces); ad.setTravelFrom(from);
		 * ad.setTravelTo(to);
		 */

		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			String getDriverIdByUsername = "select driverId from drivers where profileId like(select profileId from profiles where username like ?)";
			@SuppressWarnings("deprecation")
			int driverId = jdbc.queryForInt(getDriverIdByUsername, username);

			String insertAdvertisment = "insert into ads (driverId,TownFrom, TownTo,dateOfTravel,timeOfTravel,freePlaces  ) values(?,?,?,?,?,?) ";
			jdbc.update(insertAdvertisment, driverId, from, to, date, time,
					freePlaces);

			transactionManager.commit(status);

		} catch (DataAccessException e) {

			transactionManager.rollback(status);
		}

	}

	private void changePassword(String username, String password) {

		String changePasswordOfProfile = "Update profiles set password=? where username=?";
		jdbc.update(changePasswordOfProfile, password, username);

	}

	@Override
	public Driver changeProfile(String username, String name, String telephone,
			String musicInTheCar, boolean isSmoking, int birthYear,
			String password) {

		int smoke = (isSmoking) ? 1 : 0;

		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		Driver driver = new Driver();
		try {

			String getProfileIdByUsername = "select profileId from profiles where username=?";
			int idOfProfile = jdbc
					.queryForInt(getProfileIdByUsername, username);
			changePassword(username, password);

			String changeProfileOfDriver = "update drivers set drivers.nameOfDriver=?,drivers.telephone=?,drivers.birthYear=?,drivers.smokeInTheCar=?, drivers.musicInTheCar=? where drivers.profileId=?";

			jdbc.update(changeProfileOfDriver, name, telephone, birthYear,
					smoke, musicInTheCar, idOfProfile);
			driver = showProfile(username);
			transactionManager.commit(status);

		} catch (DataAccessException e) {

			transactionManager.rollback(status);

		}
		return driver;
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

	private List<Addvertisment> getAdsForDriver(String username) {
		return jdbc
				.query("select ads.TownFrom, ads.TownTo , ads.freePlaces,ads.dateOfTravel,ads.timeOfTravel, profiles.username from ads inner join drivers on drivers.driverId=ads.driverId inner join profiles on drivers.profileId=profiles.profileId  where  username=?",
						new Object[] { username },
						new AdvertismentDriverMapper());
	}
	
	
	

	@Override
	public List<Addvertisment> getActiveAdvertismentsForDriver(String username) {
		List<Addvertisment> allAdvertisments = getAdsForDriver(username);

		List<Addvertisment> upToDateAds = new ArrayList<Addvertisment>();

		for (Addvertisment addvertisment : allAdvertisments) {
			String[] dateOfTravel = addvertisment.getDate().split("(-)");

			int yearOfTravel = Integer.parseInt(dateOfTravel[0]);

			int monthOfTravel = Integer.parseInt(dateOfTravel[1]);

			int dayOfTravel = Integer.parseInt(dateOfTravel[2]);

			String[] timeOfTravel = addvertisment.getTimeOfTravel()
					.split("(:)");

			int hourAdd = Integer.parseInt(timeOfTravel[0]);

			int minutesAdd = Integer.parseInt(timeOfTravel[1]);

			Calendar rightNow = Calendar.getInstance();

			int currentYear = rightNow.get(Calendar.YEAR);

			int currentMonth = rightNow.get(Calendar.MONTH) + 1;

			int currentDay = rightNow.get(Calendar.DAY_OF_MONTH);

			int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);

			int currentMinute = rightNow.get(Calendar.MINUTE);

			if (yearOfTravel > currentYear) {

				upToDateAds.add(addvertisment);
				continue;
			} else if (yearOfTravel == currentYear) {

				if (monthOfTravel > currentMonth) {

					upToDateAds.add(addvertisment);
					continue;

				} else if (monthOfTravel == currentMonth) {

					if (dayOfTravel > currentDay) {

						upToDateAds.add(addvertisment);
						continue;

					} else if (dayOfTravel == currentDay) {

						if (hourAdd > currentHour) {

							upToDateAds.add(addvertisment);
							continue;

						} else if (hourAdd == currentHour) {

							if (minutesAdd > currentMinute)
								upToDateAds.add(addvertisment);
						}
					}
				}

			}

		}
		return upToDateAds;
	}
	
	
	
	

	public List<Driver> getListOfMostWantedDrivers() {
		String getMostWantedDrivers = "select drivers.rating , profiles.username from drivers inner join profiles where (drivers.profileId=profiles.profileId) order by rating desc";

		return jdbc.query(getMostWantedDrivers, new RowMapper<Driver>() {

			public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
				Driver driver = new Driver();
				Profile profile = new Profile();

				profile.setUsername(rs.getString("username"));
				driver.setProfile(profile);
				driver.setRating(rs.getInt("rating"));

				return driver;
			}
		});

	}

	
	
	public List<Driver> getListOfMostActiveDrivers() {
		String getMostWantedDrivers = "select drivers.travels , profiles.username from drivers inner join profiles where (drivers.profileId=profiles.profileId) order by rating desc";

		return jdbc.query(getMostWantedDrivers, new RowMapper<Driver>() {
			public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
				Driver driver = new Driver();
				Profile profile = new Profile();

				profile.setUsername(rs.getString("username"));
				driver.setProfile(profile);
				driver.setRating(rs.getInt("travels"));

				return driver;
			}
		});

	}

}
