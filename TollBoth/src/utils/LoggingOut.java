package utils;

import javax.swing.JFrame;

import main.Main;

public class LoggingOut {
	public static void logOut(JFrame parent) {
		parent.setVisible(false);
		parent.dispose();
		Main.getLoginFrame().getUserName().setText("");
		Main.getLoginFrame().getPassword().setText("");
		Main.getLoginFrame().setVisible(true);
	}
}
