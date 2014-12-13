package jdbc.model;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class Emailer {
	private String username;
	private String password;
	private Properties props;
	private Session session;
	
	private void init() {
		configureProperties(true, true, "smtp.gmail.com", 587);
		configureSession(getUsername(), getPassword());
	}
	
	public Properties getProps() {
		return props;
	}
	
	private void configureProperties(boolean auth, boolean starttls, String host, int port){
		this.props = new Properties();
		props.put("mail.smtp.auth", String.valueOf(auth));
		props.put("mail.smtp.starttls.enable", String.valueOf(starttls));
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", String.valueOf(port));
	}
	
	private void configureSession(final String username, final String password){
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
		}});
	}
	
	public Session getSession() {
		return session;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	
}
