package jdbc.templates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import jdbc.dao.*;
import jdbc.model.*;
import jdbc.mappers.*;

public class PassengerDAO implements IPassengerDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbc;
	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbc = new JdbcTemplate(ds);

	}

	@Override
	public List<AdvertismentDtls> searchAdvertisment(String from, String to,
			String date) {

		return jdbc
				.query("select ads.TownFrom, ads.TownTo , ads.freePlaces,ads.dateOfTravel, profiles.email from ads inner join drivers on drivers.driverId=ads.driverId inner join profiles on drivers.profileId=profiles.profileId  where  townFrom=? and townTo=? and dateOfTravel=?",
						new Object[] { from, to, date },
						new RowMapper<AdvertismentDtls>() {

							@Override
							public AdvertismentDtls mapRow(ResultSet rs,
									int rowNum) throws SQLException {

								return new AdvertismentDtls(rs
										.getString("TownFrom"), rs
										.getString("TownTo"), rs
										.getInt("freePlaces"), rs
										.getString("dateOfTravel"), rs
										.getString("email"));

							}

						});

	}

	@Override
	public ProfilePass showProfile(String username) {

		String sql = "select passengers.name,passengers.telephone,passengers.rating,passengers.birthYear, profiles.username, profiles.email from  "
				+ "passengers inner join profiles on passengers.profileId =profiles.profileId where passengers.profileId="
				+ "(SELECT profileId FROM profiles where username like ?)";
		ProfilePass profile = jdbc.queryForObject(sql,
				new Object[] { username }, new ProfilePassMapper());

		return profile;

	}

	@Override
	public void changeProfile(String name, String telephone,String birthYear, String username) {

		String SQL = "update passengers set passengers.name=?, passengers.telephone=?, passengers.birthYear=? where profileId = "
				+ "  (SELECT profileId FROM profiles where username like ?)";
		jdbc.update(SQL, name, telephone,birthYear, username);

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

			String sql = "select drivers.rating from drivers inner join voting on drivers.driverId= voting.driverId inner join profiles on drivers.profileId= profiles.profileId where username=?";
			int rating = jdbc.queryForInt(sql, username);
			String sql2 = "select voting .numberOfVotes from voting inner join drivers on drivers.driverId= voting.driverId inner join profiles on drivers.profileId= profiles.profileId where username=?";
			int numberOfVotes = jdbc.queryForInt(sql2, username);
			int newVote = rating / numberOfVotes;

			jdbc.update(
					"update drivers inner join profiles on drivers.profileId=profiles.profileId set drivers.rating=?  where  username like ?",
					newVote, username);

			transactionManager.commit(status);
		} catch (DataAccessException e) {

			transactionManager.rollback(status);
		}

	}
	public void registerPassenger(String username, String name, String birthYear, String telephone){
		
		String sql = "select profileId from profiles where username=?";
		int profileId= jdbc.queryForInt(sql, username);
		String insert ="Insert into passengers(profileId,name, rating , telephone, birthYear) values(?,?,?,?,?)";
		jdbc.update(insert,profileId,name,0,telephone,birthYear);
	}

}
