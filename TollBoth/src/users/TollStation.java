package users;

import java.sql.ResultSet;

public class TollStation {

	private int tollStationID;
	private String location;
	
	public TollStation() {
		
	}
	
	public TollStation(int tollStationID, String location) {
		this.setTollStationID(tollStationID);
		this.setLocation(location);
	}

	public TollStation(String location) {
		this.setLocation(location);
	}
	
	public int getTollStationID() {
		return tollStationID;
	}

	public void setTollStationID(int tollStationID) {
		this.tollStationID = tollStationID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "TollStation [tollStationID=" + tollStationID + ", location=" + location + "]";
	}

	

}
