package utils;

public class AppSettings {
	public static String databaseURL;
	
	public static final String DATE_TIME_FMT = "dd.MM.yyyy. HH:mm";
	
	public AppSettings(String databaseURL) {
		super();
		this.databaseURL = databaseURL;
	}

	public static String getDatabaseURL() {
		return databaseURL;
	}

	public static void setDatabaseURL(String databaseURL) {
		AppSettings.databaseURL = databaseURL;
	}
	
	
}
