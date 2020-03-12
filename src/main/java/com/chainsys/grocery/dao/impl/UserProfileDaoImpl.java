package com.chainsys.grocery.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.chainsys.grocery.dao.UserProfileDao;
import com.chainsys.grocery.model.OrderSummary;
import com.chainsys.grocery.model.UserDisplay;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.DBException;
import com.chainsys.grocery.util.DatabaseConnection;
import com.chainsys.grocery.util.ErrorMessage;
import com.chainsys.grocery.util.Jdbcpst;
import com.chainsys.grocery.util.LoggerGrocery;

@SuppressWarnings("unchecked")

public class UserProfileDaoImpl implements UserProfileDao {
	LoggerGrocery LOGGER = LoggerGrocery.getInstance();

	// CREATE ACCOUNT
	public int createAccount(String user, String pass, String address, long mobile, String mail) throws DBException {
		int rows = 0;
		try (Connection con = DatabaseConnection.connect();) {
			String sql = "insert into usersdata(user_name,delivery_address,password,phone_no,mail_id) "
					+ "values(?,?,?,?,?)";
			// rows = jdbcTemplate.update(sql, user, address, pass, mobile, mail);
			rows = Jdbcpst.preparestmt(sql, user, address, pass, mobile, mail);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.INVALID_COLUMN_INDEX, e);
		}
		return rows;

	}

	// LOGIN
	public boolean login(String username, String pass) throws SQLException, DBException {
		boolean res = false;
		String sql = "select user_name,password from usersdata where user_name = ? and password =?";
		try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, username);
			pst.setString(2, pass);
			try (ResultSet rs1 = pst.executeQuery();) {
				if (rs1.next()) {
					res = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException(ErrorMessage.VERIFICATION_FAILED, e);
			}
		}
		return res;

	}

	// VIEW PRODUCTS
	public ArrayList<UserDisplay> viewProducts(String a) throws DBException {
		ArrayList<UserDisplay> products = new ArrayList<UserDisplay>();
		String sql = "select p.*,pr.review,pr.rating  from products p,proreview pr where p.product_id = pr.product_id "
				+ a;
		try (Connection con = DatabaseConnection.connect(); Statement pst = con.createStatement();) {
			try (ResultSet rs = pst.executeQuery(sql);) {
				while (rs.next()) {
					UserDisplay ap = new UserDisplay();
					ap.setProductName(rs.getString("product_name"));
					ap.setProductId(rs.getInt("product_Id"));
					ap.setManufacturer(rs.getString("manufacturer"));
					ap.setQuantity(rs.getFloat("quantity"));
					ap.setUnit(rs.getString("unit"));
					ap.setPriceRS(rs.getInt("price_rs"));
					ap.setStock(rs.getInt("stock"));
					ap.setStatus(rs.getString("status"));
					ap.setReview(rs.getString("review"));
					ap.setRating(rs.getInt("rating"));
					products.add(ap);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.NO_DATA_FOUND, e);
		}
		return products;
	}

	// PLACE ORDER

	@SuppressWarnings("rawtypes")
	public ArrayList<UserProfile> placeOrder(ArrayList selecteditems, String username, String paytype, int transId)
			throws DBException, SQLException {

		AdminProfileDaoImpl obj = new AdminProfileDaoImpl();
		obj.createOrder(selecteditems, username, paytype, transId);
		return selecteditems;

	}

	// VIEW ORDERSUMMARY
	public ArrayList<OrderSummary> viewOrder(int userid) throws DBException {
		ArrayList<OrderSummary> productsview = new ArrayList<>();
		String sql = "select order_id,product_name,manufacturer,no_of_items,price_per_item,total_amount,"
				+ "order_date,delivery_date,delivery_address,order_status,payment,transaction_id  from orderdata o "
				+ " inner join products p on p.product_id=o.product_id and user_id= ? "
				+ " inner join usersdata u on u.user_id= ? order by o.order_date desc";
		try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			LocalDate today = LocalDate.now();
			updateOrderStatusDelivered(today);
			pst.setInt(1, userid);
			pst.setInt(2, userid);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					OrderSummary os = new OrderSummary();
					os.setOrderid(rs.getInt("order_id"));
					os.setProductname(rs.getString("product_name"));
					os.setManufacturer(rs.getString("manufacturer"));
					os.setNoofitems(rs.getInt("no_of_items"));
					os.setTotalamount(rs.getInt("total_amount"));
					os.setOrderdate(rs.getDate("order_date"));
					os.setDeliverydate(rs.getDate("delivery_date"));
					os.setDeliveryaddress(rs.getString("delivery_address"));
					os.setOrderstatus(rs.getString("order_status"));
					os.setPayment(rs.getString("payment"));
					os.setTransId(rs.getInt("transaction_id"));
					productsview.add(os);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.INVALID_COLUMN_INDEX, e);
		}
		return productsview;
	}

	/**
	 * @param today
	 * @throws DBException
	 *             UPDATE ORDER STATUS IF AN ORDER DELIVERED
	 */
	private void updateOrderStatusDelivered(LocalDate today) throws DBException {
		String sql = "update orderdata set order_status = 'DELIVERED' where order_status != 'CANCELLED' and delivery_date <= ?";
		Object[] params = { Date.valueOf(today) };
		Jdbcpst.preparestmt(sql, params);
	}

	// CANCELORDER
	public String cancelOrder(int orderid) throws DBException {
		String a = "";
		String sql = "select product_id from orderdata where order_id= ? ";
		try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			UserProfileDaoImpl obj = new UserProfileDaoImpl();
			int days = obj.findDaysForCancel(orderid);
			System.out.println("days" + days);
			if (days == 0) {
				pst.setInt(1, orderid);
				try (ResultSet rs = pst.executeQuery();) {
					if (rs.next()) {
						int id = rs.getInt("product_id");
						updateStockaftercancel(orderid, id);
						updateOrderStatus(orderid);
						updateStatus();
						a = "CANCELLED SUCCESSFULLY";
					}
				}
			} else {
				a = "YOUR ORDER DISPATCHED !! NOT ABLE TO CANCEL IT";

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.NO_DATA_FOUND, e);
		}
		return a;
	}

	// UPDATE PRODUCT STOCK
	public void updateStatus() throws DBException {
		Jdbcpst.preparestmt("update products set status='AVAILABLE' where stock > 0");
		Jdbcpst.preparestmt(" update products set status='OUT OF STOCK',stock=0 where stock<=0");
	}

	// UPDATE ORDER STATUS FOR CANCELLED ORDER
	private void updateOrderStatus(int orderid) throws DBException {
		Jdbcpst.preparestmt("update  orderdata set order_status='CANCELLED' where order_id= ?", orderid);
	}

	// UPDATE STOCK AFTER AN ORDER CANCELLED
	private void updateStockaftercancel(int orderid, int pid) throws DBException {
		Jdbcpst.preparestmt(
				"update products p set p.stock=p.stock+ (select no_of_items from orderdata  where product_id = ? and order_id= ?) where p.product_id = ? ",
				pid, orderid, pid);
	}

	// TRACKORDER
	public String trackOrder(int orderid) throws DBException {
		String s = "";
		String sql = "select order_date from orderdata where order_id=?";
		try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, orderid);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					String date = rs.getString("order_date");
					String local = date.substring(0, 10);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date1 = LocalDate.parse(local, formatter);
					int n = Period.between(date1, LocalDate.now()).getDays();
					if (n == 0) {
						s = " \n !! ORDERED !! ";
					} else if (n == 1) {
						s = " \n !! DISPATCHED  WAIT FOR 2 MORE DAYS !! ";
					} else if (n == 2) {
						s = " \n !! SHIPPED WAIT FOR 1 MORE DAY !! ";
					} else if (n >= 3) {
						s = " \n !! DELIVERED !! ";
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.NO_DATA_FOUND, e);
		}
		return s;
	}

	// UNUSED CODE
	// ADD REVIEW
	/*
	 * public void Review(int orderid, int rating) { try (Connection con =
	 * Databaseconnection.connect(); Statement stmt = con.createStatement();) {
	 * String sql4 = "select product_id from orderdata where order_id=" + orderid;
	 * try (ResultSet rs1 = stmt.executeQuery(sql4);) { rs1.next(); int productId =
	 * rs1.getInt("product_id"); Jdbcpst.
	 * preparestmt("insert into review(order_id,product_id,rating)values(?,?,?)",
	 * orderid, productId, rating); String sql1 =
	 * "select avg(rating) as avrg from review where product_id=" + productId; try
	 * (ResultSet rs = stmt.executeQuery(sql1);) { rs.next(); float avg =
	 * rs.getFloat("avrg"); if (avg >= 4) { Jdbcpst.
	 * preparestmt("update proreview set review='Good',rating=? where product_id=?",
	 * avg, productId); } else if (avg >= 3 && avg < 4) { Jdbcpst.
	 * preparestmt("update proreview set review='Better',rating=? where product_id=?"
	 * , avg, productId); } else { Jdbcpst.
	 * preparestmt("update proreview set review='Bad',rating=? where product_id=?",
	 * avg, productId); }
	 * 
	 * } catch (Exception e) { LOGGER.error(Errormessage.INVALID_COLUMN_INDEX); } }
	 * catch (Exception e) { LOGGER.error(Errormessage.INVALID_COLUMN_INDEX); } }
	 * catch (Exception e) { LOGGER.error(Errormessage.CONNECTION_FAILED); } }
	 */

	// CHANGE PASSWORD
	public int changePassword(String mail, String pass) throws DBException {
		int rows = Jdbcpst.preparestmt("update usersdata set password = ? where mail_id=?", pass, mail);
		return rows;
	}

	// CHANGE ADDRESS
	public void changeAddress(String username, String address) throws DBException {
		try {
			int id = findUserId(username);
			Jdbcpst.preparestmt("update usersdata set delivery_address= ? where user_id= ?", address, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.INVALID_COLUMN_INDEX, e);
		}
	}

	// USERNAME CHECK FOR FORGOT PASSWORD
	public boolean checkUsernameForgotPassword(String username) throws DBException {
		boolean res = false;
		String sql = "select user_name from usersdata where user_name=?";
		int rows = Jdbcpst.preparestmt(sql, username);
		if (rows != 0) {
			res = true;
		}
		return res;

	}

	// DISPLAY USERID
	public int findUserId(String user) throws DBException {
		int userid = 0;
		String sql = "select user_id from usersdata where user_name= ? ";
		try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, user);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					userid = rs.getInt("user_id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.NO_DATA_FOUND, e);
		}
		return userid;

	}

	// CHECK MAIL FOR ACC CREATION
	public boolean checkMailCreate(String mail) throws DBException {
		boolean res = false;
		String sql = "select mail_id from usersdata where mail_id=?";
		int rows = Jdbcpst.preparestmt(sql, mail);
		if (rows != 0) {
			res = true;
		}
		return res;
	}

	// CHECK USERNAME FOR ACC CREATION
	public boolean checkUsernameCreate(String username) throws DBException {
		boolean res = false;
		String sql = "select user_name from usersdata where user_name=?";
		int rows = Jdbcpst.preparestmt(sql, username);
		if (rows != 0) {
			res = true;
		}
		return res;
	}

	// CHECK MOBILE NO FOR ACC CREATION
	public boolean checkMobilenoCreate(long mobile) throws DBException {
		boolean res = false;
		String sql = "select phone_no from usersdata where phone_no=?";
		int rows = Jdbcpst.preparestmt(sql, mobile);
		if (rows != 0) {
			res = true;
		}
		return res;
	}
	// unused code
	/*
	 * // CHECK FOR RATING IF ALREADY REVIEWED public boolean checkrating(int id) {
	 * boolean res = false; try (Connection con = DatabaseConnection.connect();
	 * Statement stmt = con.createStatement();) { if
	 * (stmt.executeUpdate("select order_id from review where order_id=" + id) == 0)
	 * { res = true; } } catch (Exception e) { e.printStackTrace();
	 * LOGGER.error(ErrorMessage.INVALID_COLUMN_INDEX); } return res; }
	 */

	// TRACK ORDER FOR CANCEL
	public int findDaysForCancel(int orderid) throws DBException {
		int days = 0;
		String sql = "select order_date from orderdata where order_id= ? ";
		try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, orderid);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					String date = rs.getString("order_date");
					String local = date.substring(0, 10);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date1 = LocalDate.parse(local, formatter);
					days = Period.between(date1, LocalDate.now()).getDays();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.NO_DATA_FOUND, e);
		}
		return days;
	}

	// CHECK MAIL ID FOR CHANGE PASSWORD
	public boolean forgotPassword(String mail, String user, String pass) throws DBException {
		boolean res = false;
		int rows = 0;
		String sql = "select mail_id from usersdata where user_name=?";
		try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, user);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					String mail1 = rs.getString("mail_id");
					if (mail.equals(mail1)) {
						rows = changePassword(mail, pass);
					} else {
						LOGGER.error(ErrorMessage.VERIFICATION_FAILED);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(ErrorMessage.NO_DATA_FOUND, e);
		}
		if (rows != 0) {
			res = true;
		}
		return res;

	}

	// CHECK MAIL FOR CORRESSPONDING USER WHILE USER NOT LOGGED IN [ FORGOT
	// PASSWORD]
	public boolean checkMailUser(String mail, String user) throws DBException, SQLException {
		boolean res = false;
		String sql = "select mail_id from usersdata where user_name=?";
		try (Connection con = DatabaseConnection.connect(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, user);
			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					String maildb = rs.getString("mail_id");
					if (mail.equals(maildb)) {
						res = true;
					} else {
						LOGGER.error(ErrorMessage.VERIFICATION_FAILED);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException(ErrorMessage.INVALID_COLUMN_INDEX, e);
			}
			return res;
		}

	}
}
