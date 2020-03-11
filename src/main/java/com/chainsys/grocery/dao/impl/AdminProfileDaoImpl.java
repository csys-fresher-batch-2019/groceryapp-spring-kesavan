package com.chainsys.grocery.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.chainsys.grocery.dao.AdminProfileDao;
import com.chainsys.grocery.dao.UserProfileDao;
import com.chainsys.grocery.model.AdminProfile;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.DBException;
import com.chainsys.grocery.util.DatabaseConnection;
import com.chainsys.grocery.util.ErrorMessage;
import com.chainsys.grocery.util.Jdbcpst;
import com.chainsys.grocery.util.LoggerGrocery;

public class AdminProfileDaoImpl implements AdminProfileDao {
	LoggerGrocery LOGGER = LoggerGrocery.getInstance();
	UserProfileDao ob = new UserProfileDaoImpl();

	public int addProducts(AdminProfile[] p) throws DBException {
		int rows = 0;
		for (AdminProfile obj : p) {
			String sql = "insert into products(product_name,product_id,manufacturer,quantity,unit,price_rs,stock)values(?,?,?,?,?,?,?)";
			Object[] params = { obj.getProductName(), obj.getProductId(), obj.getManufacturer(), obj.getQuantity(),
					obj.getUnit(), obj.getPriceRS(), obj.getStock() };
			rows = Jdbcpst.preparestmt(sql, params);
			ob.updateStatus();
			savepidreview(obj);

		}
		return rows;
	}

	private void savepidreview(AdminProfile obj) throws DBException {
		Jdbcpst.preparestmt("insert into proreview(product_id) values(" + obj.getProductId() + ")");
	}

	public void createOrder(ArrayList<UserProfile> ob, String user, String type, int id)
			throws DBException, SQLException {

		UserProfileDao obj = new UserProfileDaoImpl();

		int userId = obj.checkuserid(user);
		LocalDate today = LocalDate.now();
		System.out.println(today);
		LocalDate deliveryDate = today.plusDays(3);
		for (UserProfile obj1 : ob) {
			String sql = "select product_id,price_rs from products where product_id= ?";
			try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, obj1.getProductid());
				try (ResultSet rs1 = pst.executeQuery();) {
					int productId = 0;
					int price = 0;
					if (rs1.next()) {
						productId = rs1.getInt("product_id");
						price = rs1.getInt("price_rs");
					}
					int totalBill = price * obj1.getNoOfItems();
					String payment = type;
					String sql3 = "insert into orderdata(user_id,product_id,order_date,delivery_date,no_of_items,price_per_item,order_status,total_amount,payment,transaction_id) values(?,?,?,?,?,?,'ORDERED',?,?,?)";

					Object[] params = { userId, productId, Date.valueOf(today), Date.valueOf(deliveryDate),
							obj1.getNoOfItems(), price, totalBill, payment, id };
					Jdbcpst.preparestmt(sql3, params);
					// stmt.executeUpdate(query);
					Jdbcpst.preparestmt("update products p set p.stock=p.stock- ?  where product_id =?",
							obj1.getNoOfItems(), productId);
					obj.updateStatus();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DBException(ErrorMessage.NO_DATA_FOUND, e);
				}
			}
		}
	}

	public void updateProducts(int value, int id) throws DBException {
		Jdbcpst.preparestmt("update products set stock = stock+? where product_id=?", value, id);
		ob.updateStatus();
	}

	public ArrayList<AdminProfile> viewProducts() throws DBException {
		ArrayList<AdminProfile> view = new ArrayList<AdminProfile>();

		try (Connection con = DatabaseConnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select * from products";
			try (ResultSet rs = stmt.executeQuery(sql);) {
				while (rs.next()) {
					AdminProfile ap = new AdminProfile();
					ap.setProductName(rs.getString("product_name"));
					ap.setProductId(rs.getInt("product_Id"));
					ap.setManufacturer(rs.getString("manufacturer"));
					ap.setQuantity(rs.getFloat("quantity"));
					ap.setUnit(rs.getString("unit"));
					ap.setPriceRS(rs.getInt("price_rs"));
					ap.setStock(rs.getInt("stock"));
					ap.setStatus(rs.getString("status"));
					view.add(ap);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.INVALID_COLUMN_INDEX, e);
		}
		return view;

	}

	public int bill(ArrayList<UserProfile> ob) throws DBException {
		int amount = 0;
		for (UserProfile obj1 : ob) {
			String sql = "select price_rs from products where product_id= ?";
			try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
				pst.setInt(1, obj1.getProductid());
				try (ResultSet rs1 = pst.executeQuery();) {
					if (rs1.next()) {
						int price = rs1.getInt("price_rs");
						int totalBill = price * obj1.getNoOfItems();
						amount = amount + totalBill;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException(ErrorMessage.NO_DATA_FOUND, e);
			}
		}
		return amount;
	}

	public int revenue(String a) throws DBException {

		int total = 0;
		String type = " ";
		if (a.isEmpty()) {
			type = a;
		} else {
			type = "where payment= '" + a+ "'";
		}
		try (Connection con = DatabaseConnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select sum(total_amount) as total from orderdata " + type;
			System.out.println(sql);
			try (ResultSet rs = stmt.executeQuery(sql);) {
				if (rs.next()) {
					total = rs.getInt("total");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.NO_DATA_FOUND, e);
		}

		return total;

	}
}
