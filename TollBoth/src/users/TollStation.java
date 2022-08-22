package users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(location, tollStationID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TollStation other = (TollStation) obj;
		return Objects.equals(location, other.location) && tollStationID == other.tollStationID;
	}

	@Override
	public String toString() {
		return "TollStation [tollStationID=" + tollStationID + ", location=" + location + "]";
	}

	public static TollStation Parse(ResultSet result) throws SQLException {
		int tollStationID = result.getInt("TollStationID");
		String location = result.getString("location");
		
		return new TollStation(tollStationID, location);
	}

	

}
