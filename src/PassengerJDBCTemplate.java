import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PassengerJDBCTemplate implements PassengerDAO{

	private	DataSource dataSource;
	private	JdbcTemplate jdbc;
		public void setDataSource(DataSource ds) {
			this.dataSource=ds;
			this.jdbc= new JdbcTemplate(ds);
			
		}


	@Override
	public List<AdvertismentDtls> searchAdvertisment(String from, String to,String date) {
		
		
		List<AdvertismentDtls> results = jdbc.query("select ecotravel.ads.TownFrom, ecotravel.ads.TownTo , ecotravel.ads.freePlaces,ecotravel.ads.dateOfTravel, ecotravel.profiles.email from ecotravel.ads inner join ecotravel.drivers on ecotravel.drivers.driverId=ecotravel.ads.driverId inner join ecotravel.profiles on drivers.profileId=ecotravel.profiles.profileId  where dateOfTravel=? and townFrom=? and townTo=?"
				,new Object[] { from,to,date }, new RowMapper<AdvertismentDtls>(){

					@Override
					public AdvertismentDtls mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						
						return new AdvertismentDtls(
						rs.getString("TownFrom"),
						rs.getString("TownTo"),
						rs.getInt("freePlaces"),
						rs.getString("dateOfTravel"),
						rs.getString("email"));
						
					}
					
					
				});
		
	return null;
	}



	

	@Override
	public void voteForDriver(Driver d, int vote) {
		
		
	}


	@Override
	public ProfilePass showProfile(Passenger p, String username) {

		String sql="select ecotravel.passengers.name,ecotravel.passengers.telephone,ecotravel.passengers.rating, ecotravel.profiles.username, ecotravel.profiles.email from  "
				+ "ecotravel.passengers inner join ecotravel.profiles on ecotravel.passengers.profileId =ecotravel.profiles.profileId where ecotravel.passengers.profileId="
				+ "(SELECT profileId FROM ecotravel.profiles where username like ?)";
		ProfilePass profile = jdbc.queryForObject(sql, 
                new Object[]{username}, new ProfilePassMapper());
		
		return profile;
		
	}


	@Override
	public void changeProfile(String name, String telephone, String username) {
	
			
			String SQL="update ecotravel.passengers set ecotravel.passengers.name=?, ecotravel.passengers.telephone=? where profileId = "
					+ "  (SELECT profileId FROM ecotravel.profiles where username like ?)";
			jdbc.update(SQL, name, telephone,username);
		
		
	}


	

}
