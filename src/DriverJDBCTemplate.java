import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class DriverJDBCTemplate implements DriverDAO{
	private	DataSource dataSource;
	private	JdbcTemplate jdbc;

	@Override
	public void openAdvertisment(String from, String to, String date,
			int freePlaces) {

Addvertisment ad= new Addvertisment();
ad.setDate(date);
ad.setFreePlaces(freePlaces);
ad.setTravelFrom(from);
ad.setTravelTo(to);


String sql= "insert into ecotravel.ads (driverId,TownFrom, TownTo,dateOfTravel, ) values(?,?,?,?) ";
//jdbc.update(sql, );
		
	}

	@Override
	public void deleteAdvertisment(Addvertisment ad) {
		String sql="delete from ecotravels.ads where adId=?";
		
	}

	@Override
	public void updateAdvertisment(Addvertisment ad) {
		String sql="update ecotravels.ads set ads.TownFrom=?, ads.TownTo=?, ads.Date=?, ads.freePlaces=? where adId=?";
		
	}

	@Override
	public void changeProfile() {
		
		
	}

	@Override
	public void showProfile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void increaseTravels() {
		
		
	}

	
	public void setDataSource(DataSource ds) {
		this.dataSource=ds;
		this.jdbc= new JdbcTemplate(ds);
		
	}



}
