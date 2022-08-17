/**
 * 
 */
package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import manage.ManagerFactory;
import utils.AppSettings;
import view.LoginFrame;

public class Main {
	
	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings("jdbc:ucanaccess://database/TollBooth.accdb");
		ManagerFactory managerFactory = new ManagerFactory(appSettings);
		managerFactory.loadData();
		LoginFrame loginFrame = new LoginFrame(managerFactory);
	}
}

