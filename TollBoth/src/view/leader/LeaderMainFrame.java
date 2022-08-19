package view.leader;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import manage.ManagerFactory;
import users.Leader;
import users.User;
import view.LoginFrame;


public class LeaderMainFrame extends JFrame{

	static final long serialVersionUID = -7209558051742382765L;
	private ManagerFactory mngFactory;
	private Leader leader;
	

	public LeaderMainFrame(ManagerFactory mngFactory, Leader leader) {
		super();
		this.mngFactory = mngFactory;
		this.leader = leader;
		leaderMainFrame();
	}

	private void leaderMainFrame() {
		this.setTitle("NAND - autoput");
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		leaderGUI();
		this.setMinimumSize(new Dimension(600,500));
		this.setVisible(true);
	}
	
	private void leaderGUI() {
		JMenuBar mainMenu = new JMenuBar();
		
		JMenu profileMenu = new JMenu("Profile");
		JMenuItem profileItem = new JMenuItem("Show Profile");
		
		profileMenu.add(profileItem);
		mainMenu.add(profileMenu);
		
		JMenu pricelistMenu = new JMenu("Price list");
		JMenuItem viewPricelistItem = new JMenuItem("View current price list");
		JMenuItem createPricelistItem = new JMenuItem("Create a new price list");
		JMenuItem editPricelistItem = new JMenuItem("Edit current price list");
		JMenuItem historyPricelistItem = new JMenuItem("View price list history");
		
		pricelistMenu.add(viewPricelistItem);
		pricelistMenu.add(createPricelistItem);
		pricelistMenu.add(editPricelistItem);
		pricelistMenu.add(historyPricelistItem);
		mainMenu.add(pricelistMenu);
		
		JMenu reportMenu = new JMenu("Reports");
		
		JMenuItem dailyReportItem = new JMenuItem("Daily report");
		JMenuItem customReportItem = new JMenuItem("Custom report");
		
		reportMenu.add(dailyReportItem);
		reportMenu.add(customReportItem);
		mainMenu.add(reportMenu);
		
		
		JMenu logoffMenu = new JMenu("Log off");
		JMenuItem logoffItem = new JMenuItem("Log off");
		
		logoffMenu.add(logoffItem);
		mainMenu.add(logoffMenu);
		
		this.setJMenuBar(mainMenu);
		
		getContentPane().setLayout(new FlowLayout());
		
		/////////////////////////////////////////////////////////////////////////
		

		ArrayList <JPanel> components = new ArrayList<JPanel>();
		
		LeaderProfile lp = new LeaderProfile(this.mngFactory, this.leader, this);
		components.add(lp);
		ViewPricelist vp = new ViewPricelist(this.mngFactory, this.leader, this);
		components.add(vp);
		
		lp.setVisible(false);
		vp.setVisible(true);
		
		///////////////////////////////////////////////////////////////////////////
		
		
		
		profileItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(JPanel panel : components) {
					if(panel.equals(lp)) {
						panel.setVisible(true);
					}else{
						panel.setVisible(false);
					}
				}
				
			}
			
		});
		
		viewPricelistItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(JPanel panel : components) {
					if(panel.equals(vp)) {
						panel.setVisible(true);
					}else{
						panel.setVisible(false);
					}
				}
				
			}
			
		});
		
		logoffItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				//ManagerFactory managerFactory = new ManagerFactory(mngFactory.getAppSettings());
				mngFactory.loadData();
				LoginFrame loginFrame = new LoginFrame(mngFactory);
				
			}
			
		});
		
		
		
		
		
	}


	
	
	
}