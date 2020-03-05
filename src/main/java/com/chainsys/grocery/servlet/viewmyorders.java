package com.chainsys.grocery.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocery.dao.UserProfileDao;
import com.chainsys.grocery.dao.impl.UserProfileDaoImpl;
import com.chainsys.grocery.model.Ordersummary;


@WebServlet("/viewmyorders")

public class viewmyorders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOG IN USER");
		UserProfileDao obj = new UserProfileDaoImpl();
		int user = obj.checkuserid(username);
		ArrayList<Ordersummary> orders = obj.ViewOrder(user);
		request.setAttribute("list", orders);

		RequestDispatcher d = request.getRequestDispatcher("myorders.jsp");
		d.forward(request, response);
	}

}
