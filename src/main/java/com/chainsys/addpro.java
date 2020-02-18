package com.chainsys;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocerymaven.AdminProfile;
import com.chainsys.grocerymaven.AdminProfileDao;
import com.chainsys.grocerymaven.AdminProfileDaoImpl;

public class addpro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminProfile ob=new AdminProfile();
		ob.setProductName(request.getParameter("pname"));
		ob.setProductId(Integer.parseInt(request.getParameter("pid").toString()));
		ob.setManufacturer(request.getParameter("company"));
		ob.setQuantity(Float.parseFloat(request.getParameter("quantity").toString()));
		ob.setUnit(request.getParameter("units"));
		ob.setPriceRS(Integer.parseInt(request.getParameter("price").toString()));
		ob.setStock(Integer.parseInt(request.getParameter("stock").toString()));
		AdminProfile[] p = {ob };
		AdminProfileDao obj=new AdminProfileDaoImpl();
		obj.addProducts(p);
		
	}

}
