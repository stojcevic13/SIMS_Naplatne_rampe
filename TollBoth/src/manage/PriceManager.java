package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pricelist.Price;
import users.Leader;
import users.TollStation;
import utils.AppSettings;



public class PriceManager {
	private static ArrayList<Price> priceList = new ArrayList<Price>();

	public ArrayList<Price> getPriceList() {
		return priceList;
	}
	
	public void loadData(ResultSet result) {
		priceList.clear();
		try {
			while (result.next()) {
				Integer ts = result.getInt("TollStation");
				TollStation tollStation = TollStationManager.findTollStation(ts);
				Double c1aDIN =   result.getDouble("1aDIN");
				Double c1DIN =   result.getDouble("1DIN");
				Double c2DIN =   result.getDouble("2DIN");
				Double c3DIN =   result.getDouble("3DIN");
				Double c4DIN =   result.getDouble("4DIN");
				Double c1aEU =   result.getDouble("1aEU");
				Double c1EU =   result.getDouble("1EU");
				Double c2EU =   result.getDouble("2EU");
				Double c3EU =   result.getDouble("3EU");
				Double c4EU =   result.getDouble("4EU");
				
				Price tmp = new Price(tollStation, c1aDIN, c1DIN, c2DIN, c3DIN, c4DIN, c1aEU, c1EU, c2EU, c3EU, c4EU );
				PriceManager.priceList.add(tmp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
