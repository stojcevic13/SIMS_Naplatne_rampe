package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import users.TollBooth;
import users.TollStation;
import utils.AppSettings;

public class SectionManager {
	
	public static int getDistance(TollStation beginStation, TollStation endStation) {
//		String databaseURL = AppSettings.getDatabaseURL();
		String databaseURL = "jdbc:ucanaccess://database/TollBooth.accdb";
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
		
			PreparedStatement statement = connection.prepareStatement("SELECT Distance FROM Section WHERE TollStation = ? or TollStation = ?");
			statement.setInt(1, beginStation.getTollStationID());
			statement.setInt(2, endStation.getTollStationID());
			ResultSet result = statement.executeQuery();
			
			result.next();
			int beginDistance = result.getInt("Distance");
			result.next();
			int endDistance = result.getInt("Distance");
			connection.close();
			
			return Math.abs(endDistance - beginDistance);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	public static void main(String[] args) {
		TollStation s1 = new TollStation(16, "sas");
		TollStation s2 = new TollStation(18, "sas");
		int distance = getDistance(s1, s2);
		System.out.println(distance);
	}


}
