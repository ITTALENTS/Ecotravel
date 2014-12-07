package jdbc.templates;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

public class PassengerJDBCTemplate implements PassengerDAO {

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
				.query("select ecotravel.ads.TownFrom, ecotravel.ads.TownTo , ecotravel.ads.freePlaces,ecotravel.ads.dateOfTravel, ecotravel.profiles.email from ecotravel.ads inner join ecotravel.drivers on ecotravel.drivers.driverId=ecotravel.ads.driverId inner join ecotravel.profiles on drivers.profileId=ecotravel.profiles.profileId  where  townFrom=? and townTo=? and dateOfTravel=?",
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
	public ProfilePass showProfile( String username) {

		String sql = "select ecotravel.passengers.name,ecotravel.passengers.telephone,ecotravel.passengers.rating, ecotravel.profiles.username, ecotravel.profiles.email from  "
				+ "ecotravel.passengers inner join ecotravel.profiles on ecotravel.passengers.profileId =ecotravel.profiles.profileId where ecotravel.passengers.profileId="
				+ "(SELECT profileId FROM ecotravel.profiles where username like ?)";
		ProfilePass profile = jdbc.queryForObject(sql,
				new Object[] { username }, new ProfilePassMapper());

		return profile;

	}

	@Override
	public void changeProfile(String name, String telephone, String username) {

		String SQL = "update ecotravel.passengers set ecotravel.passengers.name=?, ecotravel.passengers.telephone=? where profileId = "
				+ "  (SELECT profileId FROM ecotravel.profiles where username like ?)";
		jdbc.update(SQL, name, telephone, username);

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

}
