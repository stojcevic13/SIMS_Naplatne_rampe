package users;

public class Section {

	private TollStation tollStation;
	private int distance;
	
	public Section() {

	}
	
	public Section(TollStation tollStation, int distance) {
		this();
		this.tollStation = tollStation;
		this.distance = distance;
	}

	public TollStation getTollStation() {
		return tollStation;
	}

	public void setTollStation(TollStation tollStation) {
		this.tollStation = tollStation;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
}
