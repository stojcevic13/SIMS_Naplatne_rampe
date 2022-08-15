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
import manage.ManagerFactory;
import manage.WorkerManager;
import net.miginfocom.swing.MigLayout;
import users.Worker;

public class CreateWorker extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ManagerFactory mngFactory;
	private WorkerManager workerMng;
	
	public CreateWorker(ManagerFactory mngFactory){
		this.mngFactory = mngFactory;
		this.workerMng  = this.mngFactory.getWorkMng();
		createWorkerFrame();
	}
	
	private void createWorkerFrame() {
		this.setTitle("Create a new Worker!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		createWorkerGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void createWorkerGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "20[]20[]20", "[]20"));
		String[] choices = { "Male","Female", "Other"};
		
		JTextField nameTF = new JTextField(30);
		JTextField lastNameTF = new JTextField(30);
		JTextField emailTF = new JTextField(30);
		JTextField addressTF = new JTextField(30);
		JTextField jmbgTF = new JTextField(30);
		JComboBox<String> genderCB = new JComboBox<String>(choices);
		JTextField usernameTF = new JTextField(30);
		JPasswordField passwordTF = new JPasswordField(30);
		JButton submitBtn = new JButton("Submit");
		JLabel error = new JLabel("Input incorrect. Try again.");
		error.setForeground(Color.red);
		
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
				if(nameString.equals("") || lastNameString.equals("") || emailString.equals("") || addressString.equals("") || jmbgString.equals("") || usernameString.equals("") || passwordString.equals("")) {
					error.setText("Input incorrect. Try again.");
					error.setForeground(Color.red);
					error.setVisible(true);
				}else {
					if(workerMng.getWorkers() != null) {
						boolean check = true;
						for(Worker worker: workerMng.getWorkers()) {
							if(worker.getUsername().equals(usernameString)) {
								error.setText("Username already exists. Try again.");
								error.setForeground(Color.red);
								check = false;
							}else if(worker.getJmbg().equals(jmbgString)) {
								error.setText("JMBG already exists. Try again.");
								error.setForeground(Color.red);
								check = false;
							}
						}
						if(check == true) {
							Worker worker = new Worker(jmbgString, nameString, lastNameString, emailString, addressString, genderString, usernameString, passwordString, 1);
							workerMng.insertData(worker);
							error.setText(nameString+" added successfully.");
							error.setForeground(Color.black);
						}
					}
				}
			}
		});
	}
}