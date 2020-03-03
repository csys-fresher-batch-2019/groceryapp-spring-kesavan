package com.chainsys.grocery.service;

import java.util.ArrayList;

import com.chainsys.grocery.dao.UserProfileDao;
import com.chainsys.grocery.dao.impl.UserProfileDaoImpl;
import com.chainsys.grocery.model.Ordersummary;
import com.chainsys.grocery.model.UserDisplay;
import com.chainsys.grocery.model.UserProfile;

public class UserService {
	private UserProfileDao obj = new UserProfileDaoImpl();

	public int CreateAccount(String user, String pass, String address, long mobile, String mail) {
		int id = obj.CreateAccount(user, pass, address, mobile, mail);
		return id;
	}

	public boolean Login(String username, String password) {
		boolean status = obj.Login(username, password);
		return status;
	}

	public void Forgotpassword(String mailid, String password) {
		obj.Forgotpassword(mailid, password);
	}

	public ArrayList<UserDisplay> ViewProducts(String a) {
		ArrayList<UserDisplay> products = obj.ViewProducts(a);
		return products;
	}

	public ArrayList<UserProfile> PlaceOrder(ArrayList<?> o, String username, String payment, int Transactionid) {
		ArrayList<UserProfile> order = obj.PlaceOrder(o, username, payment, Transactionid);
		return order;
	}

	public ArrayList<Ordersummary> ViewOrder(int userid) {
		ArrayList<Ordersummary> view = obj.ViewOrder(userid);
		return view;
	}

	public void Review(int id, int rating) {
		obj.Review(id, rating);
	}

	public String Cancelorder(int orderid) {
		String res = obj.Cancelorder(orderid);
		return res;
	}

	public String Trackorder(int orderid) {
		String track = obj.Trackorder(orderid);
		return track;
	}

	public int Trackordercancel(int orderid) {
		int cancel = obj.Trackordercancel(orderid);
		return cancel;
	}

	public boolean checkusername(String username) {
		boolean res = obj.checkusername(username);
		return res;
	}

	public boolean checkmobileno(long mobile) {
		boolean res = obj.checkmobileno(mobile);
		return res;
	}

}
