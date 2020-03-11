package com.chainsys.grocery.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocery.service.AdminService;
import com.chainsys.grocery.util.DBException;

@WebServlet("/revenueservlet")
public class revenueservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminService obj = new AdminService();
		String a = request.getParameter("val");
			int total=0;
			try {
				total = obj.revenue(a);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String b = Integer.toString(total);
			request.setAttribute("res", b);
			request.setAttribute("type", a);
		
		RequestDispatcher d = request.getRequestDispatcher("totalrevenue.jsp");
		d.forward(request, response);
	}

}
