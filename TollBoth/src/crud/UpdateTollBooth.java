package crud;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manage.ManagerFactory;
import manage.TollBoothManager;
import manage.TollStationManager;
import net.miginfocom.swing.MigLayout;
import users.TollBooth;

public class UpdateTollBooth extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1824202603759736684L;
	private ManagerFactory mngFactory;
	private TollBoothManager boothMng;
	private TollStationManager stationMng;
	
	public UpdateTollBooth(ManagerFactory mngFactory){
		this.mngFactory = mngFactory;
		this.boothMng  = this.mngFactory.getBoothMng();
		this.stationMng = this.mngFactory.getStationMng();
		updateBoothFrame();
	}
	
	private void updateBoothFrame() {
		this.setTitle("Update TollBooth!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		updateBoothGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void updateBoothGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "20[]20[]20", "[]20"));
		String[] booths = getTollBooths();
		String[] data = getData(Integer.valueOf(booths[0]));
		
		JComboBox<String> tollBoothCB = new JComboBox<String>(booths);
		JCheckBox registrationScanner = new JCheckBox();
		JCheckBox tollGate = new JCheckBox();
		JCheckBox auxilaryDevices = new JCheckBox();
		JLabel error = new JLabel();
		JButton submitBtn = new JButton("Update");
		
		tollBoothCB.setSelectedIndex(0);
		error.setForeground(Color.red);
		
		registrationScanner.setSelected(Boolean.valueOf(data[0]));
		tollGate.setSelected(Boolean.valueOf(data[1]));
		auxilaryDevices.setSelected(Boolean.valueOf(data[2]));
		error.setVisible(false);
		error.setForeground(Color.red);
		
		panel.add(new JLabel("Toll Booth:"));
		panel.add(tollBoothCB, "wrap");
		panel.add(new JLabel("Registration Scanner:"));
		panel.add(registrationScanner, "wrap");
		panel.add(new JLabel("Toll Gate:"));
		panel.add(tollGate, "wrap");
		panel.add(new JLabel("Auxilary Devices:"));
		panel.add(auxilaryDevices, "wrap");
		panel.add(error, "wrap");
		panel.add(submitBtn, "wrap");
		
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				boolean regScanner = registrationScanner.isSelected();
				boolean gate = tollGate.isSelected();
				boolean auxdev = auxilaryDevices.isSelected();
				Integer id = Integer.valueOf(tollBoothCB.getSelectedItem().toString());
				Integer tollStation = Integer.valueOf(getTollStation(id));
				TollBooth tb = new TollBooth(id, tollStation, regScanner, gate, auxdev);
				boothMng.updateData(tb);
				error.setText("Updated successfully.");
				error.setForeground(Color.black);
				error.setVisible(true);
				mngFactory.loadData();

			}
		});
		
		tollBoothCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String[] newdata = getData(Integer.valueOf(tollBoothCB.getSelectedItem().toString()));
				registrationScanner.setSelected(Boolean.valueOf(newdata[0]));
				tollGate.setSelected(Boolean.valueOf(newdata[1]));
				auxilaryDevices.setSelected(Boolean.valueOf(newdata[2]));

			}
		});
	}
	
	private String[] getTollBooths() {
		String[] data = null;
		int i = 0;
		if (boothMng.getTollBooths() == null) {
			return data;
		}
		data = new String[boothMng.getTollBooths().size()];
		for(TollBooth booth : boothMng.getTollBooths()) {
			data[i] = String.valueOf(booth.getTollBoothID());
			i = i + 1;
		}
		return data;
	}
	
	private String getTollStation(Integer ID) {
		String data = new String();
		for(TollBooth booth : boothMng.getTollBooths()) {
			if(booth.getTollBoothID() == ID) {
				return String.valueOf(booth.getTollStation());
			}
		}
		return data;

	}
	
	private String[] getData(Integer ID) {
		String[] data = new String[4];
		for(TollBooth booth : boothMng.getTollBooths()) {
			if(booth.getTollBoothID() == ID) {
				data[0] = String.valueOf(booth.isRegistrationScanner());
				data[1] = String.valueOf(booth.isTollGate());
				data[2] = String.valueOf(booth.isAuxilaryDevices());
				return data;
			}
		}
		return data;
	}
}
