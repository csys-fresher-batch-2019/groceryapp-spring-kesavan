package com.chainsys.grocery.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.grocery.dao.AdminProfileDao;
import com.chainsys.grocery.dao.impl.AdminProfileDaoImpl;
import com.chainsys.grocery.model.AdminProfile;
import com.chainsys.grocery.util.DBException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("apiadmin")
@SuppressWarnings("unused")

public class ProductController {

	AdminProfileDao obj = new AdminProfileDaoImpl();

	@PostMapping("/addproduct")
	public void addProduct(@RequestParam("pname") String pname, @RequestParam("pid") Integer pid,
			@RequestParam("company") String company, @RequestParam("quantity") Float quantity,
			@RequestParam("units") String units, @RequestParam("price") Integer price,
			@RequestParam("stock") Integer stock) throws DBException {
		AdminProfile ob = new AdminProfile();
		ob.setProductName(pname);
		ob.setProductId(pid);
		ob.setManufacturer(company);
		ob.setQuantity(quantity);
		ob.setUnit(units);
		ob.setPriceRS(price);
		ob.setStock(stock);
		AdminProfile[] p = { ob };
		int a = obj.addProducts(p);
	}

	@PostMapping("/addproduct1")
	public void addProduct1(@RequestBody AdminProfile addproducts, @RequestParam("pname") String pname,
			@RequestParam("pid") Integer pid) throws DBException {
		AdminProfile[] p = { addproducts };
		int a = obj.addProducts(p);
	}

	@GetMapping("/viewproducts")
	public ArrayList<AdminProfile> viewProducts() throws DBException {
		return obj.viewProducts();
	}

	@PostMapping("/updateproducts")
	public void updateProducts(@RequestParam("pid") Integer pid, @RequestParam("stock") Integer stock)
			throws DBException {
		obj.updateProducts(stock, pid);
	}

}
