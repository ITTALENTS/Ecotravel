package jdbc.dao;
import java.util.List;

import javax.sql.DataSource;

import jdbc.model.Addvertisment;


public interface IAddvertismentDAO {
	public void setDataSource(DataSource ds);
	
	public List<Addvertisment> showActiveAdvertisments(String from, String to,
			String date);
}
