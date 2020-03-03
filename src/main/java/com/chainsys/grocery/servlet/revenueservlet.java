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

@WebServlet("/revenueservlet")
public class revenueservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminProfileDao obj = new AdminProfileDaoImpl();
		String a = request.getParameter("val");
		if (a.equals("All mode of Payments")) {
			String c="";
			int total = obj.revenue(c);
			String b = Integer.toString(total);
			request.setAttribute("res", b);
			request.setAttribute("type", a);
		} else {
			int total = obj.revenue(a);
			String b = Integer.toString(total);
			request.setAttribute("res", b);
			request.setAttribute("type", a);
		}
		RequestDispatcher d = request.getRequestDispatcher("totalrevenue.jsp");
		d.forward(request, response);
	}

}
