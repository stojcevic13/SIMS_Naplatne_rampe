package crud;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import manage.ManagerFactory;
import manage.TollBoothManager;
import manage.TollStationManager;
import net.miginfocom.swing.MigLayout;
import users.Leader;
import users.TollBooth;
import users.TollStation;

public class CreateTollBooth extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5356443022687028218L;

	private ManagerFactory mngFactory;
	private TollBoothManager boothMng;
	private TollStationManager stationMng;
	
	public CreateTollBooth(ManagerFactory mngFactory){
		this.mngFactory = mngFactory;
		this.boothMng  = this.mngFactory.getBoothMng();
		this.stationMng = this.mngFactory.getStationMng();
		createBoothFrame();
	}
	
	private void createBoothFrame() {
		this.setTitle("Create a new Toll Booth!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		createBoothGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void createBoothGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "20[]20[]20", "[]20"));
		String[] stations = getTollStations();
		
		JComboBox<String> tollStationCB = new JComboBox<String>(stations);
		JLabel error = new JLabel();
		
		error.setForeground(Color.red);
		error.setVisible(false);
		JButton submitBtn = new JButton("Submit");
		
		panel.add(new JLabel("Toll Station:"));
		panel.add(tollStationCB, "wrap");
		panel.add(error, "wrap");
		panel.add(submitBtn);

		
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer location = getIDfromLocation(tollStationCB.getSelectedItem().toString().trim());
				TollBooth station = new TollBooth(location, true, true, true);
				boothMng.insertData(station);
				error.setText("Added successfully.");
				error.setForeground(Color.black);
				error.setVisible(true);
				mngFactory.loadData();
				
			}
		});

	}
	
	private Integer getIDfromLocation(String location) {
		Integer i = 0;
		for(TollStation tollStation : stationMng.getTollStations()) {
			if(tollStation.getLocation().equals(location))
				i = tollStation.getTollStationID();
		}
		return i;
	}
	
	private String[] getTollStations() {
		String[] data;
		int i = 0;
		data = new String[stationMng.getTollStations().size()];
		for(TollStation tollStation : stationMng.getTollStations()) {
			data[i] = tollStation.getLocation();
			i = i + 1;
		}
		return data;
	}
}
