package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import users.Worker;
import utils.AppSettings;


public class WorkerManager {
	private static ArrayList<Worker> workers = new ArrayList<Worker>();
	
	public ArrayList<Worker> getWorkers(){
		return workers;
	}
	
	public void insertData(Worker worker) {
		String databaseURL = AppSettings.getDatabaseURL();
		
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = null;
			if(worker.getTollBooth() == null) {
				statement = connection.prepareStatement("INSERT into Worker(Username, Password, Jmbg, FirstName, LastName, Email, Address, Gender) VALUES('"+worker.getUsername()+"','"+worker.getPassword()+"','"+worker.getJmbg()+"','"+worker.getFirstName()+"','"+worker.getLastName()+"','"+worker.getEmail()+"','"+worker.getAddress()+"','"+worker.getGender()+"')");
			}else {
				statement = connection.prepareStatement("INSERT into Worker(Username, Password, Jmbg, FirstName, LastName, Email, Address, Gender, TollBooth) VALUES('"+worker.getUsername()+"','"+worker.getPassword()+"','"+worker.getJmbg()+"','"+worker.getFirstName()+"','"+worker.getLastName()+"','"+worker.getEmail()+"','"+worker.getAddress()+"','"+worker.getGender()+"','"+worker.getTollBooth()+"')");
				
			}
			statement.executeUpdate();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateData(Worker worker) {
		String databaseURL = AppSettings.getDatabaseURL();
		
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("UPDATE Worker SET Password = ?, Jmbg = ?, FirstName = ?, LastName = ?, Email = ?, Address = ?, Gender = ?, TollBooth = ? WHERE Username = ?");
			statement.setString(1, worker.getPassword());
			statement.setString(2, worker.getJmbg());
			statement.setString(3, worker.getFirstName());
			statement.setString(4, worker.getLastName());
			statement.setString(5, worker.getEmail());
			statement.setString(6, worker.getAddress());
			statement.setString(7, worker.getGender().toString());
			if(worker.getTollBooth() == null) {
				statement.setString(8, "0");
			}else {
				statement.setString(8, String.valueOf(worker.getTollBooth()));
			}
			statement.setString(9, worker.getUsername());
			statement.executeUpdate();
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteData(String username) {
		String databaseURL = AppSettings.getDatabaseURL();
		Connection connection;
		try {
			connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("DELETE FROM Worker WHERE Username = ?");
			statement.setString(1, username);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadData(ResultSet result) {
		workers.clear();
		try {
			while (result.next()) {
				String userName = result.getString("Username");
				String password = result.getString("Password");
				String jmbg = result.getString("Jmbg");
				String firstName = result.getString("FirstName");
				String lastName = result.getString("LastName");
				String email = result.getString("Email");
				String adress = result.getString("Address");
				String gender = result.getString("Gender");
				int tollBooth = result.getInt("TollBooth");
				
				Worker tmp = new Worker(jmbg, firstName, lastName, email, adress, gender, userName, password, tollBooth);
				this.workers.add(tmp);
				UserManager.addPerson(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Worker loadByUsername(String username) {
		String databaseURL = AppSettings.getDatabaseURL();
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Worker WHERE Username = ?");
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				return Worker.Parse(result);
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
