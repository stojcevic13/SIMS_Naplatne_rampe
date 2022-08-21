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

import manage.LeaderManager;
import manage.ManagerFactory;
import manage.TollStationManager;
import manage.UserManager;
import net.miginfocom.swing.MigLayout;
import users.Leader;
import users.TollBooth;
import users.TollStation;
import users.User;
import users.Worker;

public class CreateLeader extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8216122174381656100L;

	private ManagerFactory mngFactory;
	private LeaderManager leaderMng;
	private TollStationManager stationMng;
	private UserManager userMng;
	
	public CreateLeader(ManagerFactory mngFactory){
		this.mngFactory = mngFactory;
		this.leaderMng  = this.mngFactory.getLeaderMng();
		this.stationMng = this.mngFactory.getStationMng();
		this.userMng = this.mngFactory.getUserMng();
		createLeaderFrame();
	}

	private void createLeaderFrame() {
		this.setTitle("Create a new Leader!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		createLeaderGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void createLeaderGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "20[]20[]20", "[]20"));
		String[] choices = { "Male","Female", "Other"};
		String[] booths = getTollStations();
		
		JTextField nameTF = new JTextField(30);
		JTextField lastNameTF = new JTextField(30);
		JTextField emailTF = new JTextField(30);
		JTextField addressTF = new JTextField(30);
		JTextField jmbgTF = new JTextField(30);
		JComboBox<String> genderCB = new JComboBox<String>(choices);
		JTextField usernameTF = new JTextField(30);
		JPasswordField passwordTF = new JPasswordField(30);
		JComboBox<String> tollStationCB = new JComboBox<String>(booths);
		JButton submitBtn = new JButton("Submit");
		JLabel error = new JLabel("Input incorrect. Try again.");
		error.setForeground(Color.red);
		tollStationCB.setSelectedIndex(0);
		
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
		panel.add(new JLabel ("Username:"));
		panel.add(usernameTF, "wrap");
		panel.add(new JLabel ("Password:"));
		panel.add(passwordTF, "wrap");
		panel.add(new JLabel ("Toll Station:"));
		panel.add(tollStationCB, "wrap");
		panel.add(error, "span, align center, wrap");
		error.setVisible(false);
		panel.add(submitBtn, "span, align center");
		
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameString = nameTF.getText().trim();
				String lastNameString = lastNameTF.getText().trim();
				String emailString = emailTF.getText().trim();
				String addressString = addressTF.getText().trim();
				String jmbgString = jmbgTF.getText().trim();
				String genderString = genderCB.getSelectedItem().toString().trim().toLowerCase();
				String usernameString = usernameTF.getText().trim();
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
					if(leaderMng.getLeaders() != null) {
						boolean check = true;
						for(User user: userMng.getUsers()) {
							if(user.getUsername().equals(usernameString)) {
								error.setText("Username already exists. Try again.");
								error.setForeground(Color.red);
								error.setVisible(true);
								check = false;
							}else if(user.getJmbg().equals(jmbgString)) {
								error.setText("JMBG already exists. Try again.");
								error.setForeground(Color.red);
								error.setVisible(true);
								check = false;
							}
						}
						if(check == true) {
							Leader leader = new Leader(jmbgString, nameString, lastNameString, emailString, addressString, genderString, usernameString, passwordString, stationString);
							leaderMng.insertData(leader);
							error.setText(nameString+" added successfully.");
							error.setForeground(Color.black);
							error.setVisible(true);
							mngFactory.loadData();
						}
					}
				}
			}
		});

	}
	
	private Integer getTollStationFromLocation(String location) {
		Integer i = 0;
		for(TollStation tollStation : stationMng.getTollStations()) {
			if(tollStation.getLocation().equals(location))
				i = tollStation.getTollStationID();
		}
		return i;
	}
	
	private String[] getTollStations() {
		String[] data;
		int i = 1;
		data = new String[stationMng.getTollStations().size() + 1];
		data[0] = "None";
		if (stationMng.getTollStations().size() == 0) {
			return data;
		}
		boolean check = true;
		for(TollStation tollStation : stationMng.getTollStations()) {
			check = true;
			for(Leader leader : leaderMng.getLeaders()) {
				if(leader.getTollStation() == tollStation.getTollStationID()) {
					check = false;
				}
			}
			if(check == true) {
				i = i + 1;
			}
		}
		String[] freeStations = new String[i];
		i = 1;
		freeStations[0] = "None";
		for(TollStation tollStation : stationMng.getTollStations()) {
			check = true;
			for(Leader leader : leaderMng.getLeaders()) {
				if(leader.getTollStation() == tollStation.getTollStationID()) {
					check = false;
				}
			}
			if(check == true) {
				freeStations[i] = tollStation.getLocation();
				i = i + 1;
			}
		}
		return freeStations;
	}
}
