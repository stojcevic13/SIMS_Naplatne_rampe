package crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manage.ManagerFactory;
import manage.TollBoothManager;
import manage.TollStationManager;
import manage.WorkerManager;
import net.miginfocom.swing.MigLayout;
import users.TollBooth;
import users.TollStation;
import users.Worker;

public class DeleteTollBooth extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3416940830779249716L;
	
	private ManagerFactory mngFactory;
	private TollBoothManager boothMng;
	private WorkerManager workerMng;

	public DeleteTollBooth(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.boothMng  = this.mngFactory.getBoothMng();
		this.workerMng = this.mngFactory.getWorkMng();
		deleteBoothFrame();
	}
	
	private void deleteBoothFrame() {
		this.setTitle("Delete TollBooth!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		deleteBoothGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void deleteBoothGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout());
		
		String[] stations = getBooths();

		JComboBox<String> stationCB = new JComboBox<String>(stations);
		JButton submitBtn = new JButton("Delete");
		JLabel successlabel = new JLabel("Booth deleted successfully!");
		successlabel.setVisible(false);
		stationCB.setSelectedIndex(0);
		
		panel.add(new JLabel ("Toll Booth ID:"));
		panel.add(stationCB, "wrap");
		panel.add(successlabel, "wrap");
		panel.add(submitBtn, "span, align center");
		
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				boothMng.deleteData(stationCB.getSelectedItem().toString());
				successlabel.setVisible(true);
				removeWorkers(Integer.valueOf(stationCB.getSelectedItem().toString()));
				mngFactory.loadData();
			}
		});

	}
	
	private void removeWorkers(int boothId) {
		for(Worker worker : workerMng.getWorkers()) {
			if(worker.getTollBooth() == boothId) {
				Worker newWorker = worker;
				newWorker.setTollBooth(0);
				workerMng.updateData(newWorker);
			}
		}
	}
	
	private String[] getBooths() {
		String[] data = null;
		int i = 0;
		if(boothMng.getTollBooths() != null) {
			data = new String[boothMng.getTollBooths().size()];
			for(TollBooth booth : boothMng.getTollBooths()) {
				data[i] = String.valueOf(booth.getTollBoothID());
				i = i + 1;
			}
		}
		return data;
	}
}
