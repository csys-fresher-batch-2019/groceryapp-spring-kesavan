package com.chainsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databaseconnection {
	public static Connection connect() throws SQLException {
		LoggerGrocery LOGGER = LoggerGrocery.getInstance();
		try {
			// For sql connection 
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			// For online connection
			 Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LOGGER.debug(e);
		}
		//String url = "jdbc:oracle:thin:@localhost:1521:XE";
		//String user = "system";
		//String password = "oracle";
		 String url = "jdbc:mysql://3.7.14.61:3306/supermarket_kesavan_db";
		 String user = "kesavan";
		 String password = "kesavan";
		
		return DriverManager.getConnection(url, user, password);
	}
}