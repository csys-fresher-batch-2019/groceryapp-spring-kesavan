package com.chainsys.grocery.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.grocery.model.OrderSummary;
import com.chainsys.grocery.model.UserDisplay;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.DBException;

public interface UserProfileDao {

	int createAccount(String user, String pass, String address, long mobile, String mail)
			throws SQLException, DBException;

	boolean login(String username, String password) throws DBException, SQLException;

	int changePassword(String mailid, String password) throws DBException;

	ArrayList<UserDisplay> viewProducts(String a) throws DBException;

	ArrayList<UserProfile> placeOrder(ArrayList<?> o, String username, String payment, int Transactionid)
			throws DBException, SQLException;

	ArrayList<OrderSummary> viewOrder(int userid) throws DBException;

	public void updateStatus() throws DBException;

	String cancelOrder(int orderid) throws DBException;

	String trackOrder(int orderid) throws DBException;

	int findDaysForCancel(int orderid) throws DBException;

	boolean checkUsernameForgotPassword(String username) throws DBException;

	int findUserId(String user) throws DBException;

	boolean forgotPassword(String mail, String user, String pass) throws DBException;

	void changeAddress(String username, String address) throws DBException;

	boolean checkMailUser(String mail, String user) throws DBException, SQLException;

	// Acc creation validation
	boolean checkMailCreate(String mail) throws DBException;

	boolean checkUsernameCreate(String username) throws DBException;

	boolean checkMobilenoCreate(long mobile) throws DBException;
	
	// void Review(int id, int rating);

}
