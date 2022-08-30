package users;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TollSystem {

	private int id;
	private double speedLimit;
	
	public TollSystem() {

	}

	public TollSystem(int id, double speedLimit, List<TollStation> tollStations) {
		this();
		this.id = id;
		this.speedLimit = speedLimit;
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

	@Override
	public String toString() {
		return "TollSystem [id=" + id + ", speedLimit=" + speedLimit + "]";
	}
	
	
}
