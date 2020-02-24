package com.chainsys;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/paywallet")

public class paywallet extends HttpServlet {
/*	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	long mobile = Long.parseLong(request.getAttribute("mobileno").toString());
		HttpSession session = request.getSession();
		int amount = Integer.parseInt(session.getAttribute("bill").toString());
		WalletAPI obj = new WalletAPI();
		Merchant obj1 = new Merchant();
		try {
			obj1 = obj.cardpayment(mobile, amount);

		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}

