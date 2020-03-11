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

import com.chainsys.grocery.model.OrderSummary;
import com.chainsys.grocery.service.UserService;
import com.chainsys.grocery.util.DBException;


@WebServlet("/viewmyorders")

public class viewmyorders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOG IN USER");
		UserService obj = new UserService();
		int userid = 0;
		try {
			userid = obj.checkuserid(username);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ArrayList<OrderSummary> orders = null;
		try {
			orders = obj.ViewOrder(userid);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("list", orders);

		RequestDispatcher d = request.getRequestDispatcher("myorders.jsp");
		d.forward(request, response);
	}

}
