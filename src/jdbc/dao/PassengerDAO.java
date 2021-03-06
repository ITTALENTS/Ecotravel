package jdbc.dao;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import jdbc.model.*;
import jdbc.mappers.*;

public class PassengerDAO implements IPassengerDAO {

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
	public Passenger showProfile(String username) {

		String showProfileOfPassenger = "select passengers.name,passengers.telephone,passengers.rating,passengers.birthYear, profiles.username, profiles.email from  "
				+ "passengers inner join profiles on passengers.profileId =profiles.profileId where passengers.profileId="
				+ "(SELECT profileId FROM profiles where username like ?)";

		Passenger profile = jdbc.queryForObject(showProfileOfPassenger,
				new Object[] { username }, new ProfilePassMapper());

		return profile;

	}

	@Override
	public Passenger changeProfile(String name, String telephone,
			int birthYear, String username, String password) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		Passenger passenger = new Passenger();
		try {
			
			
			String getProfileIdByUsername = "select profileId from profiles where username=?";
			int idOfProfile = jdbc.queryForInt(getProfileIdByUsername, username);

			String changeProfileOfPassenger = "update passengers inner join profiles on passengers.profileId =profiles.profileId  set passengers.name=?,passengers.telephone=?,passengers.birthYear=?, profiles.password=? where passengers.profileId=?";

			jdbc.update(changeProfileOfPassenger, name, telephone, birthYear,
					password, idOfProfile);
			passenger= showProfile(username);
			
			transactionManager.commit(status);
		} catch (DataAccessException e) {

			transactionManager.rollback(status);

		}
		return passenger;

	}
	@Override
	public void voteForDriver(String username, int vote) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		try {

			jdbc.update(
					"update voting set numberOfvotes=( numberOfVotes +1) where driverId=(SELECT driverId FROM drivers join profiles on drivers.profileId=profiles.profileId  where username like ?)",
					username);

			jdbc.update(
					"update drivers inner join profiles on drivers.profileId=profiles.profileId set drivers.rating=(rating +?)  where  username like ?",
					vote, username);

			String getRatingOfDriver = "select drivers.rating from drivers inner join voting on drivers.driverId= voting.driverId inner join profiles on drivers.profileId= profiles.profileId where username=?";
			@SuppressWarnings("deprecation")
			int rating = jdbc.queryForInt(getRatingOfDriver, username);
			String getNumberOfVotesForriver = "select voting .numberOfVotes from voting inner join drivers on drivers.driverId= voting.driverId inner join profiles on drivers.profileId= profiles.profileId where username=?";

			@SuppressWarnings("deprecation")
			int numberOfVotes = jdbc.queryForInt(getNumberOfVotesForriver,
					username);

			int newVote = rating / numberOfVotes;

			jdbc.update(
					"update drivers inner join profiles on drivers.profileId=profiles.profileId set drivers.rating=?  where  username like ?",
					newVote, username);

			transactionManager.commit(status);

		} catch (DataAccessException e) {

			transactionManager.rollback(status);
		}

	}

	public void registerPassenger(String username,String email, String password, String name, int birthYear,
			String telephone) {

		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			
		
			String insertIntoProfiles = "insert into profiles (username, email, password) values (?, ?,?)";
			jdbc.update(insertIntoProfiles,username,
					email,password);
			

			String getProfileIdByUsername = "select profileId from profiles where username=?";
			@SuppressWarnings("deprecation")
			int profileId = jdbc.queryForInt(getProfileIdByUsername, username);
			String insertIntoPassengers = "Insert into passengers(profileId,name, rating , telephone, birthYear) values(?,?,?,?,?)";

			jdbc.update(insertIntoPassengers, profileId, name, 0, telephone,
					birthYear);

			transactionManager.commit(status);

		} catch (DataAccessException e) {

			transactionManager.rollback(status);
		}
	}

}
