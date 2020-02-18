package com.chainsys;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocerymaven.AdminProfileDaoImpl;
import com.chainsys.grocerymaven.UserProfile;

public class placeorder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOG IN USER");
		ArrayList<UserProfile> items = (ArrayList<UserProfile>) session.getAttribute("FINALCART");
		AdminProfileDaoImpl obj1 = new AdminProfileDaoImpl();
		
		
		String type = request.getParameter("Paytype");
		if (type.equals("COD")) {
			try {
				obj1.createOrder(items, username, "COD", 0);
				RequestDispatcher d = request.getRequestDispatcher("home.jsp");
				d.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (type.equals("CitiWallet")) {
			RequestDispatcher d = request.getRequestDispatcher("Walletpay.jsp");
			d.forward(request, response);

		} else {

		}
	}

}
