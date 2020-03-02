<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
</head>
<center>
	<h1>E-GROCERY</h1>
	<body style="background-color: powderblue;">

		<div align="right">
			<button>
				<a href="Logoutservlet">Log out</a>
		</div>
		</button>

		<%
			String username = (String) session.getAttribute("LOG IN USER");
		%>
		</br> LOGGED IN AS
		<%=username.toUpperCase()%>
		<br>
		<br>
		<%
			String result = request.getParameter("res");
			if (result.equals("SUCCESS")) {
		%>
	
		<h2>Order Placed Succesfully !!! </h2><br><br>
		<h2>Check Myorders for details !!! </h2>

		<%
			}else {
		%>
		<h2>Payment Failed</h2>
		<%} %>
		<button>
			<a href="home.jsp">Home</a>
		</button>
	</body>
</html>
