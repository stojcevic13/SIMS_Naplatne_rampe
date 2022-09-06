package view.leader;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import manage.ManagerFactory;
import manage.TollStationManager;
import net.miginfocom.swing.MigLayout;
import users.TollStation;
import vehicles.Stats;


public class ReportDialog extends JDialog{
	private static final long serialVersionUID = 1L;
private ManagerFactory mngFactory;
protected JButton btnOK;
private LeaderMainFrame contentPane;
	
	ReportDialog(ManagerFactory mngFactory, LeaderMainFrame contentPane){
		super();
		this.mngFactory = mngFactory;
		this.contentPane = contentPane;
		this.setTitle("Custom report");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		GUI();
		pack();
		this.setVisible(true);
		getRootPane().setDefaultButton(btnOK);
	}

	private void GUI() {
		
		MigLayout ml = new MigLayout("wrap 4", "[][][]", "[]10[]10[]10[]20[]");
		getContentPane().setLayout(ml);
		
		JLabel lblDate = new JLabel("Dates yyyy/MM/dd");
		getContentPane().add(lblDate, "span 4");

		JLabel lblDateFrom = new JLabel("Date from: ");
		getContentPane().add(lblDateFrom);

		
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		JFormattedTextField txtDateFrom = new JFormattedTextField(format);
		txtDateFrom.setColumns(12);
		
		getContentPane().add(txtDateFrom);
		
		
		JLabel lblDateTo = new JLabel("Date to: ");
		getContentPane().add(lblDateTo);

		JFormattedTextField txtDateTo = new JFormattedTextField(format);
		txtDateTo.setColumns(12);
		getContentPane().add(txtDateTo);
		
		JLabel lbltollStation = new JLabel("Chose toll station: ");
		getContentPane().add(lbltollStation);
		
		ArrayList<TollStation> tollStations = TollStationManager.tollStations;
		int size = tollStations.size() + 1;
		String[] optionsToChoose = new String[size];
		optionsToChoose[0] = "All Stations";
		for(int i = 1; i <= tollStations.size(); i++) {
			optionsToChoose[i] = tollStations.get(i - 1).getLocation();
		}
		JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
		jComboBox.setBounds(80, 50, 140, 20);
		getContentPane().add(jComboBox);
		
		getContentPane().add(new JLabel());
		getContentPane().add(new JLabel());
		getContentPane().add(new JLabel());
		getContentPane().add(new JLabel());
		JButton btnCancel = new JButton("Cancel");
		btnOK = new JButton("OK");
		
		
		add(btnCancel);
		add(btnOK);
		
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dateFromString = txtDateFrom.getText().trim();
				String dateToString = txtDateTo.getText().trim();
				LocalDate dateFromDate = null;
				LocalDate dateToDate = null;
				boolean error = false;
				
				if(dateFromString!= null || !dateFromString.equals("") || dateFromString.equals(" ") || dateFromString.length() >= 0 || dateFromString != "\n") {
					try {
						dateFromDate = LocalDate.parse(dateFromString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
						
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Date has to be in yyyy/MM/dd format", "Error - invalid date format", JOptionPane.ERROR_MESSAGE);
						error = true;
					}
				}
				
				if(dateToString!= null || !dateToString.equals("") || dateToString.equals(" ") || dateToString.length() >= 0 || dateToString != "\n") {
					try {
						dateToDate = LocalDate.parse(dateToString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
						
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Date has to be in yyyy/MM/dd format", "Error - invalid date format", JOptionPane.ERROR_MESSAGE);
						error = true;
					}
				}
				
				String selectedTollBooth = jComboBox.getItemAt(jComboBox.getSelectedIndex());
				
				if(!error) {
					if(selectedTollBooth == "All Stations") {
						Stats stats = Stats.getAllStats(mngFactory, dateFromDate , dateToDate);
						contentPane.generateAllReportFrame(dateFromDate, dateToDate, stats);
					}else {
						TollStation station = TollStationManager.findTollStation(selectedTollBooth);
						Stats stats = Stats.getSingleStats(mngFactory, dateFromDate , dateToDate, station);
						contentPane.generateSingleReportFrame(dateFromDate, dateToDate, stats);
					}
					setVisible(false);
					dispose();
				}
				
				}
			
				
			
		});
	}
}
