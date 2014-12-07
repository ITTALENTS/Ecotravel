import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Demo {

	public static void main(String[] args) {
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("Beans.xml");

	      ProfileJDBCTemplate profileJDBCTemplate = 
	      (ProfileJDBCTemplate)context.getBean("profileJDBCTemplate");
	     // profileJDBCTemplate.createProfile("gosho","gosho@mail.bg", "parolata");
	      Profile p= new Profile();
	      p.setEmail("gosho@mail.bg");
	      p.setPassword("parolata");
	      p.setUsername("gosho");
	      profileJDBCTemplate.changePassword(p, "125696");
	      
	      PassengerJDBCTemplate passengerJDBCTemplate = 
	    	      (PassengerJDBCTemplate)context.getBean("passengerJDBCTemplate");
	      Passenger pas= new Passenger();
	      pas.setName("Passenger1");
	      pas.setTelephone("0896457812");
	      pas.setRating(0);
	      pas.setProfile(p);
	      passengerJDBCTemplate.showProfile(pas, p.getUsername());
	      passengerJDBCTemplate.changeProfile("Update profile", "08888888", "gosho");
	      passengerJDBCTemplate.searchAdvertisment("Sofia", "Pernik", "2014-12-08 12:00:00");
	      
	}

}
