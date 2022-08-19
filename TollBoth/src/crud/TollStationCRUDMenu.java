package crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class TollStationCRUDMenu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2504148749579682866L;
	public ManagerFactory mngFactory;

	public TollStationCRUDMenu(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		menuFrame();
	}
	
	private void menuFrame() {
		this.setTitle("Change Toll Station Data");
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
				CreateTollStation cW = new CreateTollStation(getMngFactory());
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateTollStation uW = new UpdateTollStation(getMngFactory());

			}
		});
		
		readBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReadTollStation rW = new ReadTollStation(getMngFactory());
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteTollStation dW = new DeleteTollStation(getMngFactory());
			}
		});

	}
	public ManagerFactory getMngFactory() {
		return mngFactory;
	}
}
