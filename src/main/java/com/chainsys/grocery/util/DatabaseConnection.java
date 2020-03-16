package com.chainsys.grocery.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection connect() throws SQLException, DBException {
		String user = "", url = "", password = "";
		Connection conn = null;
		try {
			// For sql connection
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			// url = "jdbc:oracle:thin:@localhost:1521:XE";
			// user = "system";
			// password = "oracle";
			// For online connection

			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://3.7.14.61:3306/supermarket_kesavan_db";
			user = "kesavan";
			password = "kesavan";
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException(ErrorMessage.CONNECTION_FAILED, e);
		}

		return conn;
	}
}
