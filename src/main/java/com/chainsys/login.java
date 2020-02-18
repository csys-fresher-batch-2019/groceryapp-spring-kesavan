package com.chainsys;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocerymaven.UserProfileDao;
import com.chainsys.grocerymaven.UserProfileDaoImpl;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a = request.getParameter("Username");
		String b = request.getParameter("password");
		UserProfileDao obj = new UserProfileDaoImpl();
		try {
			boolean res = obj.Login(a, b);
			if (res) {
				HttpSession session = request.getSession();
				session.setAttribute("LOG IN USER", a);
				RequestDispatcher d = request.getRequestDispatcher("home.jsp");
				d.forward(request, response);
			} else {
				String result = "Invalid Username / Password";
				response.sendRedirect("index.jsp?res=" + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
