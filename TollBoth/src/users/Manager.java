package users;

public class Manager extends User {
	private int tollSystem;
	
	public Manager() {
		
	}

	public Manager(String jmbg, String firstName, String lastName, String email, String address, Person.Gender gender,
			String username, String password, int tollSystem) {
		super(jmbg, firstName, lastName, email, address, gender, username, password, User.Role.manager);
		this.tollSystem = tollSystem;
	}

	public int getTollSystem() {
		return tollSystem;
	}

	public void setTollSystem(int tollSystem) {
		this.tollSystem = tollSystem;
	}
	
	
}
