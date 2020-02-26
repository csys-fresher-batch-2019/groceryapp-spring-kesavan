package com.chainsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databaseconnection {
	public static Connection connect() throws SQLException {
		LoggerGrocery LOGGER = LoggerGrocery.getInstance();
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LOGGER.debug(e);
		}
		// String url = "jdbc:oracle:thin:@localhost:1521:XE",system","oracle"";
		String url = "jdbc:mysql://3.6.224.170:3306/supermarket_kesavan_db";
		String user = "kesavan";
		String password = "kesavan";

		// jdbc:mysql://localhost:3306/studentdb", "root", "password"

		return DriverManager.getConnection(url, user, password);
	}
}