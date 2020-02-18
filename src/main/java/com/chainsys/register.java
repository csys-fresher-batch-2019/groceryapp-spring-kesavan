package com.chainsys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.grocerymaven.UserProfileDao;
import com.chainsys.grocerymaven.UserProfileDaoImpl;

public class register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a = request.getParameter("Username");
		String b = request.getParameter("Email");
		long c = Long.parseLong(request.getParameter("Mobileno"));
		String d = request.getParameter("pw");
		String e = request.getParameter("address");
		String f=request.getParameter("pincode");
		String address=e+"-"+f;
		UserProfileDao obj = new UserProfileDaoImpl();
		int id = 0;
		boolean mobile=obj.checkmobilenocreate(c);
		boolean mail=obj.checkmailcreate(b);
		boolean user=obj.checkusernamecreate(a);
		if(user && mail && user) {
		try {

			id = obj.CreateAccount(a, d, address, c, b);
			RequestDispatcher h = request.getRequestDispatcher("index.jsp");
			h.forward(request, response);

		} catch (Exception e1) {
			e1.printStackTrace();
		}}else {
			String r="fail";
			RequestDispatcher h = request.getRequestDispatcher("Register.jsp?status="+r);
			h.forward(request, response);

		}

	}

}
