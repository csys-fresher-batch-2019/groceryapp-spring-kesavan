package com.chainsys.grocery.servlet;

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

import com.chainsys.grocery.model.UserProfile;

@WebServlet("/cart")

public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get selected items and store in items variable
		Map<Integer, Integer> items = getSelectedItems(request);

		HttpSession session = request.getSession();

		try {
			// Get existing items from the card
			@SuppressWarnings("unchecked")
			Map<Integer, Integer> cartItemsInSession = (Map<Integer, Integer>) session.getAttribute("CARTS");
			if (cartItemsInSession == null) {
				cartItemsInSession = new HashMap<Integer, Integer>();
			}
			for (Integer productId : items.keySet()) {
				Integer qty = items.get(productId);

				Integer sessionQty = cartItemsInSession.containsKey(productId) ? cartItemsInSession.get(productId) : 0;

				Integer total = sessionQty + qty;
				cartItemsInSession.put(productId, total);

			}
			// System.out.println("No of items in existing cart:" + cartItems.size());
			session.setAttribute("CARTS", cartItemsInSession);
			String res = "Items added to cart Please check the cart for checkout !!! ";
			RequestDispatcher d = request.getRequestDispatcher("productlist?res=" + res);
			d.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * retrive form values from product page get only selected items
	 * 
	 * @param request
	 * @return
	 */
	private Map<Integer, Integer> getSelectedItems(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String[] productIds = request.getParameterValues("pid");
		ArrayList<UserProfile> itemscart = new ArrayList<UserProfile>();
		Map<Integer, Integer> items = new HashMap<Integer, Integer>();
		for (String pId : productIds) {
			int b = Integer.parseInt(pId);
			int c = Integer.parseInt(request.getParameter("qty_" + pId));
			items.put(b, c);
			UserProfile obj = new UserProfile();
			obj.setProductid(b);
			obj.setNoOfItems(c);
			itemscart.add(obj);
			session.setAttribute("FINALCART", itemscart);

		}
		return items;
	}
}