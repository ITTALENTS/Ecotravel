import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import jdbc.dao.*;

import jdbc.mappers.*;
import jdbc.model.*;
import jdbc.templates.*;

public class Demo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");

		ProfileJDBCTemplate profileJDBCTemplate = (ProfileJDBCTemplate) context
				.getBean("profileJDBCTemplate");
		// profileJDBCTemplate.createProfile("gosho","gosho@mail.bg",
		// "parolata");
		Profile p = new Profile();
		p.setEmail("gosho@mail.bg");
		p.setPassword("parolata");
		p.setUsername("gosho");
		profileJDBCTemplate.changePassword(p, "125696");

		PassengerJDBCTemplate passengerJDBCTemplate = (PassengerJDBCTemplate) context
				.getBean("passengerJDBCTemplate");
		Passenger pas = new Passenger();
		pas.setName("Passenger1");
		pas.setTelephone("0896457812");
		pas.setRating(0);
		pas.setProfile(p);

		ProfilePass pp = passengerJDBCTemplate.showProfile(p.getUsername());
		System.out.println(pp.getEmail());
		passengerJDBCTemplate.changeProfile("Update profile", "08888888",
				"gosho");
		List<AdvertismentDtls> ads = passengerJDBCTemplate.searchAdvertisment(
				"Sofia", "Pernik", "2014-12-08");
		for (AdvertismentDtls advertismentDtls : ads) {
			System.out.print("From" + advertismentDtls.getTownFrom());
			System.out.print("To" + advertismentDtls.getTownTo());
			System.out.print("Date" + advertismentDtls.getDate());
			System.out.print("Driver email : " + advertismentDtls.getEmail());
			System.out.println("Free places: "
					+ advertismentDtls.getFreePlaces());
		}
		passengerJDBCTemplate.voteForDriver("gosho", 10);
		Driver d = new Driver();
		d.setProfile(p);
		DriverJDBCTemplate driverJDBCTemplate = (DriverJDBCTemplate) context
				.getBean("driverJDBCTemplate");
		ProfileDriv pd = driverJDBCTemplate.showProfile("mitaka");
		System.out.println(pd.getEmail());
		Addvertisment ad = driverJDBCTemplate.openAdvertisment(1, "Sofia",
				"Burgas", "2014-12-08", 5);
		driverJDBCTemplate.increaseTravels("gosho");
		driverJDBCTemplate.changeProfile("gosho", "New name", "0878222",
				"Techo", false);
		driverJDBCTemplate.deleteAdvertisment(ad);

	}

}
