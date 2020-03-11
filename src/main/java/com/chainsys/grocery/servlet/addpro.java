package com.chainsys.grocery.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocery.dao.AdminProfileDao;
import com.chainsys.grocery.dao.impl.AdminProfileDaoImpl;
import com.chainsys.grocery.model.AdminProfile;
import com.chainsys.grocery.service.AdminService;
import com.chainsys.grocery.util.DBException;
@WebServlet("/addpro")

public class addpro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminService as=new AdminService();
		AdminProfile ob=new AdminProfile();
		ob.setProductName(request.getParameter("pname"));
		ob.setProductId(Integer.parseInt(request.getParameter("pid").toString()));
		ob.setManufacturer(request.getParameter("company"));
		ob.setQuantity(Float.parseFloat(request.getParameter("quantity").toString()));
		ob.setUnit(request.getParameter("units"));
		ob.setPriceRS(Integer.parseInt(request.getParameter("price").toString()));
		ob.setStock(Integer.parseInt(request.getParameter("stock").toString()));
		AdminProfile[] p = {ob};
		int a = 0;
		try {
			a = as.addProducts(p);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a);
		if(a==1) {
		String r="true";
		RequestDispatcher d = request.getRequestDispatcher("Addproducts.jsp?result="+r);
		d.forward(request, response);
		}else {
			String r="fail";
			RequestDispatcher d = request.getRequestDispatcher("Addproducts.jsp?result="+r);
			d.forward(request, response);
		}
	}

}
