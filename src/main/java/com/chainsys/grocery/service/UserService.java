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

	public int createAccount(String user, String pass, String address, long mobile, String mail) throws DBException, SQLException {
		int id=0;
		return id=obj.createAccount(user, pass, address, mobile, mail);
	}

	public boolean login(String username, String password) throws DBException, SQLException {
		boolean status = obj.login(username, password);
		return status;
	}

	public void forgotpassword(String mailid, String password) throws DBException {
		obj.forgotPassword(mailid, password);
	}

	public ArrayList<UserDisplay> viewProducts(String a) throws DBException {
		ArrayList<UserDisplay> products = obj.viewProducts(a);
		return products;
	}

	public ArrayList<UserProfile> placeOrder(ArrayList<?> o, String username, String payment, int Transactionid) throws DBException, SQLException {
		ArrayList<UserProfile> order = obj.placeOrder(o, username, payment, Transactionid);
		return order;
	}

	public ArrayList<OrderSummary> viewOrder(int userid) throws DBException {
		ArrayList<OrderSummary> view = obj.viewOrder(userid);
		return view;
	}

	/*public void Review(int id, int rating) {
		obj.Review(id, rating);
	}*/

	public String cancelOrder(int orderid) throws DBException {
		String res = obj.cancelOrder(orderid);
		return res;
	}

	public String trackorder(int orderid) throws DBException {
		String track = obj.trackOrder(orderid);
		return track;
	}

	public int trackOrderCancel(int orderid) throws DBException {
		int cancel = obj.trackOrderCancel(orderid);
		return cancel;
	}

	public boolean checkUserName(String username) throws DBException {
		boolean res = obj.checkUsername(username);
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

	public int checkUserId(String user) throws DBException {
		int id = obj.checkUserId(user);
		return id;
	}

	/*public boolean checkorderid(int orderid) {
		boolean res = obj.checkorderid(orderid);
		return res;

	}*/

	

	public boolean checkMailPass(String mail, String user, String pass) throws DBException {
		boolean res = obj.checkMailPass(mail, user, pass);
		return res;

	}

	public void changeAddress(String username, String address) throws DBException {
		obj.changeAddress(username, address);
	}

	public boolean checkMailUser(String mail, String user) throws DBException, SQLException {
		boolean res = obj.checkMailUser(mail, user);
		return res;

	}

	// Acc creation
	public boolean checkMailCreate(String mail) throws DBException {
		boolean res = obj.checkMailCreate(mail);
		return res;
	}

	public boolean checkUserNameCreate(String username) throws DBException {
		boolean res = obj.checkUsernameCreate(username);
		return res;
	}

	public boolean checkMobileNoCreate(long mobile) throws DBException {
		boolean res = obj.checkMobilenoCreate(mobile);
		return res;
	}

}
