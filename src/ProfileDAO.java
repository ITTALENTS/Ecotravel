import javax.sql.DataSource;


public interface ProfileDAO {
	public void setDataSource(DataSource ds);
	public void createProfile(String username, String email, String password);
	public void changePassword(Profile profile, final String password);
}
