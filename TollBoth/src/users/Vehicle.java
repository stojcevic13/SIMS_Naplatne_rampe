package users;
import java.util.Objects;

public class Vehicle {
	
	public static enum Category {Car, Truck, Motorcycle};

	private String registration;
	private String brand;
	private Category category;
	
	public Vehicle() {

	}

	public Vehicle(String registration, String brand, Category category) {
		this();
		this.registration = registration;
		this.brand = brand;
		this.category = category;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, category, registration);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(brand, other.brand) && category == other.category
				&& Objects.equals(registration, other.registration);
	}

	@Override
	public String toString() {
		return "Vehicle [registration=" + registration + ", brand=" + brand + ", category=" + category + "]";
	}
	

}
