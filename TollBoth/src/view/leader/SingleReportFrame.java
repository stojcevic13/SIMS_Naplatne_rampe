package view.leader;



import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manage.ManagerFactory;

import users.Leader;
import vehicles.Stats;

public class SingleReportFrame extends JPanel{

	private static final long serialVersionUID = 485057167223647268L;
	
	private ManagerFactory mngFactory;
	private Leader leader;
	private JFrame contentPane;
	
	protected JLabel lblReport;
	
	public SingleReportFrame(ManagerFactory mngFactory, Leader leader, JFrame contentPane, Stats stats, LocalDate dateFrom, LocalDate dateTo) {
		super();
		this.mngFactory = mngFactory;
		this.leader = leader;
		this.contentPane = contentPane;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setMinimumSize(contentPane.getMinimumSize());
		lblReport = new JLabel("Income report for "+ stats.getMaxStation().getLocation() + " toll station of NAND from: " + dateFrom.toString() + " to: " + dateTo.toString());
		this.add(lblReport);
		this.add(new JLabel());
		this.add(new JLabel());
		
		JLabel lblTotalIncomeText = new JLabel("Total income for " + stats.getMaxStation().getLocation() + " toll station : " + Double.toString(stats.getSum()) + " din");
		this.add(lblTotalIncomeText);
		this.add(new JLabel());
		JLabel lblVehicleNumText = new JLabel("Total vehicle number: " + Integer.toString(stats.getNum()));
		this.add(lblVehicleNumText);
		this.add(new JLabel());
		
		this.contentPane.add(this);  
	}
}