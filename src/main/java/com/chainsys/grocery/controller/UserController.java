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
@RequestMapping("api1")
public class UserController {
	UserProfileDao obj = new UserProfileDaoImpl();

	@GetMapping("/login")
	public boolean login(@RequestParam("username") String user, @RequestParam("password") String pass)
			throws DBException, SQLException {
		boolean res = obj.login(user, pass);
		return res;
	}

	@GetMapping("/forgotpassword")
	public int forgotpassword(@RequestParam("mailid") String mailid, @RequestParam("password") String password)
			throws DBException {
		return obj.forgotPassword(mailid, password);
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

	@PostMapping("/trackorder")
	public String trackOrder(@RequestParam("orderid") int orderid) throws DBException {
		return obj.trackOrder(orderid);
	}

	@GetMapping("/finddaysforcancel")
	public int trackOrderCancel(@RequestParam("orderid") int orderid) throws DBException {
		return obj.findDaysForCancel(orderid);
	}

	@GetMapping("/checkuserforgotpassword")
	public boolean checkuserforgotpassword(@RequestParam("username") String username) throws DBException {
		return obj.checkUsernameForgotPassword(username);
	}

	@GetMapping("/checkuserid")

	public int checkUserId(String user) throws DBException {
		return 0;

	}

}
