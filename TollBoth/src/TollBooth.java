import java.util.Objects;

import users.Worker;

public class TollBooth {

	private int number;
	private boolean registrationScanner;
	private boolean tollGate;
	private boolean auxilaryDevices;
	private TollStation station;
	private Worker worker;
	
	public TollBooth() {
		
	}

	public TollBooth(int number, boolean registrationScanner, boolean tollGate, boolean auxilaryDevices,
			TollStation station, Worker worker) {
		this();
		this.number = number;
		this.registrationScanner = registrationScanner;
		this.tollGate = tollGate;
		this.auxilaryDevices = auxilaryDevices;
		this.station = station;
		this.worker = worker;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public TollStation getStation() {
		return station;
	}

	public void setStation(TollStation station) {
		this.station = station;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@Override
	public int hashCode() {
		return Objects.hash(auxilaryDevices, number, registrationScanner, station, tollGate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TollBooth other = (TollBooth) obj;
		return auxilaryDevices == other.auxilaryDevices && number == other.number
				&& registrationScanner == other.registrationScanner && Objects.equals(station, other.station)
				&& tollGate == other.tollGate;
	}

	@Override
	public String toString() {
		return "TollBooth [number=" + number + ", registrationScanner=" + registrationScanner + ", tollGate=" + tollGate
				+ ", auxilaryDevices=" + auxilaryDevices + ", station=" + station.getId() + "worker=" + worker.getJmbg() + "]";
	}
	
	
}
