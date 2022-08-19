package pricelist;

import java.util.HashMap;
import java.util.Map;

import users.TollStation;



public class Price {
		TollStation tollStation;
		private Map<String, Double> prices  = new HashMap<String, Double>();
		

		public  Price(TollStation tollStation, Double c1aDIN, Double c1DIN, Double c2DIN, Double c3DIN, Double c4DIN, Double c1aEU, Double c1EU, Double c2EU, Double c3EU,Double c4EU){
			this.tollStation = tollStation;
			prices.put("c1aDIN", c1aDIN);
			prices.put("c1DIN", c1DIN);
			prices.put("c2DIN", c2DIN);
			prices.put("c3DIN", c3DIN);
			prices.put("c4DIN", c4DIN);
			prices.put("c1aEU", c1aEU);
			prices.put("c1EU", c1EU);
			prices.put("c2EU", c2EU);
			prices.put("c3EU", c3EU);
			prices.put("c4EU", c4EU);
		}


		public TollStation getTollStation() {
			return tollStation;
		}


		public void setTollStation(TollStation tollStation) {
			this.tollStation = tollStation;
		}


		public Map<String, Double> getPrices() {
			return prices;
		}


		public void setPrices(Map<String, Double> prices) {
			this.prices = prices;
		}
		
		
		
}
