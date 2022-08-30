package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import manage.PriceManager.Category;
import users.Payment;
import users.TollBooth;
import utils.AppSettings;
import utils.Convert;
import utils.DateCalculator;

public class PaymentManager {

	public static void insertData(Payment payment) {
		String databaseURL = AppSettings.getDatabaseURL();
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = null;

			double amount = payment.getAmount();
			String datetime = Convert.toSqlString(payment.getDatetime());
			String category = payment.getCategory().toString();
			int stationID = payment.getTollStation().getTollStationID();

			statement = connection.prepareStatement(String.format(
					"INSERT into Payment(Amount, Datetime, VehicleCategory, TollStation) VALUES ('%f', '%s', '%s', '%d')",
					amount, datetime, category, stationID));
			statement.executeUpdate();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertData(String s) {
//		String databaseURL = AppSettings.getDatabaseURL();
		String databaseURL = "jdbc:ucanaccess://database/TollBooth.accdb";
		try {
			Connection connection = DriverManager.getConnection(databaseURL);
			PreparedStatement statement = null;

			statement = connection.prepareStatement(s);
			statement.executeUpdate();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LocalDateTime date = Convert.toDate("21.12.2012 12:00:00");
		String dateStr = Convert.toSqlString(date);
		String s = String.format(
				"INSERT into Payment(Amount, Datetime, VehicleCategory, TollStation) VALUES ('%f', '%s', '%s', '%d')",
				20.6, dateStr, "categoryyy", 9);
		insertData(s);
	}
}
