package view.worker;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

import utils.AppSettings;
import utils.Convert;
import utils.DateCalculator;

public class Proba {
	
	enum Dani {pon, uto, sre};

	public static void main(String[] args) {
		
		LocalDateTime d1 = Convert.toDate("21.12.2022 12:00:00");
		LocalDateTime d2 = Convert.toDate("21.12.2022 13:42:00");
		
		double hours = DateCalculator.getHours(d1, d2);
		System.out.println(hours);
	}

}
