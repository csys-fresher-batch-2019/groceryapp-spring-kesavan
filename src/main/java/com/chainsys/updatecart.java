package com.chainsys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocerymaven.UserProfile;
@WebServlet("/updatecart")

public class updatecart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get selected items and store in items variable
		ArrayList<UserProfile> Items = new ArrayList<UserProfile>();
		Map<Integer, Integer> items = getSelectedItems(request);
		HttpSession session = request.getSession();
		try {
			for (Integer productId : items.keySet()) {
				Integer qty = items.get(productId);
				UserProfile obj = new UserProfile();
				obj.setProductid(productId);
				obj.setNoOfItems(qty);
				Items.add(obj);
			}
			session.setAttribute("CARTS", items);
			session.setAttribute("FINALCART", Items);
			RequestDispatcher d = request.getRequestDispatcher("mycart.jsp");
			d.forward(request, response);
		}

		// System.out.println("No of items in existing cart:" + cartItems.size());

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * retrive form values from page get only selected items
	 * 
	 * @param request
	 * @return
	 */
	private Map<Integer, Integer> getSelectedItems(HttpServletRequest request) {
		String[] productIds = request.getParameterValues("pid");
		Map<Integer, Integer> items = new HashMap<Integer, Integer>();
		for (String pId : productIds) {
			int b = Integer.parseInt(pId);
			int c = Integer.parseInt(request.getParameter("qty_" + pId));
			items.put(b, c);

		}
		return items;
	}

}
