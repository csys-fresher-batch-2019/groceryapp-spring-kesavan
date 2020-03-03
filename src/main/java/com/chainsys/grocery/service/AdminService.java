package com.chainsys.grocery.service;

import java.util.ArrayList;

import com.chainsys.grocery.dao.AdminProfileDao;
import com.chainsys.grocery.dao.impl.AdminProfileDaoImpl;
import com.chainsys.grocery.model.AdminProfile;
import com.chainsys.grocery.model.UserProfile;

public class AdminService {

	private AdminProfileDao obj = new AdminProfileDaoImpl();

	public void addProducts(AdminProfile[] p) {
		obj.addProducts(p);
	}

	
	public void createOrder(ArrayList<UserProfile> ob, String user, String type, int id) {
		obj.createOrder(ob, user, type, id);
	}

	public void updateProducts(int value, int id) {
		obj.updateProducts(value, id);
	}

	public ArrayList<AdminProfile> viewProducts() {
		ArrayList<AdminProfile> pro = obj.viewProducts();
		return pro;
	}

	public int bill(ArrayList<UserProfile> ob) {
		int amount = obj.bill(ob);
		return amount;
	}
}
