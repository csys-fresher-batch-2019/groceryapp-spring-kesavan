package com.chainsys.grocery.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocery.service.UserService;
import com.chainsys.grocery.util.DBException;
@WebServlet("/Changeaddress")

public class Changeaddress extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("LOG IN USER");
		String address=request.getParameter("address");
		String pin=request.getParameter("pincode");
		String address1=address+"-"+pin;
		UserService obj=new UserService();
		try {
			obj.changeAddress(username, address1);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String status="Address Updated Successfully";
		response.sendRedirect("home.jsp?result="+status);
	}
}
