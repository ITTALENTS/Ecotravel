package jdbc.mappers;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import jdbc.model.*;

public class ProfileDriverMapper implements RowMapper<ProfileDriv>{

	@Override
	public ProfileDriv mapRow(ResultSet rs, int rn) throws SQLException {
		 ProfileDriv profile = new ProfileDriv();
	      profile.setName(rs.getString("nameOfDriver"));
	      profile.setUsername(rs.getString("username"));
	      profile.setEmail(rs.getString("email"));
	      profile.setTelephone(rs.getString("telephone"));
	      profile.setRating(rs.getInt("rating"));
	      profile.setIsSmoking(rs.getBoolean("smokeInTheCar"));
	      profile.setMusicInTheCar(rs.getString("musicInTheCar"));
	      profile.setNumberOfTravels(rs.getInt("travels"));
	      profile.setYearsInDriving(rs.getInt("yearsInDriving"));
	      return profile;
	}

}
