package users;

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
	

	
}
