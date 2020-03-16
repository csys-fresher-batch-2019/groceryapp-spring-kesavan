package com.chainsys.grocery.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocery.service.UserService;
import com.chainsys.grocery.util.ServiceException;

@WebServlet("/register")

public class register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a = request.getParameter("Username");
		String b = request.getParameter("Email");
		long c = Long.parseLong(request.getParameter("Mobileno"));
		String d = request.getParameter("pw");
		String e = request.getParameter("address");
		String f = request.getParameter("pincode");
		String address = e + "-" + f;
		UserService userService = new UserService();
		String r = "";
		boolean mobile = false;
		boolean mail = false;
		boolean user = false;
		try {
			mobile = userService.checkMobileNoCreate(c);
			mail = userService.checkMailCreate(b);
			user = userService.checkUserNameCreate(a);
		} catch (ServiceException e2) {
			e2.printStackTrace();
		}
		System.out.println("mobile" + mobile);
		System.out.println("mail" + mail);
		System.out.println("user" + user);
		if (user || mail || mobile) {
			r = "fail";
			RequestDispatcher h = request.getRequestDispatcher("Register.jsp?status=" + r);
			h.forward(request, response);

		} else {
			try {
				int id = userService.createAccount(a, d, address, c, b);
				RequestDispatcher h = request.getRequestDispatcher("index.jsp");
				h.forward(request, response);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
