package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.AppSettings;

public class ManagerFactory {
	private AppSettings appSettings;
	private LeaderManager leaderMng;
	private UserManager userMng;
	private ManagerManager managerMng;
	private WorkerManager workMng;
	
	
	
	public ManagerFactory(AppSettings appSettings) {
		super();
		this.appSettings = appSettings;
		this.leaderMng = new LeaderManager();
		this.userMng = new UserManager();
		this.managerMng = new ManagerManager();
		this.workMng = new WorkerManager();
	}
	public AppSettings getAppSettings() {
		return appSettings;
	}
	public void setAppSettings(AppSettings appSettings) {
		this.appSettings = appSettings;
	}
	public LeaderManager getLeaderMng() {
		return leaderMng;
	}
	public void setLeaderMng(LeaderManager leaderMng) {
		this.leaderMng = leaderMng;
	}
	public UserManager getUserMng() {
		return userMng;
	}
	public void setUsernMng(UserManager userMng) {
		this.userMng = userMng;
	}
	public ManagerManager getManagerMng() {
		return managerMng;
	}
	public void setManagerMng(ManagerManager managerMng) {
		this.managerMng = managerMng;
	}
	public WorkerManager getWorkMng() {
		return workMng;
	}
	public void setWorkMng(WorkerManager workMng) {
		this.workMng = workMng;
	}
	
	public void loadData() {
		String databaseURL = AppSettings.getDatabaseURL();
		
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM Worker";
			ResultSet result = statement.executeQuery(sql);
			this.workMng.loadData(result);
			
			sql = "SELECT * FROM Manager";
			result = statement.executeQuery(sql);
			this.managerMng.loadData(result);

			sql = "SELECT * FROM Leader";
			result = statement.executeQuery(sql);
			this.leaderMng.loadData(result);
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
