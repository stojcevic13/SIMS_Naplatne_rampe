package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import users.Leader;
import utils.AppSettings;

public class LeaderManager {
	private ArrayList<Leader> leaders;
	
	public LeaderManager() {
		this.leaders = new ArrayList<Leader>();
	}

	public ArrayList<Leader> getLeaders() {
		return leaders;
	}
	
	public void insertData(Leader leader) {
		String databaseURL = AppSettings.getDatabaseURL();
		
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("INSERT into Leader(Username, Password, Jmbg, FirstName, LastName, Email, Address, Gender, TollStation) VALUES('"+leader.getUsername()+"','"+leader.getPassword()+"','"+leader.getJmbg()+"','"+leader.getFirstName()+"','"+leader.getLastName()+"','"+leader.getEmail()+"','"+leader.getAddress()+"','"+leader.getGender()+"','"+leader.getTollStation()+"')");
			statement.executeUpdate();
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void loadData(ResultSet result) {
		leaders.clear();
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
				
				Integer tollStation = result.getInt("TollStation");
				
				Leader tmp = new Leader(jmbg, firstName, lastName, email, adress, gender, userName, password, tollStation);
				this.leaders.add(tmp);
				UserManager.addPerson(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateData(Leader leader) {
		String databaseURL = AppSettings.getDatabaseURL();
		
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("UPDATE Leader SET Password = ?, Jmbg = ?, FirstName = ?, LastName = ?, Email = ?, Address = ?, Gender = ?, TollStation = ? WHERE Username = ?");
			statement.setString(1, leader.getPassword());
			statement.setString(2, leader.getJmbg());
			statement.setString(3, leader.getFirstName());
			statement.setString(4, leader.getLastName());
			statement.setString(5, leader.getEmail());
			statement.setString(6, leader.getAddress());
			statement.setString(7, leader.getGender().toString());
			if(leader.getTollStation() == null) {
				statement.setString(8, "0");
			}else {
				statement.setString(8, String.valueOf(leader.getTollStation()));
			}
			statement.setString(9, leader.getUsername());
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
			PreparedStatement statement = connection.prepareStatement("DELETE FROM Leader WHERE Username = ?");
			statement.setString(1, username);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
