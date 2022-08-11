
public class Manager extends User {

	public Manager() {
		
	}

	public Manager(String jmbg, String firstName, String lastName, String email, String address, Person.Gender gender,
			String username, String password, User.Role role) {
		super(jmbg, firstName, lastName, email, address, gender, username, password, role);
	}
	
	
}
