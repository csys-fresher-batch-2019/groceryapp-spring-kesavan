package com.chainsys;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.dto.CreditCardDTO;
import com.chainsys.grocerymaven.AdminProfileDaoImpl;
import com.chainsys.grocerymaven.UserProfile;
import com.chainsys.service.OrderService;
@WebServlet("/cardpay")

public class cardpay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long cardnum = Long.parseLong(request.getParameter("cardnumber"));
		int cvv = Integer.parseInt(request.getParameter("cvv"));
		String expiry = request.getParameter("expiry");
		LocalDate exp = LocalDate.parse(expiry);
		String comments = request.getParameter("purpose");


		HttpSession session = request.getSession();
		float amount = Float.parseFloat(session.getAttribute("bill").toString());
		System.out.println(amount);
		ArrayList<UserProfile> items = (ArrayList<UserProfile>) session.getAttribute("CARTS");
		AdminProfileDaoImpl obj1 = new AdminProfileDaoImpl();
		String username = (String) session.getAttribute("LOG IN USER");
		try {
			
			CreditCardDTO cd = new CreditCardDTO(cardnum, exp, cvv, amount, comments);
			
			if (true) {//obj.isStatus()) {
				int id=0;
				OrderService.createOrder(items, username, cd);
				RequestDispatcher d = request.getRequestDispatcher("home.jsp");
				d.forward(request, response);
			}else {
				System.out.println("Payment Failed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
