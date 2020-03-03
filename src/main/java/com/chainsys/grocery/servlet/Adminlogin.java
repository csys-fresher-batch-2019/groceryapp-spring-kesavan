package com.chainsys.grocery.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Adminlogin")

public class Adminlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a = request.getParameter("Username");
		String b = request.getParameter("password");
		if(a.equals("Admin") && b.equals("grocery")) {
			RequestDispatcher d = request.getRequestDispatcher("adminhome.jsp");
			d.forward(request, response);
		}else {
			String result = "Invalid Username / Password";
			response.sendRedirect("adminindex.jsp?res=" + result);

		}
	}

	}
