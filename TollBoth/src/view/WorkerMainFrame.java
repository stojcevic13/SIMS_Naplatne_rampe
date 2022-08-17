package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import manage.ManagerFactory;
import users.User;
import utils.AppSettings;

public class WorkerMainFrame extends JFrame {
	
	private static final long serialVersionUID = 404939488496142612L;

	public WorkerMainFrame(ManagerFactory mngFactory, User worker) {
		setTitle("Worker Main Frame");
		getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(231, 20, 59, 13);
		getContentPane().add(lblWelcome);
		
		JButton btnCollection = new JButton("Collection");
		btnCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCollection.setBounds(212, 237, 97, 21);
		getContentPane().add(btnCollection);
		
		
		setSize(550, 350);
		setLocation(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings("jdbc:ucanaccess://database/TollBooth.accdb");
		ManagerFactory managerFactory = new ManagerFactory(appSettings);
		managerFactory.loadData();
		
		System.out.println("dsds");
		User user = managerFactory.getUserMng().getUsers().get(0);
		WorkerMainFrame workerMainFrame = new WorkerMainFrame(managerFactory, user); 
	}
}
