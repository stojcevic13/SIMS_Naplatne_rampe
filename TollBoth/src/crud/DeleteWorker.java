package crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manage.ManagerFactory;
import manage.WorkerManager;
import net.miginfocom.swing.MigLayout;
import users.Worker;

public class DeleteWorker extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7711981757411555664L;

	private ManagerFactory mngFactory;
	private WorkerManager workerMng;
	
	public DeleteWorker(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.workerMng  = this.mngFactory.getWorkMng();
		deleteWorkerFrame();

	}
	
	private void deleteWorkerFrame() {
		this.setTitle("Delete a Worker!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		deleteWorkerGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void deleteWorkerGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout());
		
		String[] usernames = getUsernames();

		JComboBox<String> usernameCB = new JComboBox<String>(usernames);
		JButton submitBtn = new JButton("Delete");
		JLabel successlabel = new JLabel("User deleted successfully!");
		successlabel.setVisible(false);
		usernameCB.setSelectedIndex(0);
		
		panel.add(new JLabel ("Username:"));
		panel.add(usernameCB, "wrap");
		panel.add(successlabel, "wrap");
		panel.add(submitBtn, "span, align center");
		
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				workerMng.deleteData(usernameCB.getSelectedItem().toString());
				successlabel.setVisible(true);
				mngFactory.loadData();
			}
		});
		
	}

	private String[] getUsernames() {
		String[] data = null;
		int i = 0;
		if(workerMng.getWorkers() != null) {
			data = new String[workerMng.getWorkers().size()];
			for(Worker worker : workerMng.getWorkers()) {
				data[i] = worker.getUsername();
				i = i + 1;
			}
		}
		return data;
	}

}
