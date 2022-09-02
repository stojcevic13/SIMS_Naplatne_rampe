package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import users.Leader;
import users.TollStation;
import utils.AppSettings;
import vehicles.TAG;
import vehicles.Vehicle;

public class VehicleManager {
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Vehicle> currentVehicles;
	
	public VehicleManager() {
		this.vehicles = new ArrayList<Vehicle>();
		this.currentVehicles = new ArrayList<Vehicle>();
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public ArrayList<Vehicle> getCurrentVehicles() {
		return currentVehicles;
	}
	public void setCurrentVehicles(ArrayList<Vehicle> currentVehicles) {
		this.currentVehicles = currentVehicles;
	}

	public void loadData(ResultSet result) {
		vehicles.clear();
		currentVehicles.clear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		try {
			while (result.next()) {
				String regNum = result.getString("RegistrationNum");
				String categoryS = result.getString("Category");
				int tagS = result.getInt("TAG");
				TAG tag;
				if(tagS == -1) {
					tag = null;
				}else {
					tag = null;
				}
				int entryStationS = result.getInt("EntryStation");
				int exitStationS = result.getInt("ExitStation");
				String entryTimeS = result.getString("EntryTime");
				String exitTimeS = result.getString("ExitTime");
				
				PriceManager.Category category = PriceManager.Category.valueOf(categoryS); 
				LocalDateTime entryTime = LocalDateTime.parse(entryTimeS, formatter);
				LocalDateTime exitTime = null;
				TollStation exitStation = null;
				if(exitTimeS != "null") {
					exitTime = LocalDateTime.parse(exitTimeS, formatter);
				}
				TollStation entryStation = TollStationManager.findTollStation(entryStationS);
				if(exitStationS != -1) {
					exitStation = TollStationManager.findTollStation(exitStationS);
				}
				Vehicle tmp = new Vehicle(regNum, category, tag, entryStation, exitStation, entryTime, exitTime);
				this.vehicles.add(tmp);
				if(exitTimeS != "null") {
					this.currentVehicles.add(tmp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertData(Vehicle vehicle) {
		String databaseURL = AppSettings.getDatabaseURL();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		int tag, exitStation;
		String exitTime;
		if(vehicle.getTag() == null) {
			tag = -1;
		}else {
			tag = vehicle.getTag().getID();
		}
		if(vehicle.getExitStation() == null) {
			exitStation = -1;
		}else {
			exitStation = vehicle.getExitStation().getTollStationID();
		}
		if(vehicle.getExitTime() == null) {
			
		}
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			
			PreparedStatement statement = null;
			
			statement = connection.prepareStatement("INSERT into Vehicle(RegistrationNum, Category, TAG, EntryStation, ExitStation, EntryTime, ExitTime) VALUES('"+vehicle.getRegistrationNum()+"','"+vehicle.getCategory().toString()+"','"+tag+"','"+vehicle.getEntryStation().getTollStationID()+"','"+vehicle.getExitStation().getTollStationID()+"','"+vehicle.getEntryTime().format(formatter)+"','"+vehicle.getExitTime().format(formatter)+"')");
			statement.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


