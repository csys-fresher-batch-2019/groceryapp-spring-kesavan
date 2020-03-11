package com.chainsys.grocery.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.grocery.dao.UserProfileDao;
import com.chainsys.grocery.dao.impl.UserProfileDaoImpl;
import com.chainsys.grocery.model.UserDisplay;
import com.chainsys.grocery.util.DBException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api1")
public class UserController {
	UserProfileDao obj= new UserProfileDaoImpl();
	@GetMapping("/viewproducts")
	public ArrayList<UserDisplay> viewProducts(@RequestParam(" ") String a) throws DBException {
		return obj.viewProducts(a);
	}
	
}
