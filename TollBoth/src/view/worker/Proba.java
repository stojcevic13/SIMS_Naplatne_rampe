package view.worker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import utils.AppSettings;

public class Proba {
	
	enum Dani {pon, uto, sre};

	public static void main(String[] args) {
		
		for (Dani d : Dani.values()) {
			System.out.println(d);
		}
		
		LocalDateTime date = LocalDateTime.parse("21.12.2002. 12:00", DateTimeFormatter.ofPattern(AppSettings.DATE_TIME_FMT));
		
	}

}
