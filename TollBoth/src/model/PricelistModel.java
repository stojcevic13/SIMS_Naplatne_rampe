package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import manage.ManagerFactory;
import pricelist.Price;

public class PricelistModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -4924672344808722781L;
	
	private ManagerFactory mngFactory;
	
	private String[] columnNames = {"I-a", "I", "II", "III", "IV", "Toll station", "I-a", "I", "II", "III", "IV"};
	
	private ArrayList<Price> data;
	
	public PricelistModel(ManagerFactory mngFactory) {
		this.mngFactory = mngFactory;
		this.data  = mngFactory.getPriceMng().getPriceList();
		
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
		Price p = data.get(rowIndex);
		Map<String, Double> prices = null;
		if(p == null) {
			return null;
		}else {
			prices = p.getPrices();
		}
		
		switch(columnIndex) {
		case 0:
			return prices.get("c1aDIN");
		case 1:
			return prices.get("c1DIN");
		case 2:
			return prices.get("c2DIN");
		case 3:
			return prices.get("c3DIN");
		case 4:
			return prices.get("c4DIN");
		case 5:
			return p.getTollStation().getLocation();
		case 6:
			return prices.get("c1aEU");
		case 7:
			return prices.get("c1EU");
		case 8:
			return prices.get("c2EU");
		case 9:
			return prices.get("c3EU");
		case 10:
			return prices.get("c4EU");
		default:
			return null;
		}
	}

} 