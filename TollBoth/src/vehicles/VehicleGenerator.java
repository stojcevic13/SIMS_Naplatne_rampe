package vehicles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import manage.ManagerFactory;
import manage.PriceManager;
import manage.TollStationManager;
import manage.VehicleManager;
import users.TollStation;
import utils.AppSettings;

public class VehicleGenerator {
	
	public static String regNumGenerate() {
		int alpha1 = 'A' + (int)(Math.random() * ('Z' - 'A'));
		int alpha2 = 'A' + (int)(Math.random() * ('Z' - 'A'));
		int alpha3 = 'A' + (int)(Math.random() * ('Z' - 'A')); 
		int digit1 = (int)(Math.random() * 10);
		int digit2 = (int)(Math.random() * 10);
		int digit3 = (int)(Math.random() * 10);
		int digit4 = (int)(Math.random() * 10);
		return "" + (char)alpha1 + (char)alpha2 + (char)alpha3 + digit1 + digit2 + digit3 + digit4;
	}
	
	public static Timestamp dateTimeGenerate() {
		long rangebegin = Timestamp.valueOf("2021-01-01 00:00:00").getTime();
		long rangeend = Timestamp.valueOf("2022-07-07 00:00:00").getTime();
		long diff = rangeend - rangebegin + 1;
		Timestamp rand = new Timestamp(rangebegin + (long)(Math.random() * diff));
		return rand;
	}
	
// TODO: generate random tag
	public static Vehicle generateCurrent() {
		String regNum = regNumGenerate();
		PriceManager.Category category = PriceManager.Category.generateCategory();
		Random random = new Random();
		int i = random.nextInt(TollStationManager.tollStations.size());
		TollStation entryStation = TollStationManager.tollStations.get(i);
		LocalDateTime entryTime = LocalDateTime.now();
		Vehicle vehicle = new Vehicle(regNum, category, null, entryStation, entryTime);
		return vehicle;
	}
	
	public static Vehicle generateFinished() {
		String regNum = regNumGenerate();
		PriceManager.Category category = PriceManager.Category.generateCategory();
		Random random = new Random();
		int i = random.nextInt(TollStationManager.tollStations.size());
		TollStation entryStation = TollStationManager.tollStations.get(i);
		i = random.nextInt(TollStationManager.tollStations.size());
		TollStation exitStation = TollStationManager.tollStations.get(i);
		LocalDateTime exitTime = null, entryTime = null;
		Timestamp time1TS = dateTimeGenerate();
		LocalDateTime time1 = time1TS.toLocalDateTime();
		LocalDateTime time2 = time1.plusHours(5);
		if(time1.isAfter(time2)) {
			exitTime = time1;
			entryTime = time2;
		} else if(time1.isBefore(time2)) {
			exitTime = time2;
			entryTime = time1;
		}
		Vehicle vehicle = new Vehicle(regNum, category, null, entryStation, exitStation, entryTime, exitTime);
		return vehicle;
	}
	
	public static void addToDatabase(int n) {
		Vehicle vehicle = null;
		for(int i = 0; i < n; i++) {
			vehicle = generateFinished();
			VehicleManager.insertData(vehicle);
		}
	
	}
	
}

