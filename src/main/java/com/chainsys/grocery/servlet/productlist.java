package com.chainsys.grocery.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocery.dao.UserProfileDao;
import com.chainsys.grocery.dao.impl.UserProfileDaoImpl;
import com.chainsys.grocery.model.UserDisplay;
@WebServlet("/productlist")

public class productlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a = "";
		UserProfileDao obj = new UserProfileDaoImpl();
		ArrayList<UserDisplay> list;
		try {
			list = obj.ViewProducts(a);
			request.setAttribute("listpro", list);
			RequestDispatcher d = request.getRequestDispatcher("products.jsp");
			d.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
