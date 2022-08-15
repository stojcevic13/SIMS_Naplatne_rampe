package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import users.Leader;
import users.Manager;
import users.Person;
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
				String genderStr = result.getString("Gender");
				Person.Gender gender;
				if(genderStr == "male") {
					gender = Person.Gender.male;
				}else if(genderStr == "female") {
					gender = Person.Gender.female;
				}else{
					gender = Person.Gender.other;
				}
				
				int tollStation = result.getInt("TollStation");
				
				Leader tmp = new Leader(jmbg, firstName, lastName, email, adress, gender, userName, password, tollStation);
				this.leaders.add(tmp);
				UserManager.addPerson(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
