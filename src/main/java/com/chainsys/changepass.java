package com.chainsys;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocerymaven.UserProfileDaoImpl;
@WebServlet("/changepass")

public class changepass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOG IN USER");
		String a = request.getParameter("mail");
		String b = request.getParameter("npassword");
		String c = request.getParameter("cpassword");
		UserProfileDaoImpl obj = new UserProfileDaoImpl();
		if (obj.checkmailuser(a, username)) {
			if (b.equals(c)) {
				boolean res1 = obj.checkmailpass(a, username, c);
				try {
					if (res1) {
						String st="true";
						RequestDispatcher d = request.getRequestDispatcher("index.jsp?stat="+st);
						d.forward(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				String res1 = "Password Mismatch";
				RequestDispatcher d = request.getRequestDispatcher("changepass.jsp?res=" + res1);
				d.forward(request, response);
			}
		} else {
			String res = "Invalid MailId ";
			RequestDispatcher d = request.getRequestDispatcher("home.jsp?stat=" + res);
			d.forward(request, response);
		}
	}
}