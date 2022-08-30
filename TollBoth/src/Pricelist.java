import java.time.LocalDate;
import java.util.Objects;

public class Pricelist {
	
	private int id;
	private double carPrice;
	private double truckPrice;
	private double motorPrice;
	private LocalDate from;
	private LocalDate until;
	
	public Pricelist() {

	}

	public Pricelist(int id, double carPrice, double truckPrice, double motorPrice, LocalDate from, LocalDate until) {
		this();
		this.id = id;
		this.carPrice = carPrice;
		this.truckPrice = truckPrice;
		this.motorPrice = motorPrice;
		this.from = from;
		this.until = until;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}

	public double getTruckPrice() {
		return truckPrice;
	}

	public void setTruckPrice(double truckPrice) {
		this.truckPrice = truckPrice;
	}

	public double getMotorPrice() {
		return motorPrice;
	}

	public void setMotorPrice(double motorPrice) {
		this.motorPrice = motorPrice;
	}

	public LocalDate getFrom() {
		return from;
	}

	public void setFrom(LocalDate from) {
		this.from = from;
	}

	public LocalDate getUntil() {
		return until;
	}

	public void setUntil(LocalDate until) {
		this.until = until;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carPrice, from, id, motorPrice, truckPrice, until);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pricelist other = (Pricelist) obj;
		return Double.doubleToLongBits(carPrice) == Double.doubleToLongBits(other.carPrice)
				&& Objects.equals(from, other.from) && id == other.id
				&& Double.doubleToLongBits(motorPrice) == Double.doubleToLongBits(other.motorPrice)
				&& Double.doubleToLongBits(truckPrice) == Double.doubleToLongBits(other.truckPrice)
				&& Objects.equals(until, other.until);
	}

	@Override
	public String toString() {
		return "Pricelist [id=" + id + ", carPrice=" + carPrice + ", truckPrice=" + truckPrice + ", motorPrice="
				+ motorPrice + ", from=" + from + ", until=" + until + "]";
	}

	
}
