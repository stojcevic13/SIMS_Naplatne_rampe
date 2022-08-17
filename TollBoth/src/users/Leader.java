package users;

public class Leader extends User {
	private int tollStation;
	
	public Leader() {
		
	}

	public Leader(String jmbg, String firstName, String lastName, String email, String address, Person.Gender gender,
			String username, String password, int tollStation) {
		super(jmbg, firstName, lastName, email, address, gender, username, password, User.Role.leader);
		this.tollStation = tollStation;
	}

	public int getTollStation() {
		return tollStation;
	}

	public void setTollStation(int tollStation) {
		this.tollStation = tollStation;
	}
	
	
}
