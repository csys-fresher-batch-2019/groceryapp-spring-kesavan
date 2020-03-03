package com.chainsys.grocery.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocery.dao.impl.UserProfileDaoImpl;
import com.chainsys.grocery.model.UserDisplay;
@WebServlet("/search")

public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a = request.getParameter("field");
		if (a.isEmpty() || a.equals(null)) {
			RequestDispatcher d = request.getRequestDispatcher("productlist");
			d.forward(request, response);
		} else {
			UserProfileDaoImpl obj = new UserProfileDaoImpl();
			String b = "and p.product_name like '%"
					+ (a.substring(0, 1).toString().toUpperCase() + a.substring(1, a.length())) + "%'";
			ArrayList<UserDisplay> list = obj.ViewProducts(b);

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
