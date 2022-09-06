package view.worker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import manage.ManagerFactory;
import users.TollBooth;
import users.User;
import users.Worker;
import utils.AppSettings;
import utils.LoggingOut;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class WorkerMainFrame extends JFrame {
	
	private static final long serialVersionUID = 404939488496142612L;
	
	Worker worker;
	TollBooth tollBooth;

	public WorkerMainFrame(ManagerFactory mngFactory, User user) {
		this.worker = mngFactory.getWorkMng().loadByUsername(user.getUsername());
		worker.setTollBooth(28);
		this.tollBooth = mngFactory.getBoothMng().loadByID(worker.getTollBooth());
		
		setTitle("Worker Main Frame");
		setSize(550, 350);
		setLocation(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCollection = new JButton("Collection");
		btnCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO add action for collection
				if (tollBooth == null) {
					JOptionPane.showMessageDialog(btnCollection, "You currently do not have a function!");
					return;
				}
				if (!tollBooth.isWorking()){
					JOptionPane.showMessageDialog(btnCollection, "Toll booth is not working!");
					return;
				}
				
				TollCollectionFrame tollCollectionFrame = new TollCollectionFrame(mngFactory, tollBooth);
			}
		});
		panel.add(btnCollection);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoggingOut.logOut(WorkerMainFrame.this);
			}
		});
		panel.add(btnLogOut);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblWelcome = new JLabel("Welcome");
		panel_1.add(lblWelcome);
		setVisible(true);
	}

	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings("jdbc:ucanaccess://database/TollBooth.accdb");
		ManagerFactory managerFactory = new ManagerFactory(appSettings);
		managerFactory.loadData();
		
		User user = managerFactory.getUserMng().getUsers().get(0);
		WorkerMainFrame workerMainFrame = new WorkerMainFrame(managerFactory, user); 
	}
}
