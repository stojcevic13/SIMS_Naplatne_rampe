package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import manage.ManagerFactory;
import pricelist.Price;
import users.TollStation;

public class ReportModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -4924672344808722781L;
	
	private ManagerFactory mngFactory;
	
	private String[] columnNames = {"Location", "Toll station ID", "Income"};
	
	private Map<TollStation, Double> map;
	private ArrayList<TollStation> data;
	
	public ReportModel(ManagerFactory mngFactory,Map<TollStation, Double> map) {
		this.mngFactory = mngFactory;
		this.map  = map;
		data = new ArrayList(map.keySet());
		
	}
	
	@Override
	public int getRowCount() {
		return this.data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (data.size() == 0) {
			return null;
		}
		
		TollStation t = data.get(rowIndex);
		double price = map.get(t);
		
		switch(columnIndex) {
		case 0:
			return t.getLocation();
		case 1:
			return t.getTollStationID();
		case 2:
			return price;
		default:
			return null;
		}
	}

} 