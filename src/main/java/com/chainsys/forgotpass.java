package com.chainsys;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocerymaven.UserProfileDaoImpl;
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
			UserProfileDaoImpl obj = new UserProfileDaoImpl();
			boolean res1 = obj.checkmailpass(a, username, c);
			if (res1) {
				try {
					RequestDispatcher d = request.getRequestDispatcher("home.jsp");
					d.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

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
