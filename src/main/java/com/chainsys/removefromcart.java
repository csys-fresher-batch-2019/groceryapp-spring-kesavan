package com.chainsys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.grocerymaven.UserProfile;

public class removefromcart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<UserProfile> Items = new ArrayList<UserProfile>();
		int id = Integer.parseInt(request.getParameter("id").toString());
		Map<Integer, Integer> items = (Map<Integer, Integer>) session.getAttribute("CARTS");
		Map<Integer, Integer> items2 = new HashMap<Integer, Integer>();

		try {
			for (Integer productId : items.keySet()) {
				Integer qty = items.get(productId);
				if (id == productId) {
					items.remove(productId);
				} else {
					UserProfile obj = new UserProfile();
					obj.setProductid(productId);
					obj.setNoOfItems(qty);
					Items.add(obj);
					items2.put(productId, qty);
				}
			}
			session.setAttribute("CARTS", items2);
			session.setAttribute("FINALCART", Items);
			response.sendRedirect("mycart.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
