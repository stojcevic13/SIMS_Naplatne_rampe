package crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class WorkerCRUDMenu extends JFrame{

	public ManagerFactory mngFactory;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3413603756480024960L;

	public WorkerCRUDMenu(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		menuFrame();
	}
	
	private void menuFrame() {
		this.setTitle("Change Worker Data");
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
		
		JButton createBtn = new JButton("Create new worker");
		JButton updateBtn = new JButton("Change worker data");
		JButton readBtn = new JButton("Read worker data");
		JButton deleteBtn = new JButton("Delete a worker");

		panel.add(createBtn);
		panel.add(updateBtn, "wrap");
		panel.add(readBtn);
		panel.add(deleteBtn, "wrap");

		
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateWorker cW = new CreateWorker(getMngFactory());
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		readBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	
	public ManagerFactory getMngFactory() {
		return mngFactory;
	}
}
