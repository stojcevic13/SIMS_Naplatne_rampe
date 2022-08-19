package crud;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import manage.ManagerFactory;
import manage.TollBoothManager;
import manage.TollStationManager;
import manage.WorkerManager;
import users.TollBooth;
import users.TollStation;
import users.Worker;

public class ReadTollBooth extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 390705994443955060L;
	private ManagerFactory mngFactory;
	private TollBoothManager boothMng;
	private TollStationManager stationMng;
	private WorkerManager workerMng;
	
	public ReadTollBooth(ManagerFactory mngFactory){
		this.mngFactory = mngFactory;
		this.boothMng  = this.mngFactory.getBoothMng();
		this.stationMng = this.mngFactory.getStationMng();
		this.workerMng = this.mngFactory.getWorkMng();
		readBoothFrame();
	}
	
	private void readBoothFrame() {
		this.setTitle("Read data about Toll Booths!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screensize = toolkit.getScreenSize();
		int screenHeight = screensize.height;
		int screenWidth = screensize.width;
		this.setSize(screenWidth/3*2,screenHeight/2);
		readBoothGUI();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void readBoothGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		String[] column = {"StationID", "Location", "Worker", "Registration Scanner", "Toll Gate", "Auxilary Devices"};
		String[][] data = showBoothData();
		JTable tabla = new JTable(data, column);
		JScrollPane pane = new JScrollPane(tabla);
		panel.add(pane);
	}
	
	private String[][] showBoothData(){
		String[][] data = null;
		int i = 0;
		boolean duplicate = false;
		boolean hasWorker = false;
		if(boothMng.getTollBooths() != null) {
			data = new String[boothMng.getTollBooths().size()][6];
			for(TollBooth booth : boothMng.getTollBooths()) {
				data[i][0] = String.valueOf(booth.getTollBoothID());
				for(TollStation station : stationMng.getTollStations()) {
					if(booth.getTollStation() == station.getTollStationID()) {
						data[i][1] = station.getLocation();
					}
				}
				duplicate = false;
				hasWorker = false;
				for(Worker worker : workerMng.getWorkers()) {
					if(booth.getTollBoothID() == worker.getTollBooth()) {
						if(duplicate == false) {
							data[i][2] = worker.getFirstName() + " " + worker.getLastName();
							duplicate = true;
							hasWorker = true;
						}else {
							data[i][2] = data[i][2] + " / " + worker.getFirstName() + " " + worker.getLastName();
						}
					}
				}
				if(hasWorker == false) {
					data[i][2] = "None";
				}
				data[i][3] = String.valueOf(booth.isRegistrationScanner());
				data[i][4] = String.valueOf(booth.isTollGate());
				data[i][5] = String.valueOf(booth.isAuxilaryDevices());
				i = i + 1;
			}
		}
		return data;
	}
}
