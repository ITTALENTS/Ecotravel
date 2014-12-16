package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jdbc.dao.DriverDAO;
import jdbc.dao.ProfileDAO;
import utils.MailSender;
import jdbc.model.Emailer;
import jdbc.model.Profile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailSender {
	private ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	private Emailer emailer = new Emailer();
	private Session session = emailer.getSession();
	private Properties props = emailer.getProps();
	private ProfileDAO profileDao = (ProfileDAO)context.getBean("profileDAO");
	String subject;
	String emailText;
	Message message;
	
	public boolean sendMessage(String username){		
			this.setSubject("Generating new Password");
			this.setEmailText("Be more careful next time! \n Your new password is: ");

			String email = profileDao.getEmailByUsername(username);
			if(email == null)
				return false;
			String newPassword = profileDao.generateRandomPassword(username);
			this.emailText = emailText + newPassword + "\nGreetings, Road Trip team!";	
			
			this. configurateAndTransportMessage(email, emailText);
			return true;
	}
	
	public void sendTripEmail(String driverUsername, String passengerUsername){		
			this.setSubject("Passengers for your trip");
			this.setEmailText(emailText = "The user with username: ");


			String email = profileDao.getEmailByUsername(driverUsername);
			
			emailText = emailText + passengerUsername + " wants to trip with you! " + 
			 "You can contact him at " + profileDao.getEmailByUsername(passengerUsername);		
			this. configurateAndTransportMessage(email, emailText);
	}
	
	private void setSubject(String subject){
		if(subject != null)
			this.subject = subject;
	}
	
	private void setEmailText(String emailText){
		if(emailText != null)
			this.emailText = emailText;
	}
	
	private void configurateAndTransportMessage(String email, String emailText){
		try{
			this.message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ittallentsproject@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(emailText);
			Transport.send(message);;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
