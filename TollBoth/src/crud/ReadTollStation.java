package crud;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import manage.LeaderManager;
import manage.ManagerFactory;
import manage.TollStationManager;
import users.Leader;
import users.TollStation;

public class ReadTollStation extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5326592215851687692L;

	private ManagerFactory mngFactory;
	private static TollStationManager stationMng;
	private static LeaderManager leaderMng;
	
	public ReadTollStation(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.stationMng  = this.mngFactory.getStationMng();
		this.leaderMng = this.mngFactory.getLeaderMng();
		readStationFrame();
	}
	
	private void readStationFrame(){
		this.setTitle("Read data about the Workers!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screensize = toolkit.getScreenSize();
		int screenHeight = screensize.height;
		int screenWidth = screensize.width;
		this.setSize(screenWidth/2,screenHeight/3);
		readStationGUI();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void readStationGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		String[] column = {"StationID", "Location", "Leader"};
		String[][] data = showStationData();
		JTable tabla = new JTable(data, column);
		JScrollPane pane = new JScrollPane(tabla);
		panel.add(pane);
	}
	
	private static String[][] showStationData(){
		String[][] data = null;
		int i = 0;
		if(stationMng.getTollStations() != null) {
			data = new String[stationMng.getTollStations().size()][3];
			for(TollStation station : stationMng.getTollStations()) {
				data[i][0] = String.valueOf(station.getTollStationID());
				data[i][1] = station.getLocation();
				data[i][2] = "None";
				i = i + 1;
			}
		}
		
		if(leaderMng.getLeaders() != null) {
			for(Leader leader : leaderMng.getLeaders()) {
				for(i = 0; i < stationMng.getTollStations().size(); i++) {
					if(String.valueOf(leader.getTollStation()).equals(data[i][0])) {
						data[i][2] = leader.getFirstName() + " " + leader.getLastName();
					}
				}
			}
		}
		return data;
	}
}
