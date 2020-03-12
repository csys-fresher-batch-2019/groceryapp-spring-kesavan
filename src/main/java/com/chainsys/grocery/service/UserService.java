package com.chainsys.grocery.service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.chainsys.grocery.dao.UserProfileDao;
import com.chainsys.grocery.dao.impl.UserProfileDaoImpl;
import com.chainsys.grocery.model.OrderSummary;
import com.chainsys.grocery.model.UserDisplay;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.DBException;
import com.chainsys.grocery.util.ErrorMessage;
import com.chainsys.grocery.util.ServiceException;

public class UserService {
	private UserProfileDao obj = new UserProfileDaoImpl();

	public int createAccount(String user, String pass, String address, long mobile, String mail)
			throws ServiceException {
		int id = 0;
		try {
			id = obj.createAccount(user, pass, address, mobile, mail);
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);
		}
		return id;
	}

	public boolean login(String username, String password) throws ServiceException {
		boolean status = false;
		try {
			status = obj.login(username, password);
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);
		}
		return status;
	}

	public void forgotpassword(String mailid, String password) throws ServiceException {
		try {
			obj.changePassword(mailid, password);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
	}

	public ArrayList<UserDisplay> viewProducts(String a) throws ServiceException {
		ArrayList<UserDisplay> products = null;
		try {
			products = obj.viewProducts(a);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
		return products;
	}

	public ArrayList<UserProfile> placeOrder(ArrayList<?> o, String username, String payment, int Transactionid)
			throws ServiceException {
		ArrayList<UserProfile> order = null;
		try {
			order = obj.placeOrder(o, username, payment, Transactionid);
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
		return order;
	}

	public ArrayList<OrderSummary> viewOrder(int userid) throws ServiceException {
		ArrayList<OrderSummary> view = null;
		try {
			view = obj.viewOrder(userid);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
		return view;
	}


	public String cancelOrder(int orderid) throws ServiceException {
		String res = "";
		try {
			res = obj.cancelOrder(orderid);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
		return res;
	}

	public String trackorder(int orderid) throws ServiceException {
		String track = "";
		try {
			track = obj.trackOrder(orderid);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
		return track;
	}

	public int trackOrderCancel(int orderid) throws ServiceException {
		int cancel = 0;
		try {
			cancel = obj.findDaysForCancel(orderid);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
		return cancel;
	}


	public int checkUserId(String user) throws ServiceException {
		int id = 0;
		try {
			id = obj.findUserId(user);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
		return id;
	}

	public void changeAddress(String username, String address) throws ServiceException {
		try {
			obj.changeAddress(username, address);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
	}

	public boolean userValidation(String mail, String user, String pass) throws ServiceException {
		boolean res = false;
		try {
			res = obj.userValidation(mail, user, pass);
		} catch (DBException e) {
			e.printStackTrace();
		}
		return res;
	}

	// Acc creation
	public boolean checkMailCreate(String mail) throws ServiceException {
		boolean res = false;
		try {
			res = obj.checkMailCreate(mail);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
		return res;
	}

	public boolean checkUserNameCreate(String username) throws ServiceException {
		boolean res = false;
		try {
			res = obj.checkUsernameCreate(username);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
		return res;
	}

	public boolean checkMobileNoCreate(long mobile) throws ServiceException {
		boolean res = false;
		try {
			res = obj.checkMobilenoCreate(mobile);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.SERVICE, e);

		}
		return res;
	}

}
