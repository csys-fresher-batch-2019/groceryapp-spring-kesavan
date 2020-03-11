package com.chainsys.grocery.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.service.AdminService;

@WebServlet("/placeorder")

public class placeorder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOG IN USER");
		ArrayList<UserProfile> items = (ArrayList<UserProfile>) session.getAttribute("FINALCART");
		Map<Integer, Integer> items1 = (Map<Integer, Integer>) session.getAttribute("CARTS");

		AdminService obj1 = new AdminService();

		String type = request.getParameter("Paytype");
		if (type.equals("COD")) {
			try {
				obj1.createOrder(items, username, "COD", 0);
				items.clear();
				items1.clear();
				session.setAttribute("CARTS",items1);
				session.setAttribute("FINALCART", items);
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
