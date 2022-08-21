package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import users.TollBooth;
import users.Worker;
import utils.AppSettings;

public class TollBoothManager {
	private static ArrayList<TollBooth> tollBooths = new ArrayList<TollBooth>();

	public ArrayList<TollBooth> getTollBooths() {
		return tollBooths;
	}
	
	
	public void loadData(ResultSet result) {
		tollBooths.clear();
		try {
			while (result.next()) {
				int tollStation = Integer.valueOf(result.getString("TollStation"));
				int id = Integer.valueOf(result.getString("TollBoothID"));
				boolean registrationScanner = result.getBoolean("RegistrationScanner");
				boolean tollGate = result.getBoolean("TollGate");
				boolean auxilaryDevices = result.getBoolean("AuxilaryDevices");
				
				TollBooth tmp = new TollBooth(id, tollStation, registrationScanner, tollGate, auxilaryDevices);
				this.tollBooths.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertData(TollBooth booth) {
		String databaseURL = AppSettings.getDatabaseURL();
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = null;
			statement = connection.prepareStatement("INSERT into TollBooth(TollStation, RegistrationScanner, TollGate, AuxilaryDevices) VALUES('"+booth.getTollStation()+"','"+true+"','"+true+"','"+true+"')");
			statement.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	public void updateData(TollBooth booth) {
		String databaseURL = AppSettings.getDatabaseURL();
		
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("UPDATE TollBooth SET TollStation = ?, RegistrationScanner = ?, TollGate = ?, AuxilaryDevices = ? WHERE TollBoothID = ?");
			statement.setString(1, String.valueOf(booth.getTollStation()));
			statement.setString(2, String.valueOf(booth.isRegistrationScanner()));
			statement.setString(3, String.valueOf(booth.isTollGate()));
			statement.setString(4, String.valueOf(booth.isAuxilaryDevices()));
			statement.setString(5, String.valueOf(booth.getTollBoothID()));
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteData(String ID) {
		String databaseURL = AppSettings.getDatabaseURL();
		Connection connection;
		try {
			connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("DELETE FROM TollBooth WHERE TollBoothID = ?");
			statement.setString(1, ID);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TollBooth loadByID(int id) {
		String databaseURL = AppSettings.getDatabaseURL();
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM TollBooth WHERE TollBoothID = ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				return TollBooth.Parse(result);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
