import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TollSystem {

	private int id;
	private double speedLimit;
	private List<TollStation> tollStations;
	private Pricelist pricelist;
	
	public TollSystem() {
		this.tollStations = new ArrayList<TollStation>();
	}

	public TollSystem(int id, double speedLimit, List<TollStation> tollStations, Pricelist pricelist) {
		this();
		this.id = id;
		this.speedLimit = speedLimit;
		this.tollStations = tollStations;
		this.pricelist = pricelist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(double speedLimit) {
		this.speedLimit = speedLimit;
	}

	public List<TollStation> getTollStations() {
		return tollStations;
	}

	public void setTollStations(List<TollStation> tollStations) {
		this.tollStations = tollStations;
	}

	public Pricelist getPricelist() {
		return pricelist;
	}

	public void setPricelist(Pricelist pricelist) {
		this.pricelist = pricelist;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pricelist, speedLimit, tollStations);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TollSystem other = (TollSystem) obj;
		return id == other.id && Objects.equals(pricelist, other.pricelist)
				&& Double.doubleToLongBits(speedLimit) == Double.doubleToLongBits(other.speedLimit)
				&& Objects.equals(tollStations, other.tollStations);
	}

	@Override
	public String toString() {
		return "TollSystem [id=" + id + ", speedLimit=" + speedLimit + ", tollStations=" + tollStations.size() + ", pricelist="
				+ pricelist.getId() + "]";
	}
	
	
}
