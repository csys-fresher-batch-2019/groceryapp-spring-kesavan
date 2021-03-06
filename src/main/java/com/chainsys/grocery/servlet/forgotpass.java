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

@WebServlet("/forgotpass")

public class forgotpass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("user");
		String a = request.getParameter("mail");
		String b = request.getParameter("npassword");
		String c = request.getParameter("cpassword");
		if (b.equals(c)) {
			UserService obj = new UserService();
			boolean res1 = false;
			try {
				res1 = obj.userValidation(a, username, c);
				System.out.println(res1);
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (res1) {
				String res = "Password retrived successfully Please Login Again !!!";
				RequestDispatcher d = request.getRequestDispatcher("index.jsp?stat=" + res);
				d.forward(request, response);

			} else {
				String res = "Invalid MailId";
				RequestDispatcher d = request.getRequestDispatcher("index.jsp?result=" + res);
				d.forward(request, response);
			}
		} else {
			String res = "Password Mismatch Retype again";
			RequestDispatcher d = request.getRequestDispatcher("forgot.jsp?result=" + res);
			d.forward(request, response);
		}
	}

}
