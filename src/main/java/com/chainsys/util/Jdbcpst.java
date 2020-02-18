package com.chainsys.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Jdbcpst {

	public static void preparestmt(String sql, Object... params) throws DBException {
		LoggerGrocery LOGGER = LoggerGrocery.getInstance();

		try (Connection con = Databaseconnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 1;
			for (Object obj : params) {
				pst.setObject(i, obj);
				i++;
			}
			int rows  = pst.executeUpdate();
			System.out.println("Rows:" + rows);
		} catch (Exception e) {
			LOGGER.debug(e);
		}

	}
	
	public static boolean exists(String sql, Object... params)  {
		LoggerGrocery LOGGER = LoggerGrocery.getInstance();
boolean exists = false;
		try (Connection con = Databaseconnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 1;
			for (Object obj : params) {
				pst.setObject(i, obj);
				i++;
			}
			try(ResultSet rs = pst.executeQuery()){
				if (rs.next()) {
					exists = true;
				}
			}
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return exists;

	}
}
