<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<center>
	<h1>E-GROCERY</h1>
</center>
<body style="background-color: powderblue;">
	<br>

	<br />
	<div align="right">
		<button>
			<a href="Logoutservlet">Log out</a>
	</div>
	</button>
	
		<%
			String username = (String) session.getAttribute("LOG IN USER");
		%>
		</br> <font color=maroon> WELCOME
		<%=username.toUpperCase()%></font>
	
	<center>
		<h3><a href="productlist">ProductsList</a></h3>
	</center>
	<br />
	<center>
		<h3><a href="viewmyorders">MyOrders</a></h3>
	</center>
	<br />
	<center>
		<h3><a href="changepass.jsp">Changepassword</a></h3>
	</center>
	<br />
	<center>
		<h3><a href="changeaddress.jsp">Changeaddress</a></h3>
	</center>
	<br />

	<%
		String res = (String) request.getParameter("result");
	%>
	<%
		if (res != null) {
	%>
	<script>
		alert("Address Updated Succesfully");
	</script>
	<%
		}
	%>
	<%
		String res1 = (String) request.getParameter("res");
		if (res1 != null) {
	%>
	<script>
		alert("Invalid MailId Retry Again ");
	</script>
	<%
		}
	%>
	<%
		String a = (String) request.getParameter("stat");
		if (a != null) {
	%>
	<script>
		alert("Invalid MailId Retry Again");
	</script>
	<%
		}
	%>
</body>
</html>