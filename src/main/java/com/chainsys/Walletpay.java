package com.chainsys;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.payment.WalletAPI;

import citiipay.messages.DBException;
import citiipay.models.Merchant;

public class Walletpay extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	}

}
