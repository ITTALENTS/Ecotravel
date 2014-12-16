package jdbc.dao;

import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import jdbc.mappers.*;
import jdbc.model.*;
import java.security.SecureRandom;
import java.util.Random;

public class ProfileDAO implements IProfileDAO {

	private JdbcTemplate jdbc;

	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public void setDataSource(DataSource ds) {
		this.jdbc = new JdbcTemplate(ds);

	}

	public boolean usernameExist(String username) {
		String usernameInDB = null;
		boolean usernameExistInDB = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			String sql = "select COUNT(*)  from profiles where username=?";
			@SuppressWarnings("deprecation")
			int exist = jdbc.queryForInt(sql, username);
			if (exist > 0) {
				usernameExistInDB = true;
				String getUsername = "select username from profiles where username=?";

				Map<String, Object> userPass = jdbc.queryForMap(getUsername,
						username);
				usernameInDB = (String) userPass.get("username");
			}

			else
				usernameExistInDB = false;
			transactionManager.commit(status);
		} catch (DataAccessException e) {

			transactionManager.rollback(status);
		}
		if (usernameExistInDB) {
			return (usernameInDB.equals(username));

		} else
			return false;

	}

	public boolean usernameExistForReg(String username) {
		String sql = "select COUNT(*)  from profiles where username=?";
		@SuppressWarnings("deprecation")
		int usernameExist = jdbc.queryForInt(sql, username);
		return (usernameExist > 0);

	}

	public boolean emailExist(String email) {

		String countEmails = "select count(*) from profiles where email=?";
		@SuppressWarnings("deprecation")
		int emailExist = jdbc.queryForInt(countEmails, email);
		return (emailExist > 0);
	}

	public boolean isRegistrationAllowed(String email, String username) {

		return !(emailExist(email) || usernameExistForReg(username));
	}

	public boolean matchPassword(String username, String password) {
		String passwordDB = null;
		if (usernameExist(username)) {

			String getPasswordByUsername = "select password from profiles where username=?";
			Map<String, Object> userPass = jdbc.queryForMap(
					getPasswordByUsername, username);

			passwordDB = (String) userPass.get("password");
			return passwordDB.equals(password);
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public Person login(String username, String password) {
		int isDriver = 0;
		int isPerson = 0;

		if (matchPassword(username, password)) {

			String countDriversByUsername = "select count(*) from drivers where profileId like(select profileId from profiles where username =?)";
			isDriver = jdbc.queryForInt(countDriversByUsername, username);
			String findMatchPassenger = "select count(*) from profiles where profileId like(select profileId from profiles where username =?)";
			isPerson = jdbc.queryForInt(findMatchPassenger, username);
		}
		if (isDriver > 0) {

			String selectProfileOfDriver = "select drivers.nameOfDriver,drivers.telephone,drivers.rating,drivers.yearsInDriving,drivers.travels, drivers.SmokeInTheCar, drivers.musicInTheCar, drivers.birthYear,profiles.username, profiles.email from  "
					+ "drivers inner join profiles on drivers.profileId =profiles.profileId where drivers.profileId="
					+ "(SELECT profileId FROM profiles where username like ?)";
			Driver profile = jdbc.queryForObject(selectProfileOfDriver,
					new Object[] { username }, new ProfileDriverMapper());

			return (Person) profile;

		} else if (isPerson > 0) {

			String selectProfileOfPerson = "select passengers.name,passengers.telephone,passengers.rating,passengers.birthYear, profiles.username, profiles.email from  "
					+ "passengers inner join profiles on passengers.profileId =profiles.profileId where passengers.profileId="
					+ "(SELECT profileId FROM profiles where username like ?)";
			Passenger profile = jdbc.queryForObject(selectProfileOfPerson,
					new Object[] { username }, new ProfilePassMapper());

			return (Person) profile;
		} else
			return null;

	}

	@Override
	public void createProfile(String username, String email, String password) {
		Profile profile = new Profile();
		profile.setUsername(username);
		profile.setEmail(email);
		profile.setPassword(password);
		String insertIntoProfiles = "insert into profiles (username, email, password) values (?, ?,?)";
		jdbc.update(insertIntoProfiles, profile.getUsername(),
				profile.getEmail(), profile.getPassword());
	}

	@Override
	public void changePassword(Profile profile, String password) {
		String email = profile.getEmail();
		String changePasswordOfProfile = "Update profiles set password=? where email=?";
		jdbc.update(changePasswordOfProfile, password, email);

	}

	public String getEmailByUsername(String username) {
		if (usernameExist(username)) {
			String selectEmailByUsername = "select email from profiles where username=?";
			Map<String, Object> email = jdbc.queryForMap(selectEmailByUsername,
					username);
			return (String) email.get("email");
		}
		return null;
	}

	public String generateRandomPassword(String username) {
		Random RANDOM = new SecureRandom();
		int PASSWORD_LENGTH = 8;

		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

		String newPassword = "";
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int index = (int) (RANDOM.nextDouble() * letters.length());
			newPassword += letters.substring(index, index + 1);
		}

		// String newPassword= UUID.randomUUID().toString();
		savePasswordByUsername(username, newPassword);
		return newPassword;
	}

	private void savePasswordByUsername(String username, String newPassword) {

		String changeProfile = "update profiles set password=? where username=?";
		jdbc.update(changeProfile, newPassword, username);
	}

}
