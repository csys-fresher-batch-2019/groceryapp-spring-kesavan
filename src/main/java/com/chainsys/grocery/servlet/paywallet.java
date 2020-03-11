package com.chainsys.grocery.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocery.model.UserProfile;
import com.chainsys.grocery.payment.WalletAPI;
import com.chainsys.grocery.service.AdminService;
import com.chainsys.grocery.util.DBException;

@WebServlet("/paywallet")

public class paywallet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOG IN USER");
		ArrayList<UserProfile> items = (ArrayList<UserProfile>) session.getAttribute("FINALCART");
		Map<Integer, Integer> items1 = (Map<Integer, Integer>) session.getAttribute("CARTS");
		AdminService obj1 = new AdminService();
		long mobile = Long.parseLong(request.getParameter("mobileno"));
		int pin=Integer.parseInt(request.getParameter("pin"));
		int amount = Integer.parseInt(session.getAttribute("bill").toString());
		WalletAPI obj = new WalletAPI();
		Map result = obj.paywallet(mobile, "GROCERY", amount);
		String status=(String) result.get("status");
		String msg=(String) result.get("errorMessage");
		if(status.equals("SUCCESS")) {
			int id=(int) result.get("transactionId");
			try {
				obj1.createOrder(items, username, "CITIWALLET", id);
			} catch (DBException | SQLException e) {
				e.printStackTrace();
			}
			items.clear();
			items1.clear();
			session.setAttribute("FINALCART",items);
			session.setAttribute("CARTS",items1);
			response.sendRedirect("paymentresult.jsp?res="+status);
		}else {
			response.sendRedirect("paymentresult.jsp?res="+msg);
		}

	}
}
