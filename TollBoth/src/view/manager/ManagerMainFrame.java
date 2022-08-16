package view.manager;

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
import users.Manager;
import users.Worker;
import view.LoginFrame;

public class ManagerMainFrame extends JFrame{

	private static final long serialVersionUID = -6087715025409920683L;

	private ManagerFactory mngFactory;
	private Manager manager;
	
	
	public ManagerMainFrame(ManagerFactory mngFactory, Manager manager) throws HeadlessException {
		super();
		this.mngFactory = mngFactory;
		this.manager = manager;
		managerMainFrame();
	}


	public ManagerFactory getMngFactory() {
		return mngFactory;
	}
	
	public Manager getManager() {
		return manager;
	}

	private void managerMainFrame() {
		this.setTitle("NAND Autoput");
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(600,500));
		managerGUI();
		this.setVisible(true);
	}
	
	private void managerGUI() {
		JMenuBar mainMenu = new JMenuBar();
		JMenu profileMenu = new JMenu("Profile");
		JMenuItem profileItem = new JMenuItem("Show Profile");
		
		profileMenu.add(profileItem);
		mainMenu.add(profileMenu);
		
		JMenu pricelistMenu = new JMenu("Pricelist");
		JMenuItem createPricelistItem = new JMenuItem("Create new Pricelist");
		JMenuItem editPricelistItem = new JMenuItem("Edit current Pricelist");
		JMenuItem historyPricelistItem = new JMenuItem("History of Pricelists");
		
		pricelistMenu.add(createPricelistItem);
		pricelistMenu.add(editPricelistItem);
		pricelistMenu.add(historyPricelistItem);
		mainMenu.add(pricelistMenu);
		
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