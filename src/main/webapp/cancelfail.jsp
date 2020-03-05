<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.chainsys.grocery.dao.impl.UserProfileDaoImpl"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel fail</title>
</head>
<center>
	<h1>CANCEL HERE</h1>
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
	<%
		String s = (String) (request.getAttribute("status"));
	%>
	</br>
	<center>
		LOGGED IN AS
		<%=username.toUpperCase()%></center>
	</br>

	<center>
		CANCELLATION STATUS </br>
		</br>
		<%=s%></br>
		</br>

		<button>
			<a href="viewmyorders">MyOrders</a>
		</button>
	</center>



</body>
</html>