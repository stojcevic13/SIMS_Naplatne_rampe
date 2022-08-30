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
import manage.PaymentManager;
import manage.PriceManager;
import users.Payment;
import users.TollBooth;
import users.TollStation;
import utils.AppSettings;
import utils.Convert;
import utils.DateCalculator;
import manage.PriceManager.Category;
import manage.PriceManager.Currency;
import manage.SectionManager;
import manage.TollSystemManager;

public class TollCollectionFrame extends JFrame {

	private static final long serialVersionUID = -9137333284920567165L;

	private JTextField txtDepartureTime;
	private JComboBox<TollStation> stationCB;
	private JComboBox<Category> categoryCB;

	private ManagerFactory mngFactory;
	private TollBooth tollBooth;

	protected String beginTimeStr;
	protected LocalDateTime beginTime;

	protected TollStation beginStation;

	protected TollStation endStation;

	public TollCollectionFrame(ManagerFactory mngFactory, TollBooth tollBooth) {
		this.mngFactory = mngFactory;
		this.tollBooth = tollBooth;
		this.endStation = mngFactory.getStationMng().loadByID(tollBooth.getTollStation());
		getContentPane().setLayout(null);

		JLabel lblTollCollection = new JLabel("Toll collection");
		lblTollCollection.setBounds(210, 20, 80, 13);
		getContentPane().add(lblTollCollection);

		JLabel lblDeparture = new JLabel("Departure: ");
		lblDeparture.setBounds(36, 72, 66, 13);
		getContentPane().add(lblDeparture);

		this.stationCB = new JComboBox<TollStation>();
		fillStationCB();
		stationCB.setBounds(170, 68, 209, 21);
		getContentPane().add(stationCB);

		JLabel lblDepartureTime = new JLabel("Departure time: ");
		lblDepartureTime.setBounds(36, 113, 80, 13);
		getContentPane().add(lblDepartureTime);

		txtDepartureTime = new JTextField();
		txtDepartureTime.setBounds(170, 110, 209, 19);
		getContentPane().add(txtDepartureTime);
		txtDepartureTime.setColumns(10);

		JLabel lblCategory = new JLabel("Vehicle category: ");
		lblCategory.setBounds(36, 160, 98, 13);
		getContentPane().add(lblCategory);

		this.categoryCB = new JComboBox<Category>();
		fillCategoryCB();
		categoryCB.setBounds(170, 156, 191, 21);
		getContentPane().add(categoryCB);

		JButton btnNewButton = new JButton("Collect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				beginStation = (TollStation) stationCB.getSelectedItem();
				
				Category category = (Category) categoryCB.getSelectedItem();
				beginTimeStr = txtDepartureTime.getText();
				LocalDateTime endTime = LocalDateTime.now();
				
				if (!validData()) {
					JOptionPane.showMessageDialog(btnNewButton, "Invalid input! Data format must be like: " + AppSettings.DATE_TIME_FMT);
					return;
				}
				if (overSpeedLimit(beginTime, endTime)) {
					JOptionPane.showMessageDialog(btnNewButton, "Speed over the limit! The police is called.");
					return;
				}
				
				double price = mngFactory.getPriceMng().calculatePrice(category, Currency.DIN, beginStation, endStation);
				Payment payment = new Payment(price, endTime, category, endStation);
				PaymentManager.insertData(payment);				
				
				// TODO: Poruka o uspjesnoj naplati
			}

			private boolean overSpeedLimit(LocalDateTime beginTime, LocalDateTime endTime) {
				double kilometers = SectionManager.getDistance(beginStation, endStation);
				double hours = DateCalculator.getHours(beginTime, endTime);
				double averageSpeed = kilometers*1.0 / hours;
				double speedLimit = TollSystemManager.getSpeedLimit();
				return averageSpeed > speedLimit;
			}

			private boolean validData() {
				beginTime = Convert.toDate(txtDepartureTime.getText());
				return !(beginTime == null);
			}

		});
		
		btnNewButton.setBounds(210, 270, 85, 21);
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
			if (!station.equals(endStation))
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
