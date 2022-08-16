package users;

public class Manager extends User {
	private int tollSystem;

	public Manager() {
	}

	public Manager(String jmbg, String firstName, String lastName, String email, String address, String gender,
			String username, String password, int tollSystem) {
		super(jmbg, firstName, lastName, email, address,  Person.Gender.valueOf(gender), username, password,  User.Role.manager);
		this.tollSystem = tollSystem;
	}
	
	
}
