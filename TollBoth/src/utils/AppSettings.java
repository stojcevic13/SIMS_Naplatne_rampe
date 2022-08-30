package utils;

public class AppSettings {
	public static String databaseURL;
	
	public static final String DATE_TIME_FMT = "dd.MM.yyyy HH:mm:ss";
	public static final String SQL_DATE_TIME_FMT = "yyyy-MM-dd HH:mm:ss";
	
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
