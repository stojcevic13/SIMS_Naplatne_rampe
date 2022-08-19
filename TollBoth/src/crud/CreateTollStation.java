package crud;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import manage.ManagerFactory;
import manage.TollStationManager;
import net.miginfocom.swing.MigLayout;
import users.TollStation;

public class CreateTollStation extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8919569208514246410L;

	private ManagerFactory mngFactory;
	private TollStationManager stationMng;
	
	public CreateTollStation(ManagerFactory mngFactory){
		this.mngFactory = mngFactory;
		this.stationMng  = this.mngFactory.getStationMng();
		createWorkerFrame();
	}

	private void createWorkerFrame() {
		this.setTitle("Create a new Toll Station!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		createWorkerGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void createWorkerGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "20[]20[]20", "[]20"));
		
		JTextField locationTF = new JTextField(30);
		JLabel error = new JLabel();
		
		error.setForeground(Color.red);
		error.setVisible(false);
		JButton submitBtn = new JButton("Submit");
		
		panel.add(new JLabel("Location:"));
		panel.add(locationTF, "wrap");
		panel.add(error, "wrap");
		panel.add(submitBtn);
		
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String location = locationTF.getText().trim();
				if(location == "") {
					error.setText("Input incorrect. Try again.");
					error.setForeground(Color.red);
					error.setVisible(true);
				}else {
					TollStation station = new TollStation(location);
					stationMng.insertData(station);
					error.setText(location +" added successfully.");
					error.setForeground(Color.black);
					error.setVisible(true);
					mngFactory.loadData();
				}
			}
		});
	}
}
