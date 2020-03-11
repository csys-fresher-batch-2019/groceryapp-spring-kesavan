package com.chainsys.grocery.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocery.model.UserDisplay;
import com.chainsys.grocery.service.UserService;
import com.chainsys.grocery.util.DBException;

@WebServlet("/sort")

public class sort extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String a = request.getParameter("type");
		if (a.isEmpty() || a.equals(null)) {
			a = " ";
			UserService obj = new UserService();
			ArrayList<UserDisplay> productlist;
			try {
				productlist = obj.viewProducts(a);
				request.setAttribute("listpro", productlist);
				RequestDispatcher d = request.getRequestDispatcher("products.jsp");
				d.forward(request, response);
			} catch (DBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			UserService obj = new UserService();
			ArrayList<UserDisplay> productlist;
			try {
				productlist = obj.viewProducts(a);
				request.setAttribute("listpro", productlist);
				RequestDispatcher d = request.getRequestDispatcher("products.jsp");
				d.forward(request, response);
			} catch (DBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
