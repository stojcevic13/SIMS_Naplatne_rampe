package vehicles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import manage.ManagerFactory;
import manage.PriceManager.Currency;
import users.TollStation;

public class Stats {
	private double sum;
	private int num;
	private TollStation maxStation;
	private double maxIncome;
	private Map<TollStation, Double> stations;
	
	
	public Stats(double sum, int num, TollStation station) {
		super();
		this.sum = sum;
		this.num = num;
		this.maxStation = station;
		this.maxIncome = 0;
		this.stations = null;
	}

	public Stats(double sum, int num, TollStation maxStation, double maxIncome, Map<TollStation, Double> stations) {
		super();
		this.sum = sum;
		this.num = num;
		this.maxStation = maxStation;
		this.maxIncome = maxIncome;
		this.stations = stations;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public TollStation getMaxStation() {
		return maxStation;
	}

	public void setMaxStation(TollStation maxStation) {
		this.maxStation = maxStation;
	}

	public double getMaxIncome() {
		return maxIncome;
	}

	public void setMaxIncome(double maxIncome) {
		this.maxIncome = maxIncome;
	}

	public Map<TollStation, Double> getStations() {
		return stations;
	}

	public void setStations(Map<TollStation, Double> stations) {
		this.stations = stations;
	}
	
	public static Stats getAllStats(ManagerFactory mngFactory, LocalDate startDate, LocalDate endDate) {
		ArrayList<Vehicle> vehicles = mngFactory.getVehicleMng().getVehicles();
		LocalDateTime exitTime;
		
		double sum = 0;
		int num = 0;
		Map<TollStation, Double> stations= new HashMap<TollStation, Double>();
		
		for(Vehicle vehicle : vehicles) {
			exitTime = vehicle.getExitTime();
			if((exitTime.toLocalDate().isAfter(startDate) || exitTime.toLocalDate().equals(startDate))&& (exitTime.toLocalDate().isBefore(endDate) || exitTime.toLocalDate().equals(endDate))){
				double price = mngFactory.getPriceMng().calculatePrice(vehicle.getCategory(), Currency.DIN, vehicle.getEntryStation(), vehicle.getExitStation());
				sum += price;
				num++;
				if(stations.putIfAbsent(vehicle.getExitStation(), price) != null) {
					double newPrice = stations.get(vehicle.getExitStation()) + price;
					stations.put(vehicle.getExitStation(), newPrice);
				}
			}
		}
		TollStation station = null, maxStation = null;
		Double income =  0.0;
		double maxIncome = 0.0;
		for (Map.Entry<TollStation, Double> pair : stations.entrySet()) {
		     station = pair.getKey();
		    income = pair.getValue();
		    if(income > maxIncome) {
		    	maxIncome = income;
		    	maxStation = station;
		    }
		}
		Stats stats = new Stats(sum, num, maxStation, maxIncome, stations);
		return stats;
	}
	
	public static Stats getSingleStats(ManagerFactory mngFactory, LocalDate startDate, LocalDate endDate, TollStation station) {
		ArrayList<Vehicle> vehicles = mngFactory.getVehicleMng().getVehicles();
		LocalDateTime exitTime;
		
		double sum = 0;
		int num = 0;
		
		for(Vehicle vehicle : vehicles) {
			exitTime = vehicle.getExitTime();
			if((exitTime.toLocalDate().isAfter(startDate) || exitTime.toLocalDate().equals(startDate))&& (exitTime.toLocalDate().isBefore(endDate) || exitTime.toLocalDate().equals(endDate)) && vehicle.getExitStation().equals(station)){
				double price = mngFactory.getPriceMng().calculatePrice(vehicle.getCategory(), Currency.DIN, vehicle.getEntryStation(), vehicle.getExitStation());
				sum += price;
				num++;
			}
		}
		Stats stats = new Stats(sum, num, station);
		return stats;
	}
	
}
