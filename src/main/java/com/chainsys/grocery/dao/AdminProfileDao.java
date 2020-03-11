package com.chainsys.grocery.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.grocery.model.AdminProfile;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.DBException;

public interface AdminProfileDao {
	
	int addProducts(AdminProfile[] p) throws DBException;
	
	
	void createOrder(ArrayList<UserProfile> o, String user, String pay, int id) throws DBException, SQLException;

	void updateProducts(int value, int id) throws DBException;
	
	//@SqlQuery("select * from products")
	ArrayList<AdminProfile> viewProducts() throws DBException;
	
	//@SqlQuery("select price_rs from products where product_id= ?")
	int bill(ArrayList<UserProfile> ob) throws DBException;
	
	int revenue(String a) throws DBException;
}
