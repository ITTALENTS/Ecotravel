package jdbc.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import jdbc.mappers.AdvertismentDriverMapper;
import jdbc.model.Addvertisment;

public class TripBetweenTownsDAO implements IAddvertismentDAO{
	private JdbcTemplate jdbc;
	
	public void setDataSource(DataSource ds) {
		this.jdbc = new JdbcTemplate(ds);

	}
	
	public List<Addvertisment> searchAdvertisment(String from, String to,
			String date) {

		return jdbc
				.query("select ads.adId,ads.TownFrom, ads.TownTo , ads.freePlaces,ads.dateOfTravel, profiles.email from ads inner join drivers on drivers.driverId=ads.driverId inner join profiles on drivers.profileId=profiles.profileId  where  townFrom=? and townTo=? and dateOfTravel=?",
						new Object[] { from, to, date },
						new AdvertismentDriverMapper());

	}
}
