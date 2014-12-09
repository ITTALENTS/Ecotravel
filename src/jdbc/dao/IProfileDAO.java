package jdbc.dao;

import javax.sql.DataSource;
import jdbc.model.*;

public interface IProfileDAO {
	public void setDataSource(DataSource ds);

	public void createProfile(String username, String email, String password);

	public void changePassword(Profile profile, final String password);
}
