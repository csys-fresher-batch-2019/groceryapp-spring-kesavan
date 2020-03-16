package com.chainsys.grocery.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocery.model.AdminProfile;
import com.chainsys.grocery.service.AdminService;
import com.chainsys.grocery.util.ServiceException;

@WebServlet("/adminproviewservlet")
public class adminproviewservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminService obj = new AdminService();
		ArrayList<AdminProfile> view = new ArrayList<AdminProfile>();
		try {
			view = obj.viewProducts();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("res", view);
		RequestDispatcher rd = request.getRequestDispatcher("viewproductsadmin.jsp");
		rd.forward(request, response);
	}

}
