package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import crud.CreateWorker;
import manage.ManagerFactory;
import manage.UserManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import net.miginfocom.swing.MigLayout;
import users.Leader;
import users.Person;
import users.User;
import view.leader.LeaderMainFrame;
import view.worker.WorkerMainFrame;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = -7798292689167507569L;
	private ManagerFactory mngFactory;
	private UserManager userMng;
	
	private JButton btnLogin;


	public LoginFrame(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.userMng  = mngFactory.getUserMng();
		loginFrame();
	}

	private void loginFrame() {
		this.setTitle("Login");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		loginGUI();
		pack();
		this.setVisible(true);
		getRootPane().setDefaultButton(btnLogin);
	}
	
	private void loginGUI() {
		 JPanel panel = new JPanel();

		 JTextField userName = new JTextField(20);  
		 JPasswordField password = new JPasswordField(20);
		 
		 btnLogin = new JButton("Login");
		 JButton btnReset = new JButton("Reset");
		 JButton btnCancel = new JButton("Cancel");
		 JLabel error = new JLabel("Text");
		
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("wrap 3", "[][]", "[]20[][]20[]"));

		panel.add(new JLabel("Welcome, please enter your information"), "align center,span");
		panel.add(new JLabel("Username"));
		panel.add(userName, "wrap");
		panel.add(new JLabel("Password"));
		panel.add(password, "wrap");
		
		error.setForeground(Color.red);
		panel.add(error, "span, align center");
		error.setVisible(false);
		panel.add(btnCancel);
		panel.add(btnReset, "align center");
		panel.add(btnLogin);

		
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userNameString = userName.getText().trim();
				String passwordString = new String(password.getPassword()).trim();
				if(userNameString.equals("") || passwordString.equals("")) {
					error.setText("You didn't fill all the fields");
					error.setVisible(true);
				}else {
				boolean found = false;
				if(userMng.getUsers() != null) {
					for(User user : userMng.getUsers()) {
						if(user.getUsername().equals(userNameString) &&
								user.getPassword().equals(passwordString)) {
							found = true;
							setVisible(false);
							dispose();
							switch(user.getClass().getName()) {
							case "users.Worker":
								CreateWorker cw = new CreateWorker(mngFactory);
								cw.setVisible(true);
								WorkerMainFrame wMF = new WorkerMainFrame(getMngFactory(), user);
								break;
							case "users.Manager":
								ManagerMainFrame mMF = new ManagerMainFrame(getMngFactory(), user);
								break;
							case "users.Leader":
								LeaderMainFrame lMF = new LeaderMainFrame(getMngFactory(), (Leader) user);
								break;
							default:
								JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
								setVisible(false);
								dispose();
							}
						}
					}
				
				}
				if(!found) {
					error.setText("User not with that username and password not found");
					error.setVisible(true);
				}}
				
			}
			
		});
		
		btnCancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			
						}});
		
		
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userName.setText(null);
				password.setText(null);
				error.setVisible(false);
			}
			
		});
		
	
		

	}

	public ManagerFactory getMngFactory() {
		return mngFactory;
	}
}
