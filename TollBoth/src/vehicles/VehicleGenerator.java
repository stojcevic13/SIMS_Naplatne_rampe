package vehicles;

public class VehicleGenerator {
	
	public static String registrationNumGenerate() {
		int alpha1 = 'A' + (int)(Math.random() * ('Z' - 'A'));
		int alpha2 = 'A' + (int)(Math.random() * ('Z' - 'A'));
		int alpha3 = 'A' + (int)(Math.random() * ('Z' - 'A')); 
		int digit1 = (int)(Math.random() * 10);
		int digit2 = (int)(Math.random() * 10);
		int digit3 = (int)(Math.random() * 10);
		int digit4 = (int)(Math.random() * 10);
		return "" + alpha1 + alpha2 + alpha3 + digit1 + digit2 + digit3 + digit4;
	}

	public static Vehicle generate() {
		
		
		return null;
		
	}
}
