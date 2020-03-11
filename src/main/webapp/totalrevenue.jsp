<%@page import="com.chainsys.grocery.dao.impl.AdminProfileDaoImpl"%>
<%@page import="com.chainsys.grocery.dao.AdminProfileDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Total Income</title>
</head>
<center>
	<body style="background-color: powderblue;">
		<h2>Total Revenue</h2>
		<br><br><div align="right">
			<button>
				<a href="index.jsp">Logout</a>
		</div>
		</button>
		<form action="revenueservlet">
			Choose an Option:
			<select name="val">
			<option value="COD">Cash on Delivery</option>
							<option value="CARD">Card Payment</option>
							<option value="CITIWALLET">Wallet Payment</option>
							<option value="">All Modes of Pay</option>
			</select><br><br>
				<br>
				<button type="SUBMIT">Submit</button>

		</form>

		<%	
			String type=(String)(request.getAttribute("type"));
			String revenue = (String) (request.getAttribute("res"));
			if (revenue == null) {
			} else {
		%>
	<br>	<h2>Total Revenue till Date by <%=type %></h2><br>
	
		<h2>Rs.<%=revenue%></h2>
		<%
			}
		%><br><br>
		<h3><a href="adminhome.jsp">Home</a></h3>
	</body>
</html>