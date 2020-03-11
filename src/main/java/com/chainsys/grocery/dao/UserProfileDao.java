package com.chainsys.grocery.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.grocery.model.OrderSummary;
import com.chainsys.grocery.model.UserDisplay;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.DBException;

public interface UserProfileDao {

	int CreateAccount(String user, String pass, String address, long mobile, String mail)
			throws SQLException, DBException;

	boolean Login(String username, String password) throws DBException, SQLException;

	int Forgotpassword(String mailid, String password) throws DBException;

	ArrayList<UserDisplay> ViewProducts(String a) throws DBException;

	ArrayList<UserProfile> PlaceOrder(ArrayList<?> o, String username, String payment, int Transactionid)
			throws DBException, SQLException;

	ArrayList<OrderSummary> ViewOrder(int userid) throws DBException;

	// void Review(int id, int rating);
	
	public void updateStatus() throws DBException ;


	String cancelOrder(int orderid) throws DBException;

	String Trackorder(int orderid) throws DBException;

	int Trackordercancel(int orderid) throws DBException;

	boolean checkusername(String username) throws DBException;

	int checkuserid(String user) throws DBException;

	boolean checkMailPass(String mail, String user, String pass) throws DBException;

	void changeAddress(String username, String address) throws DBException;

	boolean checkmailuser(String mail, String user) throws DBException, SQLException;

	// Acc creation validation
	boolean checkMailCreate(String mail) throws DBException;

	boolean checkusernamecreate(String username) throws DBException;

	boolean checkmobilenocreate(long mobile) throws DBException;
}
