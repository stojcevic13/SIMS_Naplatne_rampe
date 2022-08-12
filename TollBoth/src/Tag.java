import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Tag {
	
	private int id;
	private double amount;
	private TollStation startStation;
	private LocalDateTime enterTime;
	private Vehicle vehicle;
	
	public Tag() {

	}

	public Tag(int id, double amount, TollStation startStation, LocalDateTime enterTime, Vehicle vehicle) {
		this();
		this.id = id;
		this.amount = amount;
		this.startStation = startStation;
		this.enterTime = enterTime;
		this.vehicle = vehicle;
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

	public TollStation getStartStation() {
		return startStation;
	}

	public void setStartStation(TollStation startStation) {
		this.startStation = startStation;
	}

	public LocalDateTime getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(LocalDateTime enterTime) {
		this.enterTime = enterTime;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, enterTime, id, startStation, vehicle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(enterTime, other.enterTime) && id == other.id
				&& Objects.equals(startStation, other.startStation) && Objects.equals(vehicle, other.vehicle);
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", amount=" + amount + ", startStation=" + startStation.getId() + ", enterTime=" + enterTime
				+ ", vehicle=" + vehicle.getRegistration() + "]";
	}

	
}
