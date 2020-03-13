package com.chainsys.grocery.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.grocery.dao.AdminProfileDao;
import com.chainsys.grocery.dao.impl.AdminProfileDaoImpl;
import com.chainsys.grocery.model.AdminProfile;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.DBException;
import com.chainsys.grocery.util.ErrorMessage;
import com.chainsys.grocery.util.ServiceException;

public class AdminService {

	private AdminProfileDao obj = new AdminProfileDaoImpl();

	public int addProducts(AdminProfile[] p) throws ServiceException {
		int res = 0;
		try {
			res = obj.addProducts(p);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);
		}
		return res;
	}

	public void createOrder(ArrayList<UserProfile> ob, String user, String type, int id) throws ServiceException {
		try {
			obj.createOrder(ob, user, type, id);
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);
		}
	}

	public void updateProducts(int value, int id) throws ServiceException {
		try {
			obj.updateProducts(value, id);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);
		}
	}

	public ArrayList<AdminProfile> viewProducts() throws ServiceException {
		ArrayList<AdminProfile> pro = null;
		try {
			pro = obj.viewProducts();
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);
		}
		return pro;
	}

	public int bill(ArrayList<UserProfile> ob) throws ServiceException {
		int amount = 0;
		try {
			amount = obj.bill(ob);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);
		}
		return amount;
	}

	public int revenue(String a) throws ServiceException {
		int amount = 0;
		try {
			amount = obj.revenue(a);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);
		}
		return amount;
	}

}
