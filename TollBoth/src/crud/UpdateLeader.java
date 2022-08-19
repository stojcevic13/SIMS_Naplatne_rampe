package crud;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.lang3.ArrayUtils;

import manage.LeaderManager;
import manage.ManagerFactory;
import manage.TollStationManager;
import net.miginfocom.swing.MigLayout;
import users.Leader;
import users.TollStation;
import users.Worker;

public class UpdateLeader extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6200774035966485298L;
	
	private ManagerFactory mngFactory;
	private LeaderManager leaderMng;
	private TollStationManager stationMng;
	
	public UpdateLeader(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.leaderMng  = this.mngFactory.getLeaderMng();
		this.stationMng = this.mngFactory.getStationMng();
		updateLeaderFrame();
	}

	private void updateLeaderFrame() {
		this.setTitle("Update Leader data!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		updateLeaderGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void updateLeaderGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "20[]20[]20", "[]20"));
		String[] choices = { "male","female", "other"};
		String[] usernames = getUsernames();
		String[] data = getData(usernames[0]);
		String[] tollStations = getTollStations(usernames[0]);

		JComboBox<String> usernameCB = new JComboBox<String>(usernames);
		JTextField nameTF = new JTextField(30);
		JTextField lastNameTF = new JTextField(30);
		JTextField emailTF = new JTextField(30);
		JTextField addressTF = new JTextField(30);
		JTextField jmbgTF = new JTextField(30);
		JComboBox<String> genderCB = new JComboBox<String>(choices);
		JPasswordField passwordTF = new JPasswordField(30);
		JComboBox<String> tollStationCB = new JComboBox<String>(tollStations);
		JButton submitBtn = new JButton("Update");
		JLabel error = new JLabel("Input incorrect. Try again.");

		error.setForeground(Color.red);
		usernameCB.setSelectedIndex(0);
		passwordTF.setText(data[0]);
		jmbgTF.setText(data[1]);
		nameTF.setText(data[2]);
		lastNameTF.setText(data[3]);
		emailTF.setText(data[4]);
		addressTF.setText(data[5]);
		genderCB.setSelectedIndex(ArrayUtils.indexOf(choices, data[6]));
		tollStationCB.setSelectedIndex(ArrayUtils.indexOf(tollStations, getLocationFromID(data[7])));

		
		panel.add(new JLabel ("Username:"));
		panel.add(usernameCB, "wrap");
		panel.add(new JLabel ("First name:"));
		panel.add(nameTF, "wrap");
		panel.add(new JLabel ("Last name:"));
		panel.add(lastNameTF, "wrap");
		panel.add(new JLabel ("Email:"));
		panel.add(emailTF, "wrap");
		panel.add(new JLabel ("Address:"));
		panel.add(addressTF, "wrap");
		panel.add(new JLabel ("JMBG:"));
		panel.add(jmbgTF, "wrap");
		panel.add(new JLabel ("Gender:"));
		panel.add(genderCB, "wrap");
		panel.add(new JLabel ("Password:"));
		panel.add(passwordTF, "wrap");
		panel.add(new JLabel ("Toll Station:"));
		panel.add(tollStationCB, "wrap");
		panel.add(error, "span, align center, wrap");
		error.setVisible(false);
		panel.add(submitBtn, "span, align center");
		
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String nameString = nameTF.getText().trim();
				String lastNameString = lastNameTF.getText().trim();
				String emailString = emailTF.getText().trim();
				String addressString = addressTF.getText().trim();
				String jmbgString = jmbgTF.getText().trim();
				String genderString = genderCB.getSelectedItem().toString().trim().toLowerCase();
				String usernameString = usernameCB.getSelectedItem().toString().trim();
				String passwordString = new String(passwordTF.getPassword()).trim();
				Integer stationString;
				if(tollStationCB.getSelectedItem().toString().equals("None")) {
					stationString = null;
				}else {
					stationString = getTollStationFromLocation(tollStationCB.getSelectedItem().toString());
				}
				
				if(nameString.equals("") || lastNameString.equals("") || emailString.equals("") || addressString.equals("") || jmbgString.equals("") || usernameString.equals("") || passwordString.equals("")) {
					error.setText("Input incorrect. Try again.");
					error.setForeground(Color.red);
					error.setVisible(true);
				}else {
					if(stationMng.getTollStations() != null) {
						boolean check = true;
						for(Leader leader: leaderMng.getLeaders()) {
								if((leader.getJmbg().equals(jmbgString)) && (leader.getUsername().equals(usernameString) == false)) {
								error.setText("JMBG already exists. Try again.");
								error.setForeground(Color.red);
								error.setVisible(true);
								check = false;
							}
						}
						if(check == true) {
							Leader leader = new Leader(jmbgString, nameString, lastNameString, emailString, addressString, genderString, usernameString, passwordString, stationString);
							leaderMng.updateData(leader);
							error.setText(nameString+" updated successfully.");
							error.setForeground(Color.black);
							error.setVisible(true);
							mngFactory.loadData();
						}
					}
				}
			}
		});
		
		
		usernameCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String[] newData = getData(usernameCB.getSelectedItem().toString());
				String[] newStations = getTollStations(usernameCB.getSelectedItem().toString());
				passwordTF.setText(newData[0]);
				jmbgTF.setText(newData[1]);
				nameTF.setText(newData[2]);
				lastNameTF.setText(newData[3]);
				emailTF.setText(newData[4]);
				addressTF.setText(newData[5]);
				genderCB.setSelectedIndex(ArrayUtils.indexOf(choices, newData[6]));
				tollStationCB.removeAllItems();
				for(String newlocation : newStations) {
					tollStationCB.addItem(newlocation);
				}
				tollStationCB.setSelectedIndex(ArrayUtils.indexOf(newStations, getLocationFromID(newData[7])));
			}
		});

	}
	
	private String getLocationFromID(String ID) {
		String i = null;
		if(ID == "None") {
			return "None";
		}
		for(TollStation tollStation : stationMng.getTollStations()) {
			if(tollStation.getTollStationID() == Integer.valueOf(ID))
				i = tollStation.getLocation();
		}
		return i;

	}
	
	private Integer getTollStationFromLocation(String location) {
		Integer i = 0;
		for(TollStation tollStation : stationMng.getTollStations()) {
			if(tollStation.getLocation().equals(location))
				i = tollStation.getTollStationID();
		}
		return i;
	}
	
	private String[] getUsernames() {
		String[] data = null;
		int i = 0;
		if(leaderMng.getLeaders() != null) {
			data = new String[leaderMng.getLeaders().size()];
			for(Leader leader : leaderMng.getLeaders()) {
				data[i] = leader.getUsername();
				i = i + 1;
			}
		}
		return data;

	}
	
	private String[] getData(String username) {
		String[] data = null;
		if(leaderMng.getLeaders() != null) {
			data = new String[8];
			for(Leader leader : leaderMng.getLeaders()) {
				if(username == leader.getUsername()) {
					data[0] = leader.getPassword();
					data[1] = leader.getJmbg();
					data[2] = leader.getFirstName();
					data[3] = leader.getLastName();
					data[4] = leader.getEmail();
					data[5] = leader.getAddress();
					data[6] = leader.getGender().name();
					if(leader.getTollStation() == 0) {
						data[7] = "None";
					}else {
						data[7] = String.valueOf(leader.getTollStation());
					}
				}
			}
		}
		return data;
	}
	
	private String[] getTollStations(String username) {
		String[] data = null;
		int i = 1;
		if (stationMng.getTollStations() == null) {
			return data;
		}
		boolean used;
		
		for(TollStation tollStation : stationMng.getTollStations()) {
			used = false;
			for(Leader leader : leaderMng.getLeaders()) {
				if(leader.getTollStation() == tollStation.getTollStationID() && leader.getUsername() != username) {
					used = true;
				}
			}
			if(used == false) {
				i = i + 1;
			}
		}
		data = new String[i];
		data[0] = "None";
		i = 1;
		for(TollStation tollStation : stationMng.getTollStations()) {
			used = false;
			for(Leader leader : leaderMng.getLeaders()) {
				if(leader.getTollStation() == tollStation.getTollStationID() && leader.getUsername().equals(username) != true) {
					used = true;
				}
			}
			if(used == false) {
				data[i] = String.valueOf(tollStation.getLocation());
				i = i + 1;
			}	
		}
		return data;
	}
}
