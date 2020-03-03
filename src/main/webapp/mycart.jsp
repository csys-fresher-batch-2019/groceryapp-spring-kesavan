<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.chainsys.grocery.model.UserProfile"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<center>
	<h1>E-GROCERY CART</h1>


	<body style="background-color: powderblue;">

		<div align="right">
			<button>
				<a href="Logoutservlet">Log out</a>
		</div>
		</button>

		<%
			String username = (String) session.getAttribute("LOG IN USER");
		%>
		<br> LOGGED IN AS
		<%=username.toUpperCase()%>

		<br>
		<br>
		<br>
		<%
			Map<Integer, Integer> items = (Map<Integer, Integer>) session.getAttribute("CARTS");
			if (items == null || items.isEmpty()) {
				out.println("<h3>Your Cart is Empty</h3>");

			} else {
		%>
		<form action=updatecart>
			<table border="1">
				<thead>
					<tr>
						<th>S.no</th>
						<th>ProductId</th>
						<th>Quantity</th>
					</tr>
				</thead>
				<tbody>
					<%
						int i = 1;
							for (Integer productId : items.keySet()) {
								Integer qty = items.get(productId);
					%>
					<tr>

						<td><%=i++%></td>
						<td><input type="number" name="pid" id="pid_<%=productId%>"
							value="<%=productId%>" readonly></td>
						<td><input type="number" name="qty_<%=productId%>"
							value="<%=qty%>" min="1" and max="6"></td>
						<td><button>
								<a href="removefromcart?id=<%=productId%>">Delete</a>
							</button></td>
					</tr>
					<%
						}
					%>


				</tbody>
			</table>
			<br> <br>
			<h2></h2>

			<button type="submit">Change Cart</button>
			<button>
				&nbsp; <a href="paymenttype.jsp">Buy now</a>
			</button>
		</form>

		<%
			}
		%>
		<br>
		<button>
			&nbsp; <a href="productlist">Back</a>
		</button>
		<button>
			<a href="home.jsp">Home</a>
		</button>

	</body>
</html>