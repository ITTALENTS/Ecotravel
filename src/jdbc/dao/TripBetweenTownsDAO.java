package jdbc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import jdbc.mappers.AdvertismentDriverMapper;
import jdbc.model.Addvertisment;

import java.util.Calendar;

public class TripBetweenTownsDAO implements IAdvertismentDAO {
	private JdbcTemplate jdbc;

	public void setDataSource(DataSource ds) {
		this.jdbc = new JdbcTemplate(ds);

	}

	private List<Addvertisment> searchAdvertisment(String from, String to,
			String date) {
		return jdbc
				.query("select ads.TownFrom, ads.TownTo , ads.freePlaces,ads.dateOfTravel,ads.timeOfTravel, profiles.username from ads inner join drivers on drivers.driverId=ads.driverId inner join profiles on drivers.profileId=profiles.profileId  where  TownFrom=? and TownTo=? and dateOfTravel=?",
						new Object[] { from, to, date },
						new AdvertismentDriverMapper());

	}

	public List<Addvertisment> showMatchingAdvertisments(String from,
			String to, String date) {
		List<Addvertisment> advertisments = searchAdvertisment(from, to, date);
		List<Addvertisment> activeAds = new ArrayList();
		List<Addvertisment> upToDateAds = new ArrayList<Addvertisment>();

		for (Addvertisment addvertisment : advertisments) {
			String[] dateOfTravel = addvertisment.getDate().split("(-)");
			int yearOfTravel = Integer.parseInt(dateOfTravel[0]);

			int monthOfTravel = Integer.parseInt(dateOfTravel[1]);

			int dayOfTravel = Integer.parseInt(dateOfTravel[2]);

			String[] timeOfTravel = addvertisment.getTimeOfTravel()
					.split("(:)");
			int hourAdd = Integer.parseInt(timeOfTravel[0]);

			int minutesAdd = Integer.parseInt(timeOfTravel[1]);

			Calendar rightNow = Calendar.getInstance();

			int currentYear = rightNow.get(Calendar.YEAR);

			int currentMonth = rightNow.get(Calendar.MONTH) + 1;

			int currentDay = rightNow.get(Calendar.DAY_OF_MONTH);

			int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);

			int currentMinute = rightNow.get(Calendar.MINUTE);

			if (yearOfTravel > currentYear) {
				upToDateAds.add(addvertisment);
				continue;
			} else if (yearOfTravel == currentYear) {
				if (monthOfTravel > currentMonth) {
					upToDateAds.add(addvertisment);
					continue;
				} else if (monthOfTravel == currentMonth) {

					if (dayOfTravel > currentDay) {
						upToDateAds.add(addvertisment);
						continue;
					} else if (dayOfTravel == currentDay) {
						if (hourAdd > currentHour) {
							upToDateAds.add(addvertisment);
							continue;
						} else if (hourAdd == currentHour) {
							if (minutesAdd > currentMinute)
								upToDateAds.add(addvertisment);
						}
					}
				}

			}

		}
		return upToDateAds;
	}
}
