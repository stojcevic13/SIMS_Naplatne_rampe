package users;

public class Worker extends User {
	private int toolBooth;
	
	public Worker() {
		
	}

	public Worker(String jmbg, String firstName, String lastName, String email, String address, String gender,
			String username, String password, int toolBooth) {
		super(jmbg, firstName, lastName, email, address, Person.Gender.valueOf(gender), username, password, User.Role.worker);
		this.toolBooth = toolBooth;
	}
	

	
}
