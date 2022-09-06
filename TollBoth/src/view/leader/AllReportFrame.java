package view.leader;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import manage.ManagerFactory;
import manage.PriceManager;
import manage.PriceManager.Currency;
import manage.VehicleManager;

import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import model.PricelistModel;
import model.ReportModel;
import users.Leader;
import users.TollStation;
import vehicles.Stats;
import vehicles.Vehicle;

public class AllReportFrame extends JPanel{

	private static final long serialVersionUID = 485057167223647268L;
	
	private ManagerFactory mngFactory;
	private Leader leader;
	private JFrame contentPane;
	
	protected JLabel lblReport;
	protected JTable table;
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	


	public AllReportFrame(ManagerFactory mngFactory, Leader leader, JFrame contentPane, Stats stats, LocalDate dateFrom, LocalDate dateTo) {
		super();
		this.mngFactory = mngFactory;
		this.leader = leader;
		this.contentPane = contentPane;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setMinimumSize(contentPane.getMinimumSize());
		lblReport = new JLabel("Income report for all toll stations of NAND from: " + dateFrom.toString() + " to: " + dateTo.toString());
		this.add(lblReport);
		this.add(new JLabel());
		this.add(new JLabel());
		
		JLabel lblTotalIncomeText = new JLabel("Total income: " + Double.toString(stats.getSum()) + " din");
		this.add(lblTotalIncomeText);
		this.add(new JLabel());
		JLabel lblVehicleNumText = new JLabel("Total vehicle number: " + Integer.toString(stats.getNum()));
		this.add(lblVehicleNumText);
		this.add(new JLabel());
		JLabel lblMaxStation = new JLabel("Toll station with biggest income: " + stats.getMaxStation().getLocation());
		this.add(lblMaxStation);
		this.add(new JLabel());
		JLabel lblStationIncome = new JLabel("Income for " + stats.getMaxStation().getLocation() + " station: " + Double.toString(stats.getMaxIncome()) + " din");
		this.add(lblStationIncome);
		this.add(new JLabel());
		
		ReportModel pricelistModel = new ReportModel(mngFactory, stats.getStations());
		  
		  table = new JTable(pricelistModel);
		  table.getColumnModel().getColumn(0).setPreferredWidth(100);
		  table.getColumnModel().getColumn(1).setPreferredWidth(100);
		  table.getColumnModel().getColumn(2).setPreferredWidth(100);
		  table.setRowSelectionAllowed(true); table.setColumnSelectionAllowed(false);
		  table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		  table.setDefaultEditor(Object.class, null);
		  tableSorter.setModel((AbstractTableModel) table.getModel());
		  table.setRowSorter(tableSorter);
		  table.setPreferredScrollableViewportSize(new Dimension(300, 150));
		  table.setSize(contentPane.getSize());
		  
		  JScrollPane scrollPane = new JScrollPane(table);
		  this.add(scrollPane);
		  
		  scrollPane.setVisible(true);
		
		  
		this.contentPane.add(this);  
	}
}