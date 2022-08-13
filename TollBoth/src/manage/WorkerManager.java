package manage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import users.Worker;


public class WorkerManager {
	private ArrayList<Worker> workers;
	
	public WorkerManager() {
		this.workers = new ArrayList<Worker>();
	}
	
	public ArrayList<Worker> getWorkers(){
		return workers;
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
