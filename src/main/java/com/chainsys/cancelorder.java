package com.chainsys;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocerymaven.UserProfileDaoImpl;

public class cancelorder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int orderid = Integer.parseInt(request.getParameter("order_id"));
		int transid = Integer.parseInt(request.getParameter("trans_id"));

		String a = request.getParameter("confirm");
		if (a.equals("yes")) {
			UserProfileDaoImpl obj = new UserProfileDaoImpl();

			try {
				String s = obj.Cancelorder(orderid);
				request.setAttribute("status", s);
				RequestDispatcher d = request.getRequestDispatcher("cancelfail.jsp");
				d.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher d = request.getRequestDispatcher("myorders.jsp");
			d.forward(request, response);
		}

	}

}
