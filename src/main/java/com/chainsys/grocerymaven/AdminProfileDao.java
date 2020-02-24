package com.chainsys.grocerymaven;

import java.util.ArrayList;

public interface AdminProfileDao {
	
	int addProducts(AdminProfile[] p);
	
	
	void createOrder(ArrayList<UserProfile> o, String user, String pay, int id);

	void updateProducts(int value, int id);
	
	//@SqlQuery("select * from products")
	ArrayList<AdminProfile> viewProducts();
	
	//@SqlQuery("select price_rs from products where product_id= ?")
	int bill(ArrayList<UserProfile> ob);
	
	int revenue(String a);
}
