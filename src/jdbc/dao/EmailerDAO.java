package jdbc.dao;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jdbc.model.Emailer;
import jdbc.model.Profile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmailerDAO {
	public void sendMessage(String username){
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Emailer emailer = (Emailer) context.getBean("Emailer");
		Session session = emailer.getSession();
		Properties props = emailer.getProps();
		
		try {
			String subject = "Generating new Password";
			String emailText = "Be more careful next time! \n Your new password is: ";
			Profile p = (Profile)context.getBean("profile");
			ProfileDAO profileDao = (ProfileDAO)context.getBean("profileDAO");
			String email = p.getEmailByUsername(username);
			emailText.compareTo(profileDao.generateRandomPassword()).concat("\nGreetings, Road Trip team!");			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ittallentsproject@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(emailText);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
