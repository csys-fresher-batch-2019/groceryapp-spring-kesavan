package com.chainsys;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chainsys.grocerymaven.UserDisplay;
import com.chainsys.grocerymaven.UserProfileDaoImpl;

public class sort extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String a = request.getParameter("type");
		if (a.isEmpty()||a.equals(null)) {
			a = " ";
			UserProfileDaoImpl obj = new UserProfileDaoImpl();
			ArrayList<UserDisplay> list = obj.ViewProducts(a);
			try {

				request.setAttribute("listpro", list);
				RequestDispatcher d = request.getRequestDispatcher("products.jsp");
				d.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			UserProfileDaoImpl obj = new UserProfileDaoImpl();
			ArrayList<UserDisplay> list = obj.ViewProducts(a);
			try {

				request.setAttribute("listpro", list);
				RequestDispatcher d = request.getRequestDispatcher("products.jsp");
				d.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
