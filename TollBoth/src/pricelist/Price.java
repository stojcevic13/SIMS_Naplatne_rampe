package pricelist;

import java.util.HashMap;
import java.util.Map;

import users.TollStation;



public class Price {
		TollStation tollStation;
		private Map<String, Double> prices  = new HashMap<String, Double>();
		

		public  Price(TollStation tollStation, Double c1aDIN, Double c1DIN, Double c2DIN, Double c3DIN, Double c4DIN, Double c1aEU, Double c1EU, Double c2EU, Double c3EU,Double c4EU){
			this.tollStation = tollStation;
			prices.put("1aDIN", c1aDIN);
			prices.put("1DIN", c1DIN);
			prices.put("2DIN", c2DIN);
			prices.put("3DIN", c3DIN);
			prices.put("4DIN", c4DIN);
			prices.put("1aEU", c1aEU);
			prices.put("1EU", c1EU);
			prices.put("2EU", c2EU);
			prices.put("3EU", c3EU);
			prices.put("4EU", c4EU);
		}
}
