import java.util.Objects;

public class Section {
	
	private TollStation startStation;
	private TollStation endStation;
	private double distance;
	
	public Section() {

	}

	public Section(TollStation startStation, TollStation endStation, double distance) {
		this();
		this.startStation = startStation;
		this.endStation = endStation;
		this.distance = distance;
	}

	public TollStation getStartStation() {
		return startStation;
	}

	public void setStartStation(TollStation startStation) {
		this.startStation = startStation;
	}

	public TollStation getEndStation() {
		return endStation;
	}

	public void setEndStation(TollStation endStation) {
		this.endStation = endStation;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(distance, endStation, startStation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Section other = (Section) obj;
		return Double.doubleToLongBits(distance) == Double.doubleToLongBits(other.distance)
				&& Objects.equals(endStation, other.endStation) && Objects.equals(startStation, other.startStation);
	}

	@Override
	public String toString() {
		return "Section [startStation=" + startStation.getId() + ", endStation=" + endStation.getId() + ", distance=" + distance + "]";
	}
	
	
}
