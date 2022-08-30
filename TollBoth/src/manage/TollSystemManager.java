package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.AppSettings;

public class TollSystemManager {
	
	
	public static int getSpeedLimit() {
		//String databaseURL = AppSettings.getDatabaseURL();
		String databaseURL = "jdbc:ucanaccess://database/TollBooth.accdb";
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("SELECT SpeedLimit FROM TollSystem WHERE TollSystemID = '1'");
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				return result.getInt("SpeedLimit");
			}
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
