package vehicles;

import java.time.LocalDateTime;
import java.util.Date;

import manage.PriceManager;
import manage.PriceManager.Category;
import users.TollStation;

public class Vehicle {
	private String registrationNum;
	private PriceManager.Category category;
	private TAG tag;
	private TollStation entryStation;
	private TollStation exitStation;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	
	public Vehicle(String registrationNum, Category category, TAG tag, TollStation entryStation,
			LocalDateTime entryTime) {
		super();
		this.registrationNum = registrationNum;
		this.category = category;
		this.tag = tag;
		this.entryStation = entryStation;
		this.entryTime = entryTime;
		this.exitTime = null;
		this.exitStation = null;
	}
	
	
	
	public Vehicle(String registrationNum, Category category, TAG tag, TollStation entryStation,
			TollStation exitStation, LocalDateTime entryTime, LocalDateTime exitTime) {
		super();
		this.registrationNum = registrationNum;
		this.category = category;
		this.tag = tag;
		this.entryStation = entryStation;
		this.exitStation = exitStation;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
	}



	public String getRegistrationNum() {
		return registrationNum;
	}
	public void setRegistrationNum(String registrationNum) {
		this.registrationNum = registrationNum;
	}
	public PriceManager.Category getCategory() {
		return category;
	}
	public void setCategory(PriceManager.Category category) {
		this.category = category;
	}
	public TAG getTag() {
		return tag;
	}
	public void setTag(TAG tag) {
		this.tag = tag;
	}
	public TollStation getEntryStation() {
		return entryStation;
	}
	public void setEntryStation(TollStation entryStation) {
		this.entryStation = entryStation;
	}
	public TollStation getExitStation() {
		return exitStation;
	}
	public void setExitStation(TollStation exitStation) {
		this.exitStation = exitStation;
	}
	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}
	public LocalDateTime getExitTime() {
		return exitTime;
	}
	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}
	
	
}
