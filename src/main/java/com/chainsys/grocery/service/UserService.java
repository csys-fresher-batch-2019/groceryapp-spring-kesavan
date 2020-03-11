package com.chainsys.grocery.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.grocery.dao.UserProfileDao;
import com.chainsys.grocery.dao.impl.UserProfileDaoImpl;
import com.chainsys.grocery.model.OrderSummary;
import com.chainsys.grocery.model.UserDisplay;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.DBException;

public class UserService {
	private UserProfileDao obj = new UserProfileDaoImpl();

	public int CreateAccount(String user, String pass, String address, long mobile, String mail) throws DBException, SQLException {
		int id=0;
		return id=obj.CreateAccount(user, pass, address, mobile, mail);
	}

	public boolean Login(String username, String password) throws DBException, SQLException {
		boolean status = obj.Login(username, password);
		return status;
	}

	public void Forgotpassword(String mailid, String password) throws DBException {
		obj.Forgotpassword(mailid, password);
	}

	public ArrayList<UserDisplay> ViewProducts(String a) throws DBException {
		ArrayList<UserDisplay> products = obj.ViewProducts(a);
		return products;
	}

	public ArrayList<UserProfile> PlaceOrder(ArrayList<?> o, String username, String payment, int Transactionid) throws DBException, SQLException {
		ArrayList<UserProfile> order = obj.PlaceOrder(o, username, payment, Transactionid);
		return order;
	}

	public ArrayList<OrderSummary> ViewOrder(int userid) throws DBException {
		ArrayList<OrderSummary> view = obj.ViewOrder(userid);
		return view;
	}

	/*public void Review(int id, int rating) {
		obj.Review(id, rating);
	}*/

	public String Cancelorder(int orderid) throws DBException {
		String res = obj.cancelOrder(orderid);
		return res;
	}

	public String Trackorder(int orderid) throws DBException {
		String track = obj.Trackorder(orderid);
		return track;
	}

	public int Trackordercancel(int orderid) throws DBException {
		int cancel = obj.Trackordercancel(orderid);
		return cancel;
	}

	public boolean checkusername(String username) throws DBException {
		boolean res = obj.checkusername(username);
		return res;
	}


	/*public boolean checkproduct(int product) {
		boolean res = obj.checkproduct(product);
		return res;
	}

	public boolean checkstock(int noofitems, int product) {
		boolean res = obj.checkstock(noofitems, product);
		return res;

	}*/

	public int checkuserid(String user) throws DBException {
		int id = obj.checkuserid(user);
		return id;
	}

	/*public boolean checkorderid(int orderid) {
		boolean res = obj.checkorderid(orderid);
		return res;

	}*/

	

	public boolean checkmailpass(String mail, String user, String pass) throws DBException {
		boolean res = obj.checkMailPass(mail, user, pass);
		return res;

	}

	public void changeaddress(String username, String address) throws DBException {
		obj.changeAddress(username, address);
	}

	public boolean checkmailuser(String mail, String user) throws DBException, SQLException {
		boolean res = obj.checkmailuser(mail, user);
		return res;

	}

	// Acc creation
	public boolean checkmailcreate(String mail) throws DBException {
		boolean res = obj.checkMailCreate(mail);
		return res;
	}

	public boolean checkusernamecreate(String username) throws DBException {
		boolean res = obj.checkusernamecreate(username);
		return res;
	}

	public boolean checkmobilenocreate(long mobile) throws DBException {
		boolean res = obj.checkmobilenocreate(mobile);
		return res;
	}

}
