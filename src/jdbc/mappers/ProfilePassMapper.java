package jdbc.mappers;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mysql.jdbc.ResultSet;
import jdbc.model.*;

public class ProfilePassMapper implements RowMapper<Passenger> {

	@Override
	public Passenger mapRow(java.sql.ResultSet rs, int rowNum)
			throws SQLException {
		Passenger profile = new Passenger();
		Profile profileOfPassenger= new Profile();
		profile.setName(rs.getString("name"));
		//profile.setUsername(rs.getString("username"));
		//profile.setEmail(rs.getString("email"));
		profileOfPassenger.setUsername(rs.getString("username"));
		profileOfPassenger.setEmail(rs.getString("email"));
		profile.setProfile(profileOfPassenger);
		profile.setTelephone(rs.getString("telephone"));
		
		profile.setRating(rs.getInt("rating"));
		profile.setBirthYear(rs.getInt("birthYear"));
		return profile;
	}

}
