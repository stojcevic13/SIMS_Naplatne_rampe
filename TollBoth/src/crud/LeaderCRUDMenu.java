package crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class LeaderCRUDMenu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4607743030494145866L;
	public ManagerFactory mngFactory;

	public LeaderCRUDMenu(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		menuFrame();
	}
	
	private void menuFrame() {
		this.setTitle("Change Leader Data");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		menuGUI();
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void menuGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "20[]20[]20", "[]20"));
		
		JButton createBtn = new JButton("CREATE");
		JButton updateBtn = new JButton("UPDATE");
		JButton readBtn = new JButton("READ");
		JButton deleteBtn = new JButton("DELETE");

		createBtn.setPreferredSize(updateBtn.getPreferredSize());
		updateBtn.setPreferredSize(updateBtn.getPreferredSize());
		readBtn.setPreferredSize(updateBtn.getPreferredSize());
		deleteBtn.setPreferredSize(updateBtn.getPreferredSize());
		
		panel.add(createBtn);
		panel.add(updateBtn, "wrap");
		panel.add(readBtn);
		panel.add(deleteBtn, "wrap");

		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateLeader cW = new CreateLeader(getMngFactory());
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateLeader uW = new UpdateLeader(getMngFactory());

			}
		});
		
		readBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReadLeader rW = new ReadLeader(getMngFactory());
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteLeader dW = new DeleteLeader(getMngFactory());
			}
		});

	}
	public ManagerFactory getMngFactory() {
		return mngFactory;
	}
}
