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
	private ArrayList<Worker> workers;
	
	public WorkerManager() {
		this.workers = new ArrayList<Worker>();
	}
	
	public ArrayList<Worker> getWorkers(){
		return workers;
	}
	
	public void insertData(Worker worker) {
		String databaseURL = AppSettings.getDatabaseURL();
		
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("INSERT into Worker(Username, Password, Jmbg, FirstName, LastName, Email, Address, Gender, TollBooth) VALUES('"+worker.getUsername()+"','"+worker.getPassword()+"','"+worker.getJmbg()+"','"+worker.getFirstName()+"','"+worker.getLastName()+"','"+worker.getEmail()+"','"+worker.getAddress()+"','"+worker.getGender()+"','"+worker.getTollBooth()+"')");
			statement.executeUpdate();
			
			connection.close();
			
		} catch (SQLException e) {
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
	
}
