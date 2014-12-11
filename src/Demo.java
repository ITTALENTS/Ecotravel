import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import jdbc.dao.*;

import jdbc.mappers.*;
import jdbc.model.*;


public class Demo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");

		ProfileDAO profileDAO = (ProfileDAO) context
				.getBean("profileDAO");
		// profileJDBCTemplate.createProfile("gosho","gosho@mail.bg",
		// "parolata");
		Profile p = new Profile();
		p.setEmail("gosho@mail.bg");
		p.setPassword("parolata");
		p.setUsername("gosho");
		profileDAO.changePassword(p, "125696");

		PassengerDAO passengerDAO = (PassengerDAO) context
				.getBean("passengerDAO");
		Passenger pas = new Passenger();
		pas.setName("Passenger1");
		pas.setTelephone("0896457812");
		pas.setRating(0);
		pas.setProfile(p);

		ProfilePass pp = passengerDAO.showProfile(p.getUsername());
		System.out.println(pp.getEmail());
		passengerDAO.changeProfile("Update profile", "08888888",
				"gosho", "2010-10-10");
		List<AdvertismentDtls> ads = passengerDAO.searchAdvertisment(
				"Sofia", "Pernik", "2014-12-08");
		for (AdvertismentDtls advertismentDtls : ads) {
			System.out.print("From" + advertismentDtls.getTownFrom());
			System.out.print("To" + advertismentDtls.getTownTo());
			System.out.print("Date" + advertismentDtls.getDate());
			System.out.print("Driver email : " + advertismentDtls.getEmail());
			System.out.println("Free places: "
					+ advertismentDtls.getFreePlaces());
		}
		passengerDAO.voteForDriver("gosho", 10);
		Driver d = new Driver();
		d.setProfile(p);
		DriverDAO driverDAO = (DriverDAO) context
				.getBean("driverDAO");
		ProfileDriv pd = driverDAO.showProfile("mitaka");
		System.out.println(pd.getEmail());
		Addvertisment ad = driverDAO.openAdvertisment(1, "Sofia",
				"Burgas", "2014-12-08", 5);
		driverDAO.increaseTravels("gosho");
		driverDAO.changeProfile("gosho", "New name", "0878222",
				"Techo", false,"2010-12-10");
		driverDAO.deleteAdvertisment(ad);
		System.out.println(profileDAO.usernameExist("gosho"));
		System.out.println(profileDAO.matchPassword("gosho", "125696"));
		System.out.println(profileDAO.login("mitaka", "15a2dfg").getClass().getSimpleName());
		profileDAO.createProfile("cecko", "cecko@abv.bg","15121211");
		//passengerJDBCTemplate.registerPassenger("cecko", "Cecko", "2014-12-08", "081554884");
		driverDAO.registerDriver("cecko","Cvetan", "2014-12-08", "081554884", 20, "Rock", false);

	}

}
