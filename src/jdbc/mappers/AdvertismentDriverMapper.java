package jdbc.mappers;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jdbc.model.*;

public class AdvertismentDriverMapper implements RowMapper<Addvertisment>{

	@Override
	public Addvertisment mapRow(ResultSet rs, int nr) throws SQLException {
	Addvertisment advertisment =new Addvertisment();
	Driver driver = new Driver();
	Profile profile = new Profile();
	advertisment.setAdvertismentId(rs.getInt("adId"));
	advertisment.setDate(rs.getString("dateOfTravel"));
	advertisment.setFreePlaces(rs.getInt("freePlaces"));
	advertisment.setTravelFrom(rs.getString("TownFrom"));
	advertisment.setTravelTo(rs.getString("TownTo"));
	profile.setEmail(rs.getString("email"));
	driver.setProfile(profile);
	advertisment.setDriver(driver);
		return advertisment;
	}

}
