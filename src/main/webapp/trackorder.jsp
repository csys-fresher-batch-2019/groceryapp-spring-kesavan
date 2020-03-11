<%@page import="com.chainsys.grocery.dao.impl.UserProfileDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Track order</title>
</head>
<center>
	<h1>TRACK ORDERS HERE</h1>
<center>
<body style="background-color: powderblue;">

	<div align="right">
		<button>
			<a href="Logoutservlet">Log out</a>
	</div>
	</button>

	<%
	   int orderId = Integer.parseInt(request.getParameter("order_id"));
		String username = (String) session.getAttribute("LOG IN USER");
	%>
	<br>
	
		LOGGED IN AS
		<%=username.toUpperCase()%></center>
		<br>
		
		<%
					UserProfileDaoImpl obj=new UserProfileDaoImpl();
						String a=obj.trackOrder(orderId);
				%>
		
		<br><br>
		
		YOUR ORDER STATUS </br></br></br> <%=a.toUpperCase() %>
		
				<br><br><br>	<button ><a href="home.jsp">Home</a></button>
		

</body>
</html>