package manage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pricelist.Price;
import users.TollStation;
import java.util.Random;




public class PriceManager {
	
	public static enum Category{
		c1a, c1, c2, c3, c4;
		
		public static Category generateCategory() {
            Category[] values = Category.values();
            int length = values.length;
            int randIndex = new Random().nextInt(length);
            return values[randIndex];
        }
	  
	};

	
	public static enum Currency{
		DIN, EU
	};

	private static ArrayList<Price> priceList = new ArrayList<Price>();

	public ArrayList<Price> getPriceList() {
		return priceList;
	}
	
	public double calculatePrice(Category category, Currency currency, TollStation startPoint, TollStation endPoint  ) {
		Price startPricelist = null;
		Price endPricelist = null;
		double startPrice, endPrice;
		for(Price price : priceList) {
			if(price.getTollStation() == startPoint) {
				startPricelist = price;
			}
			if(price.getTollStation() == endPoint) {
				endPricelist = price;
			}
		}
		String subCategory = category.toString() + currency.toString();
		startPrice = startPricelist.getPrices().get(subCategory);
		endPrice = endPricelist.getPrices().get(subCategory);
		
		if(startPrice < endPrice) {
			return endPrice - startPrice;
		} else if (endPrice < startPrice) {
			return startPrice - endPrice;
		} else {
			return priceList.get(0).getPrices().get(subCategory);
		}
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
