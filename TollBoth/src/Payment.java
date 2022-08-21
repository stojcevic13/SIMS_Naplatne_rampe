import java.time.LocalDateTime;
import java.util.Objects;

import users.Vehicle;

public class Payment {

	private int id;
	private double amount;
	private LocalDateTime datetime;
	private Vehicle.Category category;
	private TollStation tollStation;
	
	public Payment() {

	}

	public Payment(int id, double amount, LocalDateTime datetime, Vehicle.Category category, TollStation tollStation) {
		this();
		this.id = id;
		this.amount = amount;
		this.datetime = datetime;
		this.category = category;
		this.tollStation = tollStation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public Vehicle.Category getCategory() {
		return category;
	}

	public void setCategory(Vehicle.Category category) {
		this.category = category;
	}

	public TollStation getTollStation() {
		return tollStation;
	}

	public void setTollStation(TollStation tollStation) {
		this.tollStation = tollStation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, category, datetime, id, tollStation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && category == other.category
				&& Objects.equals(datetime, other.datetime) && id == other.id
				&& Objects.equals(tollStation, other.tollStation);
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", datetime=" + datetime + ", category=" + category
				+ ", tollStation=" + tollStation.getId() + "]";
	}
	
	
}
