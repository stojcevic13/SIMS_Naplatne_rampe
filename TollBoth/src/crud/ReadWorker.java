package crud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import manage.ManagerFactory;
import manage.WorkerManager;
import users.Manager;
import users.Worker;

public class ReadWorker extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3235042685021797527L;

	private ManagerFactory mngFactory;
	private static WorkerManager workerMng;
	
	public ReadWorker(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.workerMng  = this.mngFactory.getWorkMng();
		readWorkerFrame();
	}
	
	private void readWorkerFrame(){
		this.setTitle("Read data about the Workers!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screensize = toolkit.getScreenSize();
		int screenHeight = screensize.height;
		int screenWidth = screensize.width;
		this.setSize(screenWidth,screenHeight/3);
		
		this.setResizable(true);
		readWorkerGUI();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void readWorkerGUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		String column[]={"Username","Password","Jmbg","FirstName","LastName","Email","Address","Gender","TollBoothID"};
		String row[][] = showData();
		if(row == null) {
			JLabel error = new JLabel("No workers found. Add more workers so you can see their data!");
			error.setForeground(Color.red);
			panel.add(error);
		}else {
			JTable tabla = new JTable(row, column);
			JScrollPane pane = new JScrollPane(tabla);
			panel.add(pane);
		}
	}
	
	private static String[][] showData(){
		String[][] data = null;
		int i = 0;
		if(workerMng.getWorkers() != null) {
			data = new String[workerMng.getWorkers().size()][9];
			for(Worker worker : workerMng.getWorkers()) {
				data[i][0] = worker.getUsername();
				data[i][1] = worker.getPassword();
				data[i][2] = worker.getJmbg();
				data[i][3] = worker.getFirstName();
				data[i][4] = worker.getLastName();
				data[i][5] = worker.getEmail();
				data[i][6] = worker.getAddress();
				data[i][7] = worker.getGender().name();
				data[i][8] = String.valueOf(worker.getTollBooth());
				i = i + 1;
			}
		}
		return data;
	}
	
	public ManagerFactory getMngFactory() {
		return mngFactory;
	}
	
}
