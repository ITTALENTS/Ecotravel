package jdbc.dao;

import java.util.List;

import javax.sql.DataSource;

import jdbc.model.Addvertisment;

public interface IAdvertismentDAO {
	public void setDataSource(DataSource ds);

	public List<Addvertisment> showMatchingAdvertisments(String from,
			String to, String date);
}
