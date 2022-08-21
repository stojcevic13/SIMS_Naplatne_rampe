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
import manage.TollStationManager;
import net.miginfocom.swing.MigLayout;
import users.TollBooth;
import users.TollStation;
import users.Worker;

public class UpdateTollStation extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2068215177557157429L;
	private ManagerFactory mngFactory;
	private TollStationManager stationMng;

	public UpdateTollStation(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.stationMng  = this.mngFactory.getStationMng();
		updateStationFrame();
	}
	
	private void updateStationFrame() {
		this.setTitle("Update Toll Station data!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		updateStationGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void updateStationGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "20[]20[]20", "[]20"));
		String[] ids = getTollStations();
		String locationIntro = getLocation(ids[0]);
		JComboBox<String> idCB = new JComboBox<String>(ids);
		JTextField locationTF = new JTextField(30);
		JLabel error = new JLabel();
		JButton submitBtn = new JButton("Update");
		
		idCB.setSelectedIndex(0);
		error.setForeground(Color.red);
		locationTF.setText(locationIntro);
		error.setVisible(false);
		error.setForeground(Color.red);
		
		panel.add(new JLabel("Station ID:"));
		panel.add(idCB, "wrap");
		panel.add(new JLabel("Location:"));
		panel.add(locationTF, "wrap");
		panel.add(error, "wrap");
		panel.add(submitBtn);

		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int stationID = Integer.valueOf(idCB.getSelectedItem().toString());
				String location = locationTF.getText();
				if(location.equals("")) {
					error.setText("Field empty. Try Again");
					error.setVisible(true);
				}else {
					TollStation station = new TollStation(stationID, location);
					stationMng.updateData(station);
					error.setText("Updated successfully.");
					error.setForeground(Color.black);
					error.setVisible(true);
					mngFactory.loadData();
				}
			}
		});
		
		idCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				locationTF.setText(getLocation(idCB.getSelectedItem().toString()));
			}
		});
	}
	
	private String getLocation(String stationId) {
		String data = null;
		if(stationMng.getTollStations() != null) {
			for(TollStation station : stationMng.getTollStations()) {
				if(String.valueOf(station.getTollStationID()).equals(stationId)) {
					data = station.getLocation();
				}
			}
		}
		return data;
	}

	private String[] getTollStations() {
		String[] data = null;
		int i = 0;
		if (stationMng.getTollStations() == null) {
			return data;
		}
		data = new String[stationMng.getTollStations().size()];
		for(TollStation tollStation : stationMng.getTollStations()) {
			data[i] = String.valueOf(tollStation.getTollStationID());
			i = i + 1;
		}
		return data;
	}


}
