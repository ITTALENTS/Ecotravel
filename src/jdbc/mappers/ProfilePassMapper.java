package jdbc.mappers;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mysql.jdbc.ResultSet;
import jdbc.model.*;

public class ProfilePassMapper implements RowMapper<ProfilePass> {

	@Override
	public ProfilePass mapRow(java.sql.ResultSet rs, int rowNum)
			throws SQLException {
		ProfilePass profile = new ProfilePass();
		profile.setName(rs.getString("name"));
		profile.setUsername(rs.getString("username"));
		profile.setEmail(rs.getString("email"));
		profile.setTelephone(rs.getString("telephone"));
		profile.setRating(rs.getInt("rating"));
		return profile;
	}

}
