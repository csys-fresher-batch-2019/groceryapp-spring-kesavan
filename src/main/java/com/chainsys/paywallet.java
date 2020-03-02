package com.chainsys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocerymaven.AdminProfileDaoImpl;
import com.chainsys.grocerymaven.UserProfile;
import com.chainsys.payment.WalletAPI;

@WebServlet("/paywallet")

public class paywallet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOG IN USER");
		ArrayList<UserProfile> items = (ArrayList<UserProfile>) session.getAttribute("FINALCART");
		AdminProfileDaoImpl obj1 = new AdminProfileDaoImpl();
		long mobile = Long.parseLong(request.getParameter("mobileno"));
		int pin=Integer.parseInt(request.getParameter("pin"));
		int amount = Integer.parseInt(session.getAttribute("bill").toString());
		WalletAPI obj = new WalletAPI();
		Map result = obj.paywallet(mobile, "GROCERY", amount);
		String status=(String) result.get("status");
		if(status.equals("SUCCESS")) {
			int id=(int) result.get("transactionId");
			obj1.createOrder(items, username, "CITIWALLET", id);
			System.out.println("Order Placed");
			response.sendRedirect("paymentresult.jsp?res="+status);
		}else {
			response.sendRedirect("paymentresult.jsp?res="+status);
		}

	}
}
