package utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateCalculator {
	
	public static double getHours(LocalDateTime startDate, LocalDateTime endDate) {
		long minutes = ChronoUnit.MINUTES.between(startDate, endDate);
		return minutes*1.0/60;
	}


}
