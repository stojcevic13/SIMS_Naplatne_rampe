package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import users.TollStation;
import utils.AppSettings;

public class TollStationManager {
	private static ArrayList<TollStation> tollStations = new ArrayList<TollStation>();

	public ArrayList<TollStation> getTollStations() {
		return tollStations;
	}
	
	public void loadData(ResultSet result) {
		tollStations.clear();
		try {
			while (result.next()) {
				int tollStationID = Integer.valueOf(result.getString("TollStationID"));
				String location = result.getString("Location");
				
				TollStation tmp = new TollStation(tollStationID, location);
				this.tollStations.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertData(TollStation station) {
		String databaseURL = AppSettings.getDatabaseURL();
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = null;
			statement = connection.prepareStatement("INSERT into TollStation(Location) VALUES('"+station.getLocation()+"')");
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteData(String id) {
		String databaseURL = AppSettings.getDatabaseURL();
		Connection connection;
		try {
			connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("DELETE FROM TollStation WHERE TollStationID = ?");
			statement.setString(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void updateData(TollStation station) {
		String databaseURL = AppSettings.getDatabaseURL();
		
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("UPDATE TollStation SET Location = ? WHERE TollStationID = ?");
			statement.setString(1, station.getLocation());
			statement.setString(2, String.valueOf(station.getTollStationID()));
			statement.executeUpdate();
			connection.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
