package com.chainsys.grocery.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbcpst {

	public static int preparestmt(String sql, Object... params) throws DBException {
		int rows = 0;
		try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 1;
			for (Object obj : params) {
				pst.setObject(i, obj);
				i++;
			}
			rows = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new DBException(ErrorMessage.INVALID_COLUMN_INDEX, e);
		}
		return rows;

	}

	public static boolean exists(String sql, Object... params) throws DBException {
		boolean exists = false;
		try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			int i = 1;
			for (Object obj : params) {
				pst.setObject(i, obj);
				i++;
			}
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					exists = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.INVALID_COLUMN_INDEX, e);
				}
		return exists;

	}
}
