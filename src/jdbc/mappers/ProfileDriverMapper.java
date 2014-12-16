package jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import jdbc.model.*;

public class ProfileDriverMapper implements RowMapper<Driver> {

	@Override
	public Driver mapRow(ResultSet rs, int rn) throws SQLException {
		
		Driver profile = new Driver();		
		Profile profileOfDriver= new Profile();
		profile.setName(rs.getString("nameOfDriver"));
		profileOfDriver.setEmail(rs.getString("email"));
		profileOfDriver.setUsername(rs.getString("username"));	
		profile.setProfile(profileOfDriver);		
		profile.setTelephone(rs.getString("telephone"));
		profile.setRating(rs.getInt("rating"));	
		profile.setIsSmoking(rs.getBoolean("smokeInTheCar"));
		profile.setMusicInTheCar(rs.getString("musicInTheCar"));
		profile.setNumberOfTravels(rs.getInt("travels"));
		profile.setLicenseYear(rs.getInt("yearsInDriving"));
		profile.setBirthYear(rs.getInt("birthYear"));
		return profile;
	}

}
