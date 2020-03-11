<%@page import="com.chainsys.grocery.model.OrderSummary"%>
<%@page import="com.chainsys.grocery.dao.impl.UserProfileDaoImpl"%>
<%@page import="com.chainsys.grocery.dao.UserProfileDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders Page</title>
</head>
<center>
	<h1>YOUR ORDERS</h1>
</center>
<body style="background-color: powderblue;">

	<div align="right">
		<button>
			<a href="Logoutservlet">Log out</a>
	</div>
	</button>

	<%
		String username = (String) session.getAttribute("LOG IN USER");
	%>
	</br>
	<center>
		LOGGED IN AS
		<%=username.toUpperCase()%></center>
	<br />
	<br />

	<%
		ArrayList<OrderSummary> orders = (ArrayList) request.getAttribute("list");
			if(orders.size()>0){
	%>
	<table border="1">
		<thead>
			<tr>
				<th>S.no</th>
				<th>OrderId</th>
				<th>ProductName</th>
				<th>Manufacturer</th>
				<th>Quantity</th>
				<th>Totalamount</th>
				<th>Orderdate</th>
				<th>Deliverydate</th>
				<th>Deliveryaddress</th>
				<th>Orderstatus</th>
				<th>Payment</th>
				<th>Transaction Id</th>
				<th>Click to Cancel</th>
				<th>Click to track</th>
			</tr>
		</thead>
		<tbody>
			<%
				int i = 1;
					for (OrderSummary ud : orders) {
			%>
			<tr>

				<td><%=i++%></td>
				<td><%=ud.getOrderid()%></td>
				<td><%=ud.getProductname()%></td>
				<td><%=ud.getManufacturer()%></td>
				<td><%=ud.getNoofitems()%></td>
				<td><%=ud.getTotalamount()%></td>
				<td><%=ud.getOrderdate()%></td>
				<td><%=ud.getDeliverydate()%></td>
				
				<td><%=ud.getDeliveryaddress()%></td>
				<td><%=ud.getOrderstatus()%></td>
				<td><%=ud.getPayment()%></td>
				<td><%=ud.getTransId()%></td>

				<%
					if (ud.getOrderstatus().equals("ORDERED")) {
				%>
				<td><a
					href="cancelconfirm.jsp?order_id=<%=ud.getOrderid()%>&trans_id=<%=ud.getTransId()%>">Cancel</a></td>
				<td><a href="trackorder.jsp?order_id=<%=ud.getOrderid()%>">Track</a></td>
				<%
					}else{%>
						<td>------------------</td><td>----------------</td>
				<% }%>	
				
			</tr>

			<%
				}}else {
			%>
				<center><h3>Not Yet any Order any Placed.</h3>
				<%} %>
		</tbody>
	</table>
	<br>
	<button>
		<a href="home.jsp">Home</a>
	</button>
</center>
</body>
</html>