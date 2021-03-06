package com.chainsys.grocery.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocery.service.UserService;
import com.chainsys.grocery.util.ServiceException;

@WebServlet("/changepass")

public class changepass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("LOG IN USER");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("npassword");
		String pass1 = request.getParameter("cpassword");
		UserService obj = new UserService();
		try {
			if (pass.equals(pass1)) {
				if (obj.userValidation(mail, user, pass)) {
					String st = "true";
					RequestDispatcher d = request.getRequestDispatcher("index.jsp?stat=" + st);
					d.forward(request, response);

				} else {
					String res = "Invalid MailId ";
					RequestDispatcher d = request.getRequestDispatcher("home.jsp?stat=" + res);
					d.forward(request, response);
				}
			} else {
				String res1 = "Password Mismatch";
				RequestDispatcher d = request.getRequestDispatcher("changepass.jsp?res=" + res1);
				d.forward(request, response);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}