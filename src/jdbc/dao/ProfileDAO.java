package jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;
import javax.sql.RowSet;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jdbc.dao.*;
import jdbc.mappers.*;
import jdbc.model.*;

public class ProfileDAO<Person> implements IProfileDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbc;

	@Override
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbc = new JdbcTemplate(ds);

	}
	
	
	public boolean usernameExist(String username) {

		String sql = "select COUNT(*)  from profiles where username=?";
		int exist = jdbc.queryForInt(sql, username);
		return (exist > 0);
	}

	public boolean emailExist(String email) {

		String sql = "select count(*) from profiles where email=?";
		int emailExist = jdbc.queryForInt(sql, email);
		return (emailExist > 0);
	}

	public boolean matchPassword(String username, String password) {
		String passwordDB = null;
		if (usernameExist(username)) {

			String sql = "select password from profiles where username=?";
			Map<String, Object> userPass = jdbc.queryForMap(sql, username);

			passwordDB = (String) userPass.get("password");
			return passwordDB.equals(password);
		}
		return false;
	}
 
	public Person login(String username, String password) {
		int isDriver = 0;
		int isPerson=0;

		if (matchPassword(username, password)) {
			
			String sql = "select count(*) from drivers where profileId like(select profileId from profiles where username =?)";
			isDriver = jdbc.queryForInt(sql, username);
			String findMatchPassenger= "select count(*) from profiles where profileId like(select profileId from profiles where username =?)";
			isPerson=jdbc.queryForInt(findMatchPassenger, username);
		}
		if (isDriver > 0) {
			String sql = "select drivers.nameOfDriver,drivers.telephone,drivers.rating,drivers.yearsInDriving,drivers.travels, drivers.SmokeInTheCar, drivers.musicInTheCar, drivers.birthYear,profiles.username, profiles.email from  "
					+ "drivers inner join profiles on drivers.profileId =profiles.profileId where drivers.profileId="
					+ "(SELECT profileId FROM profiles where username like ?)";
			Driver profile = jdbc.queryForObject(sql,
					new Object[] { username }, new ProfileDriverMapper());

			return  (Person ) profile;

		} else if(isPerson>0){

			String sql = "select passengers.name,passengers.telephone,passengers.rating,passengers.birthYear, profiles.username, profiles.email from  "
					+ "passengers inner join profiles on passengers.profileId =profiles.profileId where passengers.profileId="
					+ "(SELECT profileId FROM profiles where username like ?)";
		Passenger profile = jdbc.queryForObject(sql,
					new Object[] { username }, new ProfilePassMapper());

			return  (Person) profile;
		}
		else return null;

 }
 

	@Override
	public void createProfile(String username, String email, String password) {
		Profile profile = new Profile();
		profile.setUsername(username);
		profile.setEmail(email);
		profile.setPassword(password);
		String SQL = "insert into profiles (username, email, password) values (?, ?,?)";
		jdbc.update(SQL, profile.getUsername(), profile.getEmail(),
				profile.getPassword());
	}

	@Override
	public void changePassword(Profile profile, String password) {
		String email = profile.getEmail();
		String SQL = "Update profiles set password=? where email=?";
		jdbc.update(SQL, password, email);

	}

}
