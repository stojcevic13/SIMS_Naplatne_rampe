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
import net.miginfocom.swing.MigLayout;
import users.Leader;

public class DeleteLeader extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1833964318005075248L;

	private ManagerFactory mngFactory;
	private LeaderManager leaderMng;

	public DeleteLeader(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.leaderMng  = this.mngFactory.getLeaderMng();
		deleteLeaderFrame();
	}

	private void deleteLeaderFrame() {
		this.setTitle("Delete a Leader!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		deleteLeaderGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void deleteLeaderGUI() {
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
				leaderMng.deleteData(usernameCB.getSelectedItem().toString());
				successlabel.setVisible(true);
				mngFactory.loadData();
			}
		});
		
	}
	
	private String[] getUsernames() {
		String[] data = null;
		int i = 0;
		if(leaderMng.getLeaders() != null) {
			data = new String[leaderMng.getLeaders().size()];
			for(Leader leader : leaderMng.getLeaders()) {
				data[i] = leader.getUsername();
				i = i + 1;
			}
		}
		return data;
	}


}
