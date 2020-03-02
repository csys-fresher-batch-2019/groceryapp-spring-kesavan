package com.chainsys.grocerymaven;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.chainsys.util.Errormessage;
import com.chainsys.util.Jdbcpst;
import com.chainsys.util.LoggerGrocery;
import com.chainsys.util.Databaseconnection;

public class UserProfileDaoImpl implements UserProfileDao {
	LoggerGrocery LOGGER = LoggerGrocery.getInstance();

	// CREATE ACCOUNT
	public int CreateAccount(String user, String pass, String address, long mobile, String mail) {
		int id = 0;
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "insert into usersdata(user_name,delivery_address,password,phone_no,mail_id) "
					+ "values(?,?,?,?,?)";
			Jdbcpst.preparestmt(sql, user, address, pass, mobile, mail);
			String sql1 = "select user_id from usersdata where user_name='" + user + "'";

			try (ResultSet rs = stmt.executeQuery(sql1);) {
				if (rs.next()) {
					id = rs.getInt("user_id");
				} else {
					System.out.println("User not exists");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return id;

	}

	// LOGIN
	public boolean Login(String username, String pass) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select user_name,password from usersdata where user_name = '" + username
					+ "' and password = '" + pass + "'";
			try (ResultSet rs1 = stmt.executeQuery(sql);) {
				if (rs1.next()) {
					return true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return false;

	}

	// VIEW PRODUCTS
	public ArrayList<UserDisplay> ViewProducts(String a) {
		ArrayList<UserDisplay> products = new ArrayList<UserDisplay>();
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select p.*,pr.review,pr.rating  from products p,proreview pr where p.product_id= pr.product_id "
					+ a;
			try (ResultSet rs = stmt.executeQuery(sql);) {
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

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return products;

	}

	// PLACE ORDER
	public ArrayList<UserProfile> PlaceOrder(ArrayList a, String username, String type, int transId) {
		try {
			AdminProfileDaoImpl obj = new AdminProfileDaoImpl();
			obj.createOrder(a, username, type, transId);
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return a;

	}

	// VIEW ORDERSUMMARY
	public ArrayList<Ordersummary> ViewOrder(int userid) {
		ArrayList<Ordersummary> productsview = new ArrayList<>();
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			LocalDate today = LocalDate.now();
			String sql4 = "update orderdata set order_status = 'DELIVERED' where order_status != 'CANCELLED' and delivery_date <= ?";
			Object[] params = { Date.valueOf(today) };
			Jdbcpst.preparestmt(sql4, params);

			String sql = "select order_id,product_name,manufacturer,no_of_items,price_per_item,total_amount,"
					+ "order_date,delivery_date,delivery_address,order_status,payment,transaction_id  from orderdata o "
					+ " inner join products p on p.product_id=o.product_id and user_id=" + userid + ""
					+ " inner join usersdata u on u.user_id=" + userid + " order by o.order_date desc";
			try (ResultSet rs = stmt.executeQuery(sql);) {
				while (rs.next()) {
					Ordersummary os = new Ordersummary();
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

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return productsview;
	}

	// CANCELORDER
	public String Cancelorder(int orderid) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			UserProfileDaoImpl obj = new UserProfileDaoImpl();
			int days = obj.Trackordercancel(orderid);
			if (days == 0) {
				String sql2 = "select product_id from orderdata where order_id=" + orderid;
				try (ResultSet rs = stmt.executeQuery(sql2);) {
					rs.next();
					int id = rs.getInt("product_id");
					Jdbcpst.preparestmt(
							"update products p set p.stock=p.stock+ (select no_of_items from orderdata  where product_id = "
									+ id + " and order_id=" + orderid + ") where p.product_id = " + id + "");
					Jdbcpst.preparestmt(
							"update  orderdata set order_status='CANCELLED' where order_id= ?", orderid);
					Jdbcpst.preparestmt("update products set status='AVAILABLE'where stock > 0");
					Jdbcpst.preparestmt(" update products set status='OUTOFSTOCK'where stock <= 0");

				}
			} else {
				return "YOUR ORDER DISPATCHED !! NOT ABLE TO CANCEL IT";
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return "CANCELLED SUCCESSFULLY";
	}

	// TRACKORDER
	public String Trackorder(int orderid) {
		String s = "";
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select order_date from orderdata where order_id=" + orderid;
			try (ResultSet rs = stmt.executeQuery(sql);) {
				rs.next();
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
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return s;
	}

	// ADD REVIEW
	public void Review(int orderid, int rating) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql4 = "select product_id from orderdata where order_id=" + orderid;
			try (ResultSet rs1 = stmt.executeQuery(sql4);) {
				rs1.next();
				int productId = rs1.getInt("product_id");
				Jdbcpst.preparestmt("insert into review(order_id,product_id,rating)values(?,?,?)", orderid, productId,
						rating);
				String sql1 = "select avg(rating) as avrg from review where product_id=" + productId;
				try (ResultSet rs = stmt.executeQuery(sql1);) {
					rs.next();
					float avg = rs.getFloat("avrg");
					if (avg >= 4) {
						Jdbcpst.preparestmt("update proreview set review='Good',rating=? where product_id=?", avg,
								productId);
					} else if (avg >= 3 && avg < 4) {
						Jdbcpst.preparestmt("update proreview set review='Better',rating=? where product_id=?", avg,
								productId);
					} else {
						Jdbcpst.preparestmt("update proreview set review='Bad',rating=? where product_id=?", avg,
								productId);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
	}

	// CHANGE PASSWORD
	public void Forgotpassword(String mail, String pass) {
		try {
			Jdbcpst.preparestmt("update usersdata set password = ? where mail_id=?", pass, mail);
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
	}

	// CHANGE ADDRESS
	public void changeaddress(String username, String address) {
		try {
			UserProfileDaoImpl obj = new UserProfileDaoImpl();
			int id = obj.checkuserid(username);
			Jdbcpst.preparestmt("update usersdata set delivery_address= ? where user_id= ?", address, id);
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
	}

	// USERNAME CHECK FOR FORGOT PASSWORD
	public boolean checkusername(String username) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			if (stmt.executeUpdate("select user_name from usersdata where user_name='" + username + "'") != 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return false;

	}

	// MOBILE NO CHECK
	public boolean checkmobileno(long mobile) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			if (stmt.executeUpdate("select phone_no from usersdata where phone_no='" + mobile + "'") == 1) {
				return false;
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return true;

	}

	// CHECK VALID PRODUCT
	public boolean checkproduct(int product) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			if (stmt.executeUpdate("select product_id from products where product_id=" + product) != 1) {
				return false;
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return true;

	}

	// CHECK STOCK
	public boolean checkstock(int noofitems, int product) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			try (ResultSet rs = stmt.executeQuery("select stock from products where product_id =" + product);) {
				rs.next();
				int stock = rs.getInt("stock");
				if (noofitems <= stock) {
					return true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);

		}
		return false;
	}

	// DISPLAY USERID
	public int checkuserid(String user) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select user_id from usersdata where user_name='" + user + "'";
			try (ResultSet rs = stmt.executeQuery(sql);) {
				if (rs.next()) {
					int user1 = rs.getInt("user_id");
					return user1;
				}
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);

		}
		return 0;

	}

	// CHECK ORDERID
	public boolean checkorderid(int orderid) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			if (stmt.executeUpdate("select order_id from orderdata where order_id='" + orderid + "'") == 1) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);

		}
		return false;

	}

	// CHECKID
	public boolean checkid(int userid) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			if (stmt.executeUpdate("select user_id from usersdata where user_id=" + userid) != 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);

		}
		return false;

	}

	// CHECK MAIL
	public boolean checkmail(String mail) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			if (stmt.executeUpdate("select mail_id from usersdata where mail_id='" + mail + "'") != 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);

		}
		return false;

	}

	// CHECK MAIL FOR ACC CREATION
	public boolean checkmailcreate(String mail) {

		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select mail_id from usersdata where mail_id='" + mail + "'";
			try (ResultSet rs = stmt.executeQuery(sql)) {
				if (rs.next()) {
					return true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return false;
	}

	// CHECK USERNAME FOR ACC CREATION
	public boolean checkusernamecreate(String username) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select user_name from usersdata where user_name='" + username + "'";
			try (ResultSet rs = stmt.executeQuery(sql)) {
				if (rs.next()) {
					return true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return false;
	}

	// CHECK MOBILE NO FOR ACC CREATION
	public boolean checkmobilenocreate(long mobile) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select phone_no from usersdata where phone_no='" + mobile + "'";
			try (ResultSet rs = stmt.executeQuery(sql)) {
				if (rs.next()) {
					return true;
				}

			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);

		}
		return false;
	}

	// CHECK FOR RATING IF ALREADY REVIEWED
	public boolean checkrating(int id) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			if (stmt.executeUpdate("select order_id from review where order_id=" + id) == 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return false;
	}

	// TRACK FOR CAMCEL
	public int Trackordercancel(int orderid) {
		int n = 0;
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select order_date from orderdata where order_id=" + orderid;
			stmt.executeUpdate(sql);
			try (ResultSet rs = stmt.executeQuery(sql);) {
				rs.next();
				String date = rs.getString("order_date");
				String local = date.substring(0, 10);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date1 = LocalDate.parse(local, formatter);
				n = Period.between(date1, LocalDate.now()).getDays();
			}
		} catch (Exception e) {
			LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
		}
		return n;
	}

	// CHANGE PASSWORD WITH MAIL CONFIRM
	public boolean checkmailpass(String mail, String user, String pass) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select mail_id from usersdata where user_name='" + user + "'";
			UserProfileDaoImpl obj = new UserProfileDaoImpl();
			boolean a = obj.checkusername(user);
			boolean b = obj.checkusername(user);

			try (ResultSet rs = stmt.executeQuery(sql);) {
				rs.next();
				String mail1 = rs.getString("mail_id");
				if (mail.equals(mail1)) {
					try {
						Jdbcpst.preparestmt("update usersdata set password = ? where mail_id=?", pass, mail);
						return true;
					} catch (Exception e) {
						LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);
					}
				} else {
					LOGGER.error(Errormessage.NO_DATA_FOUND);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(Errormessage.NO_DATA_FOUND);

		}
		return false;

	}

	// CHECK MAIL FOR CORRESSPONDING USER
	public boolean checkmailuser(String mail, String user) {
		try (Connection con = Databaseconnection.connect(); Statement stmt = con.createStatement();) {
			String sql = "select mail_id from usersdata where user_name='" + user + "'";
			try (ResultSet rs = stmt.executeQuery(sql);) {
				rs.next();
				String maildb = rs.getString("mail_id");
				if (mail.equals(maildb)) {
					System.out.println(" try true");
					return true;
				} else {
					System.out.println("try false");
					return false;
				}
			} catch (SQLException e1) {
				LOGGER.error(Errormessage.INVALID_COLUMN_INDEX);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
