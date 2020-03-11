package com.chainsys.grocery.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocery.service.UserService;

@WebServlet("/cancelorder")

public class cancelorder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int orderid = Integer.parseInt(request.getParameter("order_id"));
		// int transid = Integer.parseInt(request.getParameter("trans_id"));

		String a = request.getParameter("confirm");
		if (a.equals("yes")) {
			UserService obj = new UserService();

			try {
				String s = obj.cancelOrder(orderid);
				request.setAttribute("status", s);
				RequestDispatcher d = request.getRequestDispatcher("cancelfail.jsp");
				d.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher d = request.getRequestDispatcher("viewmyorders");
			d.forward(request, response);
		}

	}

}
