package users;

public class Leader extends User {
	private Integer tollStation;
	
	public Leader() {
		
	}

	public Leader(String jmbg, String firstName, String lastName, String email, String address, String gender,
			String username, String password, Integer tollStation) {
		super(jmbg, firstName, lastName, email, address, Person.Gender.valueOf(gender), username, password, User.Role.leader);
		this.tollStation = tollStation;
	}

	public Integer getTollStation() {
		return tollStation;
	}

	public void setTollStation(Integer tollStation) {
		this.tollStation = tollStation;
	}
	
	
}
