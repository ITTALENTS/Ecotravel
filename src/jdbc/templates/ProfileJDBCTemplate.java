package jdbc.templates;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import jdbc.dao.*;
import jdbc.mappers.*;
import jdbc.model.*;

public class ProfileJDBCTemplate implements ProfileDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbc;

	@Override
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbc = new JdbcTemplate(ds);

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
