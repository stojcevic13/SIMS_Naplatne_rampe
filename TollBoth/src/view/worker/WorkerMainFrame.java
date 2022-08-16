package view.worker;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import manage.ManagerFactory;
import users.Worker;
import view.LoginFrame;

public class WorkerMainFrame extends JFrame{

	private static final long serialVersionUID = -6087715025409920683L;

	private ManagerFactory mngFactory;
	private Worker worker;
	
	
	public WorkerMainFrame(ManagerFactory mngFactory, Worker worker) throws HeadlessException {
		super();
		this.mngFactory = mngFactory;
		this.worker = worker;
		workerMainFrame();
	}


	public ManagerFactory getMngFactory() {
		return mngFactory;
	}
	
	public Worker getWorker() {
		return worker;
	}

	private void workerMainFrame() {
		this.setTitle("NAND Autoput");
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(600,500));
		workerGUI();
		this.setVisible(true);
	}
	
	private void workerGUI() {
		JMenuBar mainMenu = new JMenuBar();
		JMenu profileMenu = new JMenu("Profile");
		JMenuItem profileItem = new JMenuItem("Show Profile");
		
		profileMenu.add(profileItem);
		mainMenu.add(profileMenu);
		
		JMenu kurseviMenu = new JMenu("Process vehicle");
		
		JMenu odjavaMenu = new JMenu("Log off");
		JMenuItem odjaviteSeItem = new JMenuItem("Log off");
		
		odjavaMenu.add(odjaviteSeItem);
		mainMenu.add(odjavaMenu);
		
		this.setJMenuBar(mainMenu);
		
		getContentPane().setLayout(new FlowLayout());
		

		
		odjaviteSeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				ManagerFactory managerFactory = new ManagerFactory(mngFactory.getAppSettings());
				managerFactory.loadData();
				LoginFrame loginFrame = new LoginFrame(managerFactory);				
			}
			
		});
		
		
		
		
		
	}

	
	
	
	

}
