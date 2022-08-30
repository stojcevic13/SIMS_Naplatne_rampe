package users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TollBooth {

	private int tollBoothID;
	private int tollStation;
	private boolean registrationScanner;
	private boolean tollGate;
	private boolean auxilaryDevices;
	
	public TollBooth() {
		
	}
	
	public TollBooth(int tollBoothID, int tollStation, boolean registrationScanner, boolean tollGate, boolean auxilaryDevices) {
		this.tollBoothID = tollBoothID;
		this.tollStation = tollStation;
		this.registrationScanner = registrationScanner;
		this.tollGate = tollGate;
		this.auxilaryDevices = auxilaryDevices;
	}
	
	public TollBooth(int tollStation, boolean registrationScanner, boolean tollGate, boolean auxilaryDevices) {
		this.tollStation = tollStation;
		this.registrationScanner = registrationScanner;
		this.tollGate = tollGate;
		this.auxilaryDevices = auxilaryDevices;
	}

	public int getTollBoothID() {
		return tollBoothID;
	}

	public void setTollBoothID(int tollBoothID) {
		this.tollBoothID = tollBoothID;
	}

	public int getTollStation() {
		return tollStation;
	}

	public void setTollStation(int tollStation) {
		this.tollStation = tollStation;
	}

	public boolean isRegistrationScanner() {
		return registrationScanner;
	}

	public void setRegistrationScanner(boolean registrationScanner) {
		this.registrationScanner = registrationScanner;
	}

	public boolean isTollGate() {
		return tollGate;
	}

	public void setTollGate(boolean tollGate) {
		this.tollGate = tollGate;
	}
	
	public boolean isAuxilaryDevices() {
		return auxilaryDevices;
	}
	
	public void setAuxilaryDevices(boolean auxilaryDevices) {
		this.auxilaryDevices = auxilaryDevices;
	}
	
	public static TollBooth Parse(ResultSet result) throws SQLException {
		int tollBoothID = result.getInt("TollBoothID");
		int tollStation = result.getInt("TollStation");
		boolean registrationScanner = result.getBoolean("RegistrationScanner");
		boolean tollGate = result.getBoolean("TollGate");
		boolean AuxilaryDevices = result.getBoolean("AuxilaryDevices");
		
		return new TollBooth(tollBoothID, tollStation, registrationScanner, tollGate, AuxilaryDevices);
	}
	
	public boolean isWorking() {
		return this.registrationScanner && this.auxilaryDevices && this.tollGate;
	}
	
	
}
