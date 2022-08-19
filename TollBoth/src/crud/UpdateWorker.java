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

import manage.ManagerFactory;
import manage.TollBoothManager;
import manage.WorkerManager;
import net.miginfocom.swing.MigLayout;
import users.TollBooth;
import users.Worker;

public class UpdateWorker extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8416713796972349873L;

	private ManagerFactory mngFactory;
	private WorkerManager workerMng;
	private TollBoothManager boothMng;
	
	public UpdateWorker(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.workerMng  = this.mngFactory.getWorkMng();
		this.boothMng = this.mngFactory.getBoothMng();
		updateWorkerFrame();

	}
	
	private void updateWorkerFrame() {
		this.setTitle("Update Worker data!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		updateWorkerGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void updateWorkerGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "20[]20[]20", "[]20"));
		String[] choices = { "male","female", "other"};
		String[] usernames = getUsernames();
		String[] data = getData(usernames[0]);
		String[] tollBooths = getTollBooths();
		
		JComboBox<String> usernameCB = new JComboBox<String>(usernames);
		JTextField nameTF = new JTextField(30);
		JTextField lastNameTF = new JTextField(30);
		JTextField emailTF = new JTextField(30);
		JTextField addressTF = new JTextField(30);
		JTextField jmbgTF = new JTextField(30);
		JComboBox<String> genderCB = new JComboBox<String>(choices);
		JPasswordField passwordTF = new JPasswordField(30);
		JComboBox<String> tollBoothCB = new JComboBox<String>(tollBooths);
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
		tollBoothCB.setSelectedIndex(ArrayUtils.indexOf(tollBooths, data[7]));
		
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
		panel.add(new JLabel ("Toll Booth:"));
		panel.add(tollBoothCB, "wrap");
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
				Integer boothString;
				if(tollBoothCB.getSelectedItem().toString().equals("None")) {
					boothString = null;
				}else {
					boothString = Integer.valueOf(tollBoothCB.getSelectedItem().toString().trim());
				}
				
				if(nameString.equals("") || lastNameString.equals("") || emailString.equals("") || addressString.equals("") || jmbgString.equals("") || usernameString.equals("") || passwordString.equals("")) {
					error.setText("Input incorrect. Try again.");
					error.setForeground(Color.red);
					error.setVisible(true);
				}else {
					if(workerMng.getWorkers() != null) {
						boolean check = true;
						for(Worker worker: workerMng.getWorkers()) {
								if((worker.getJmbg().equals(jmbgString)) && (worker.getUsername().equals(usernameString) == false)) {
								error.setText("JMBG already exists. Try again.");
								error.setForeground(Color.red);
								error.setVisible(true);
								check = false;
							}
						}
						if(check == true) {
							Worker worker = new Worker(jmbgString, nameString, lastNameString, emailString, addressString, genderString, usernameString, passwordString, boothString);
							workerMng.updateData(worker);
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
				passwordTF.setText(newData[0]);
				jmbgTF.setText(newData[1]);
				nameTF.setText(newData[2]);
				lastNameTF.setText(newData[3]);
				emailTF.setText(newData[4]);
				addressTF.setText(newData[5]);
				genderCB.setSelectedIndex(ArrayUtils.indexOf(choices, newData[6]));
				tollBoothCB.setSelectedIndex(ArrayUtils.indexOf(tollBooths, newData[7]));
			}
		});
	}
	
	
	
	private String[] getUsernames() {
		String[] data = null;
		int i = 0;
		if(workerMng.getWorkers() != null) {
			data = new String[workerMng.getWorkers().size()];
			for(Worker worker : workerMng.getWorkers()) {
				data[i] = worker.getUsername();
				i = i + 1;
			}
		}
		return data;
	}
	
	
	private String[] getData(String username){
		String[] data = null;
		if(workerMng.getWorkers() != null) {
			data = new String[8];
			for(Worker worker : workerMng.getWorkers()) {
				if(username == worker.getUsername()) {
					data[0] = worker.getPassword();
					data[1] = worker.getJmbg();
					data[2] = worker.getFirstName();
					data[3] = worker.getLastName();
					data[4] = worker.getEmail();
					data[5] = worker.getAddress();
					data[6] = worker.getGender().name();
					if(worker.getTollBooth() == 0) {
						data[7] = "None";
					}else {
						data[7] = String.valueOf(worker.getTollBooth());
					}
				}
			}
		}
		return data;
	}
	
	private String[] getTollBooths() {
		String[] data = null;
		int i = 1;
		if (boothMng.getTollBooths() == null) {
			return data;
		}
		data = new String[boothMng.getTollBooths().size() + 1];
		data[0] = "None";
		for(TollBooth tollBooth : boothMng.getTollBooths()) {
			data[i] = String.valueOf(tollBooth.getTollBoothID());
			i = i + 1;
		}
		return data;
	}
}
