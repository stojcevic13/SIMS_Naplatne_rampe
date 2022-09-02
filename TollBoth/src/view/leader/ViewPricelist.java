package view.leader;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;


import manage.ManagerFactory;
import model.PricelistModel;
import users.Leader;

public class ViewPricelist extends JPanel{

	private static final long serialVersionUID = 485057167223647268L;
	
	private ManagerFactory mngFactory;
	private Leader leader;
	private JFrame contentPane;
	
	protected JLabel lblPrices;
	protected JTable table;
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	


	public ViewPricelist(ManagerFactory mngFactory, Leader leader, JFrame contentPane) {
		super();
		this.mngFactory = mngFactory;
		this.leader = leader;
		this.contentPane = contentPane;
		this.setLayout(new BorderLayout());
		
		this.setMinimumSize(contentPane.getMinimumSize());
		lblPrices = new JLabel("Current pricelist");

		this.add(lblPrices, BorderLayout.NORTH);
		
				
		PricelistModel pricelistModel = new PricelistModel(mngFactory);
		  
		  table = new JTable(pricelistModel);
		  table.getColumnModel().getColumn(0).setPreferredWidth(80);
		  table.getColumnModel().getColumn(1).setPreferredWidth(80);
		  table.getColumnModel().getColumn(2).setPreferredWidth(80);
		  table.getColumnModel().getColumn(3).setPreferredWidth(80);
		  table.getColumnModel().getColumn(4).setPreferredWidth(80);
		  table.getColumnModel().getColumn(5).setPreferredWidth(100);
		  table.getColumnModel().getColumn(6).setPreferredWidth(80);
		  table.getColumnModel().getColumn(7).setPreferredWidth(80);
		  table.getColumnModel().getColumn(8).setPreferredWidth(80);
		  table.getColumnModel().getColumn(9).setPreferredWidth(80);
		  table.getColumnModel().getColumn(10).setPreferredWidth(80);
		  table.setRowSelectionAllowed(true); table.setColumnSelectionAllowed(false);
		  table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		  table.setDefaultEditor(Object.class, null);
		  tableSorter.setModel((AbstractTableModel) table.getModel());
		  table.setRowSorter(tableSorter);
		  table.setPreferredScrollableViewportSize(new Dimension(550, 150));
		  table.setSize(contentPane.getSize());
		  
		  JScrollPane scrollPane = new JScrollPane(table);
		  this.add(scrollPane, BorderLayout.CENTER);
		  
		  scrollPane.setVisible(true);
		  		  
		  
		this.contentPane.add(this);
		 

	}

}
