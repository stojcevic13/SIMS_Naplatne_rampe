package crud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import manage.LeaderManager;
import manage.ManagerFactory;
import manage.TollStationManager;
import manage.WorkerManager;
import users.Leader;
import users.TollStation;
import users.Worker;

public class ReadLeader extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2479471685017963315L;
	private ManagerFactory mngFactory;
	private static LeaderManager leaderMng;
	private static TollStationManager stationMng;
	
	public ReadLeader(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.leaderMng  = this.mngFactory.getLeaderMng();
		this.stationMng = this.mngFactory.getStationMng();
		readWorkerFrame();
	}
	
	private void readWorkerFrame(){
		this.setTitle("Read data about the Leaders!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screensize = toolkit.getScreenSize();
		int screenHeight = screensize.height;
		int screenWidth = screensize.width;
		this.setSize(screenWidth,screenHeight/3);
		
		this.setResizable(true);
		readLeaderGUI();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void readLeaderGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		String column[]={"Username","Password","Jmbg","FirstName","LastName","Email","Address","Gender","TollStation"};
		String row[][] = showData();
		if(row == null) {
			JLabel error = new JLabel("No workers found. Add more workers so you can see their data!");
			error.setForeground(Color.red);
			panel.add(error);
		}else {
			JTable tabla = new JTable(row, column);
			JScrollPane pane = new JScrollPane(tabla);
			panel.add(pane);
		}

	}
	
	
	private static String[][] showData(){
		String[][] data = null;
		int i = 0;
		if(leaderMng.getLeaders() != null) {
			data = new String[leaderMng.getLeaders().size()][9];
			for(Leader leader : leaderMng.getLeaders()) {
				data[i][0] = leader.getUsername();
				data[i][1] = leader.getPassword();
				data[i][2] = leader.getJmbg();
				data[i][3] = leader.getFirstName();
				data[i][4] = leader.getLastName();
				data[i][5] = leader.getEmail();
				data[i][6] = leader.getAddress();
				data[i][7] = leader.getGender().name();
				data[i][8] = locationFromID(leader.getTollStation());
				i = i + 1;
			}
		}
		return data;
	}
	
	private static String locationFromID(Integer ID) {
		String i = null;
		if(ID == 0) {
			return "None";
		}
		for(TollStation tollStation : stationMng.getTollStations()) {
			if(tollStation.getTollStationID() == ID)
				i = tollStation.getLocation();
		}
		return i;

	}
}
