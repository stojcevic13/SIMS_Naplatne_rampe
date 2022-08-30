package users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Worker extends User {
	private Integer tollBooth;
	
	public Worker() {
		tollBooth = 0;
	}

	public Worker(String jmbg, String firstName, String lastName, String email, String address, String gender,
			String username, String password, Integer toolBooth) {
		super(jmbg, firstName, lastName, email, address, Person.Gender.valueOf(gender), username, password, User.Role.worker);
		this.setTollBooth(toolBooth);
	}

	public Integer getTollBooth() {
		return tollBooth;
	}

	public void setTollBooth(Integer toolBooth) {
		this.tollBooth = toolBooth;
	}

	public static Worker Parse(ResultSet result) throws SQLException {
		String username = result.getString("Username");
		String password = result.getString("Password");
		String jmbg = result.getString("Jmbg");
		String firstName = result.getString("FirstName");
		String lastName = result.getString("LastName");
		String email = result.getString("Email");
		String address = result.getString("Address");
		String gender = result.getString("Gender");
		int tollBooth = result.getInt("TollBooth");
		
		return new Worker(jmbg, firstName, lastName, email, address, gender, username, password, tollBooth);
	}
	

	
}
