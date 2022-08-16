package manage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import users.Manager;
import users.Worker;


public class ManagerManager {
	private ArrayList<Manager> managers;
	
	public ManagerManager() {
		this.managers = new ArrayList<Manager>();
	}
	
	public ArrayList<Manager> getManagers(){
		return managers;
	}
	
	public void loadData(ResultSet result) {
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
