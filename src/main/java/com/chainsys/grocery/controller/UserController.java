package com.chainsys.grocery.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.grocery.dao.UserProfileDao;
import com.chainsys.grocery.dao.impl.UserProfileDaoImpl;
import com.chainsys.grocery.model.OrderSummary;
import com.chainsys.grocery.model.UserDisplay;
import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.util.DBException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("apiuser")
public class UserController {
	UserProfileDao obj = new UserProfileDaoImpl();

	@PostMapping("/register")
	int createAccount(@RequestParam("username") String user, @RequestParam("Password") String pass,
			@RequestParam("address") String address, @RequestParam("mobile") long mobile,
			@RequestParam("mailId") String mail) throws SQLException, DBException {
		return obj.createAccount(user, pass, address, mobile, mail);

	}

	@GetMapping("/login")
	public boolean login(@RequestParam("username") String user, @RequestParam("password") String pass)
			throws DBException, SQLException {
		return obj.login(user, pass);
	}

	@PostMapping("/changepassword")
	public int forgotpassword(@RequestParam("mailid") String mailid, @RequestParam("password") String password)
			throws DBException {
		return obj.changePassword(mailid, password);
	}

	@GetMapping("/viewproducts")
	public List<UserDisplay> viewProducts(@RequestParam("condition") String a) throws DBException {
		return obj.viewProducts(a);
	}

	@PostMapping("/placeorder")
	public void placeorder(@RequestBody ArrayList<UserProfile> items, @RequestParam("user") String username,
			@RequestParam("payment") String payment, @RequestParam("transid") int transactionid)
			throws DBException, SQLException {
		obj.placeOrder(items, username, payment, transactionid);
	}

	@GetMapping("/vieworders")
	public ArrayList<OrderSummary> viewOrder(@RequestParam("userid") int userid) throws DBException {
		return obj.viewOrder(userid);
	}

	@PostMapping("/updatestatus")
	public void updateStatus() throws DBException {
		obj.updateStatus();
	}

	@PostMapping("/cancelorder")
	public String cancelOrder(@RequestParam("orderid") int orderid) throws DBException {
		return obj.cancelOrder(orderid);
	}

	@GetMapping("/trackorder")
	public String trackOrder(@RequestParam("orderid") int orderid) throws DBException {
		return obj.trackOrder(orderid);
	}

	@GetMapping("/finddaysforcancel")
	public int trackOrderCancel(@RequestParam("orderid") int orderid) throws DBException {
		return obj.findDaysForCancel(orderid);
	}

	@GetMapping("/displayuserid")
	public int findUserId(String user) throws DBException {
		return obj.findUserId(user);
	}

	@PostMapping("/changeaddress")
	public void changeAddress(String username, String address) throws DBException {
		obj.changeAddress(username, address);
	}

	@GetMapping("/uservalidation") // FOR FORGOT OR CHANGE PASSWORD
	public boolean userValidation(String mail, String user, String pass) throws DBException {
		return false;
	}

	// FOR ACC CREATION

	@GetMapping("/checkmailAccCeation")
	public boolean checkMailCreate(String mail) throws DBException {
		return obj.checkMailCreate(mail);
	}

	@GetMapping("/checkuserAccCreation")
	public boolean checkUsernameCreate(String username) throws DBException {
		return obj.checkUsernameCreate(username);

	}

	@GetMapping("/checkmobileAccCreation")
	public boolean checkMobilenoCreate(long mobile) throws DBException {
		return obj.checkMobilenoCreate(mobile);
	}

}
