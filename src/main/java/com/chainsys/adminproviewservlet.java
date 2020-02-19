package com.chainsys;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocerymaven.AdminProfile;
import com.chainsys.grocerymaven.AdminProfileDaoImpl;

@WebServlet("/adminproviewservlet")
public class adminproviewservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminProfileDaoImpl obj = new AdminProfileDaoImpl();
		ArrayList<AdminProfile> view = new ArrayList<AdminProfile>();
		view = obj.viewProducts();
		request.setAttribute("res", view);
		RequestDispatcher rd=request.getRequestDispatcher("viewproductsadmin.jsp");
		rd.forward(request, response);
	}

}
