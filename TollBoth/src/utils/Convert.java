package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Convert {
	
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(AppSettings.DATE_TIME_FMT);
	private static DateTimeFormatter sqlDateTimeFormatter = DateTimeFormatter.ofPattern(AppSettings.SQL_DATE_TIME_FMT);
	
	public static LocalDateTime toDate(String dateStr) {
		try {
			return LocalDateTime.parse(dateStr, dateTimeFormatter);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String toString(LocalDateTime date) {
		return date.format(dateTimeFormatter);
	}
	
	public static String toSqlString(LocalDateTime date) {
		return date.format(sqlDateTimeFormatter);
	}

	public static void main(String[] args) {
		
		LocalDateTime d = toDate("21.12.2022 12:00:00");
		String s = toString(d);
		System.out.println(s);
	}

}
