package users;
import java.util.Objects;

public class Person {

	public static enum Gender {
		male, female, other
	};

	private String jmbg;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private Gender gender;

	public Person() {

	}

	public Person(String jmbg, String firstName, String lastName, String email, String address, Person.Gender gender) {
		this();
		this.jmbg = jmbg;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.gender = gender;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(address, email, firstName, gender, jmbg, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && gender == other.gender
				&& Objects.equals(jmbg, other.jmbg) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "Person [jmbg=" + jmbg + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", gender=" + gender + "]";
	}

}
