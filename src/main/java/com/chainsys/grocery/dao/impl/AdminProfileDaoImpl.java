package com.chainsys.grocery.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.SystemPropertyUtils;

import com.chainsys.grocery.dao.AdminProfileDao;
import com.chainsys.grocery.model.AdminProfile;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.Databaseconnection;
import com.chainsys.grocery.util.Errormessage;
import com.chainsys.grocery.util.Jdbcpst;
import com.chainsys.grocery.util.LoggerGrocery;

public class AdminProfileDaoImpl implements AdminProfileDao {
	LoggerGrocery LOGGER = LoggerGrocery.getInstance();

	public int addProducts(AdminProfile[] p) {
		int a = 0;
		for (AdminProfile obj : p) {
			try {
				Jdbcpst.preparestmt(
						"insert into products(product_name,product_id,manufacturer,quantity,unit,price_rs,stock)values('"
								+ obj.getProductName() + "'," + obj.getProductId() + ",'" + obj.getManufacturer() + "',"
								+ obj.getQuantity() + ",'" + obj.getUnit() + "'," + obj.getPriceRS() + ","
								+ obj.getStock() + ")");
				a = 1;

			} catch (Exception e) {
				LOGGER.debug(Errormessage.INVALID_COLUMN_INDEX);
			}
			try {
				Jdbcpst.preparestmt(" update products set status='AVAILABLE'where stock > 0");

			} catch (Exception e) {
				LOGGER.debug(Errormessage.INVALID_COLUMN_INDEX);
			}
			try {
				Jdbcpst.preparestmt(" update products set status='OUTOFSTOCK',stock=0 where stock <= 0");

			} catch (Exception e) {
				LOGGER.debug(Errormessage.INVALID_COLUMN_INDEX);
			}
			try {
				Jdbcpst.preparestmt("insert into proreview(product_id) values(" + obj.getProductId() + ")");

			} catch (Exception e) {
				LOGGER.debug(Errormessage.INVALID_COLUMN_INDEX);
			}
		}
		return a;
	}

	public void createOrder(ArrayList<UserProfile> ob, String user, String type, int id) {

		String sql2 = "select user_id from usersdata where user_name= '" + user + "'";

		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			try (ResultSet rs = stmt.executeQuery(sql2);) {
				int userId = 0;
				if (rs.next()) {
					userId = rs.getInt("user_id");
				}
				LocalDate today = LocalDate.now();
				LocalDate deliveryDate = today.plusDays(3);
				for (UserProfile obj1 : ob) {
					String sql = "select product_id,price_rs from products where product_id= ?";
					try (PreparedStatement pst = con.prepareStatement(sql);) {
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
							Jdbcpst.preparestmt("update products set status='OUT OF STOCK',stock=0 where stock<=0");

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug((Errormessage.INVALID_COLUMN_INDEX));
		}

	}

	public void updateProducts(int value, int id) {
		try {
			Jdbcpst.preparestmt("update products set stock = stock+? where product_id=?", value, id);
			Jdbcpst.preparestmt("update products set status='AVAILABLE'where stock > 0");
			Jdbcpst.preparestmt(" update products set status='OUTOFSTOCK',stock=0 where stock <= 0");
		} catch (Exception e) {
			LOGGER.error((Errormessage.INVALID_COLUMN_INDEX));
		}
	}

	public ArrayList<AdminProfile> viewProducts() {
		ArrayList<AdminProfile> view = new ArrayList<AdminProfile>();

		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
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

		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);

		}
		return view;

	}

	public int bill(ArrayList<UserProfile> ob) {
		int amount = 0;
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			for (UserProfile obj1 : ob) {
				String sql = "select price_rs from products where product_id= ?";
				try (PreparedStatement pst = con.prepareStatement(sql);) {
					pst.setInt(1, obj1.getProductid());
					try (ResultSet rs1 = pst.executeQuery();) {
						if (rs1.next()) {
							int price = rs1.getInt("price_rs");
							int totalBill = price * obj1.getNoOfItems();
							amount = amount + totalBill;
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return amount;
	}

	public int revenue(String a) {

		int total = 0;
		if (a.isEmpty()) {
			try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
				String sql = "select sum(total_amount) as total from orderdata";
				try (ResultSet rs1 = stmt.executeQuery(sql);) {
					if (rs1.next()) {
						total = rs1.getInt("total");
					}
				}
			} catch (Exception e) {
				LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
			}
		} else {
			try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
				String sql = "select sum(total_amount) as total from orderdata where payment='" + a + "'";
				try (ResultSet rs1 = stmt.executeQuery(sql);) {
					if (rs1.next()) {
						total = rs1.getInt("total");
					}
				}
			} catch (Exception e) {
				LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
			}
		}
		return total;

	}
}
