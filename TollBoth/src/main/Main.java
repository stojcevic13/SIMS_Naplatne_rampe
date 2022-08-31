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
	
	private static LoginFrame loginFrame;

	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings("jdbc:ucanaccess://database/TollBooth.accdb");
		ManagerFactory managerFactory = new ManagerFactory(appSettings);
		managerFactory.loadData();
		setLoginFrame(new LoginFrame(managerFactory));
	}

	public static LoginFrame getLoginFrame() {
		return loginFrame;
	}

	public static void setLoginFrame(LoginFrame loginFrame) {
		Main.loginFrame = loginFrame;
	}
}

