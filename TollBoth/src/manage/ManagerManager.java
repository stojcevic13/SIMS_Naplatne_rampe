package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import users.Manager;
import users.Person;
import users.Worker;
import utils.AppSettings;

public class ManagerManager {
	private ArrayList<Manager> managers;
	
	public ManagerManager() {
		this.managers = new ArrayList<Manager>();
	}
	
	public ArrayList<Manager> getManagers(){
		return managers;
	}
	
	public void insertData(Manager manager) {
		String databaseURL = AppSettings.getDatabaseURL();
	
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = connection.prepareStatement("INSERT into Manager(Username, Password, Jmbg, FirstName, LastName, Email, Address, Gender, TollSystem) VALUES('"+manager.getUsername()+"','"+manager.getPassword()+"','"+manager.getJmbg()+"','"+manager.getFirstName()+"','"+manager.getLastName()+"','"+manager.getEmail()+"','"+manager.getAddress()+"','"+manager.getGender()+"','"+manager.getTollSystem()+"')");
			statement.executeUpdate();
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void loadData(ResultSet result) {
		managers.clear();
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
				
				int tollSystem = result.getInt("TollSystem");
				
				Manager tmp = new Manager(jmbg, firstName, lastName, email, adress, gender, userName, password, tollSystem);
				this.managers.add(tmp);
				UserManager.addPerson(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
