package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import crud.LeaderCRUDMenu;
import crud.TollBoothCRUDMenu;
import crud.TollStationCRUDMenu;
import crud.WorkerCRUDMenu;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import users.User;
import utils.LoggingOut;

public class ManagerMainFrame extends JFrame{
	ManagerFactory mngFactory;
	User user = new User();
	/**
	 * 
	 */
	private static final long serialVersionUID = 3815657894826347374L;

	public ManagerMainFrame(ManagerFactory mngFactory, User user) {
		this.mngFactory = mngFactory;
		this.user = user;
		menuFrame();
	}
	
	private void menuFrame() {
		this.setTitle("Manager Menu");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		menuGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
	
	private void menuGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "20[]20[]20", "[]20"));
		
		JButton workerBtn = new JButton("Change Worker Data");
		JButton leaderBtn = new JButton("Change Leader Data");
		JButton tollStationBtn = new JButton("Change Toll Station Data");
		JButton tollBoothBtn = new JButton("Change Toll Booth Data");
		JButton reportBtn = new JButton("Check Reports");
		JButton logOutBtn = new JButton("Log out");
		
		workerBtn.setPreferredSize(tollStationBtn.getPreferredSize());
		leaderBtn.setPreferredSize(tollStationBtn.getPreferredSize());
		tollBoothBtn.setPreferredSize(tollStationBtn.getPreferredSize());
		reportBtn.setPreferredSize(tollStationBtn.getPreferredSize());
		logOutBtn.setPreferredSize(tollStationBtn.getPreferredSize());
		
		panel.add(new JLabel ("Welcome, "+user.getFirstName()), "wrap");
		panel.add(workerBtn);
		panel.add(leaderBtn, "wrap");
		panel.add(tollStationBtn);
		panel.add(tollBoothBtn, "wrap");
		panel.add(reportBtn);
		panel.add(logOutBtn);
		
		workerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WorkerCRUDMenu wCm = new WorkerCRUDMenu(getMngFactory());
			}
		});
		
		leaderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LeaderCRUDMenu lCm = new LeaderCRUDMenu(getMngFactory());
			}
		});
		
		tollStationBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TollStationCRUDMenu sCm = new TollStationCRUDMenu(getMngFactory());
			}
		});
		
		tollBoothBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TollBoothCRUDMenu bCm = new TollBoothCRUDMenu(getMngFactory());
			}
		});
		
		reportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoggingOut.logOut(ManagerMainFrame.this);
			}
		});
	}
	
	public ManagerFactory getMngFactory() {
		return mngFactory;
	}
}
