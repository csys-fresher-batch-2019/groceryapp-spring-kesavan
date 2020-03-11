package com.chainsys.grocery.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.grocery.dao.AdminProfileDao;
import com.chainsys.grocery.dao.impl.AdminProfileDaoImpl;
import com.chainsys.grocery.model.AdminProfile;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.DBException;

public class AdminService {

	private AdminProfileDao obj = new AdminProfileDaoImpl();

	public int addProducts(AdminProfile[] p) throws DBException {
		return obj.addProducts(p);
	}

	public void createOrder(ArrayList<UserProfile> ob, String user, String type, int id)
			throws DBException, SQLException {
		obj.createOrder(ob, user, type, id);
	}

	public void updateProducts(int value, int id) throws DBException {
		obj.updateProducts(value, id);
	}

	public ArrayList<AdminProfile> viewProducts() throws DBException {
		ArrayList<AdminProfile> pro = obj.viewProducts();
		return pro;
	}

	public int bill(ArrayList<UserProfile> ob) throws DBException {
		int amount = obj.bill(ob);
		return amount;
	}

	public int revenue(String a) throws DBException {
		int amount = obj.revenue(a);
		return amount;
	}

}
