package com.chainsys.grocery.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocery.service.AdminService;
import com.chainsys.grocery.util.ServiceException;

@WebServlet("/updatestockserv")
public class updatestockserv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("pid"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		AdminService obj = new AdminService();
		try {
			obj.updateProducts(stock, id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("adminproviewservlet");
	}

}
