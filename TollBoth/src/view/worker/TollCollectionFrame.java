package view.worker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import manage.ManagerFactory;
import users.TollBooth;
import users.TollStation;
import users.Vehicle.Category;

public class TollCollectionFrame extends JFrame {

	private static final long serialVersionUID = -9137333284920567165L;

	private JTextField txtDepartureTime;
	private JComboBox<TollStation> stationCB;
	private JComboBox<Category> categoryCB;
	
	private ManagerFactory mngFactory;
	private TollBooth tollBooth;

	public TollCollectionFrame(ManagerFactory mngFactory, TollBooth tollBooth) {
		this.mngFactory = mngFactory;
		this.tollBooth = tollBooth;
		getContentPane().setLayout(null);

		JLabel lblTollCollection = new JLabel("Toll collection");
		lblTollCollection.setBounds(210, 20, 80, 13);
		getContentPane().add(lblTollCollection);

		JLabel lblDeparture = new JLabel("Departure: ");
		lblDeparture.setBounds(36, 72, 66, 13);
		getContentPane().add(lblDeparture);

		this.stationCB = new JComboBox<TollStation>();
		fillStationCB();
		stationCB.setBounds(126, 68, 209, 21);
		getContentPane().add(stationCB);

		JLabel lblDepartureTime = new JLabel("Departure time: ");
		lblDepartureTime.setBounds(36, 113, 80, 13);
		getContentPane().add(lblDepartureTime);

		txtDepartureTime = new JTextField();
		txtDepartureTime.setBounds(126, 110, 209, 19);
		getContentPane().add(txtDepartureTime);
		txtDepartureTime.setColumns(10);

		JLabel lblCategory = new JLabel("Vehicle category: ");
		lblCategory.setBounds(36, 160, 98, 13);
		getContentPane().add(lblCategory);

		this.categoryCB = new JComboBox<Category>();
		fillCategoryCB();
		categoryCB.setBounds(144, 156, 191, 21);
		getContentPane().add(categoryCB);

		JButton btnNewButton = new JButton("Collect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TollStation beginStation = (TollStation) stationCB.getSelectedItem();
				int endStation = tollBooth.getTollStation();
				Category category = (Category) categoryCB.getSelectedItem();
				String beginTimeStr = txtDepartureTime.getText();
				if (validData()) {
					
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Invalid input! Data format must be like: ");
				}
				
			}

			private boolean validData() {
				// TODO Auto-generated method stub
				return false;
			}

		});
		btnNewButton.setBounds(210, 291, 85, 21);
		getContentPane().add(btnNewButton);

		setTitle("Toll Collection Frame");
		setSize(550, 350);
		setLocation(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void fillStationCB() {
		this.mngFactory.getStationMng().reloadData();
		List<TollStation> stations = this.mngFactory.getStationMng().getTollStations();
		for (TollStation station : stations) {
			this.stationCB.addItem(station);
		}
	}

	private void fillCategoryCB() {
		for (Category category : Category.values()) {
			this.categoryCB.addItem(category);
		}
	}

	public static void main(String[] args) {
		System.out.println("aaa");
	}
}
