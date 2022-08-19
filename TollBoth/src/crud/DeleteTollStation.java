package crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manage.LeaderManager;
import manage.ManagerFactory;
import manage.TollBoothManager;
import manage.TollStationManager;
import manage.WorkerManager;
import net.miginfocom.swing.MigLayout;
import users.Leader;
import users.TollBooth;
import users.TollStation;
import users.Worker;

public class DeleteTollStation extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5051133345600021095L;

	private ManagerFactory mngFactory;
	private TollStationManager stationMng;
	private TollBoothManager boothMng;
	private WorkerManager workerMng;
	private LeaderManager leaderMng;
	
	public DeleteTollStation(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.stationMng  = this.mngFactory.getStationMng();
		this.boothMng = this.mngFactory.getBoothMng();
		this.workerMng = this.mngFactory.getWorkMng();
		this.leaderMng = this.mngFactory.getLeaderMng();
		deleteStationFrame();
	}
	
	private void deleteStationFrame() {
		this.setTitle("Delete the Toll Station!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		deleteStationGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void deleteStationGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout());
		
		String[] stations = getStations();

		JComboBox<String> stationCB = new JComboBox<String>(stations);
		JButton submitBtn = new JButton("Delete");
		JLabel successlabel = new JLabel("Station deleted successfully!");
		successlabel.setVisible(false);
		stationCB.setSelectedIndex(0);
		
		panel.add(new JLabel ("Toll Station ID:"));
		panel.add(stationCB, "wrap");
		panel.add(successlabel, "wrap");
		panel.add(submitBtn, "span, align center");
		
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				stationMng.deleteData(stationCB.getSelectedItem().toString());
				updateBoothAndUsers(Integer.valueOf(stationCB.getSelectedItem().toString()));
				successlabel.setVisible(true);
				mngFactory.loadData();
			}
		});

	}

	private void updateBoothAndUsers(int stationId) {
		for(TollBooth booth : boothMng.getTollBooths()) {
			if(booth.getTollStation() == stationId) {
				for(Worker worker : workerMng.getWorkers()) {
					if(worker.getTollBooth() == booth.getTollBoothID()) {
						Worker newWorker = worker;
						newWorker.setTollBooth(0);
						workerMng.updateData(newWorker);
					}
				}
				
				boothMng.deleteData(String.valueOf(booth.getTollBoothID()));
			}
		}
		for(Leader leader : leaderMng.getLeaders()) {
			if(leader.getTollStation() == stationId) {
				Leader newLeader = leader;
				newLeader.setTollStation(0);
				leaderMng.updateData(newLeader);
			}
		}
	}
	
	private String[] getStations() {
		String[] data = null;
		int i = 0;
		if(stationMng.getTollStations() != null) {
			data = new String[stationMng.getTollStations().size()];
			for(TollStation station : stationMng.getTollStations()) {
				data[i] = String.valueOf(station.getTollStationID());
				i = i + 1;
			}
		}
		return data;
	}
}
