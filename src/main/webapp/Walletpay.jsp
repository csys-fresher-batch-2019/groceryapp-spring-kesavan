<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wallet Pay</title>
</head>
<body style="background-color: powderblue;">

<div align="right">
	<button>
		<a href="Logoutservlet">Log out</a>
</div>
</button>

<%
	String username = (String) session.getAttribute("LOG IN USER");
%>
</br> LOGIN AS
<%=username.toUpperCase()%>
<center>
<form action="paywallet" >
		<br /> Citipay Wallet MobileNumber : <input type="number"
		name="mobileno" required /> <br /> 
		Enter your Pin :<input type="number"
		name="pin" required /> <br /> 
		
	<button type="submit">Submit</button>
</form>
</center>
</body>
</html>