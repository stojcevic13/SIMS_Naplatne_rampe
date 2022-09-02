/**
 * 
 */
package main;

import java.io.IOException;
import java.util.ArrayList;

import manage.ManagerFactory;
import utils.AppSettings;
import vehicles.VehicleGenerator;
import view.LoginFrame;

public class Main {
	
	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings("jdbc:ucanaccess://database/TollBooth.accdb");
		ManagerFactory managerFactory = new ManagerFactory(appSettings);
		managerFactory.loadData();
		//VehicleGenerator.addToDatabase(10);
		LoginFrame loginFrame = new LoginFrame(managerFactory);
	}
}

