<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<br> LOGIN AS
		<%=username.toUpperCase()%>
		<br>
		<br>
		<form action="Changeaddress">
			Enter Your Address : <input type="text" name="address"
				pattern="[1-9]{2,},[a-z]{5,},[a-z]{3,}"
				title="Enter Door No,Streetname,city"
				placeholder="Enter new address " required autofocus /> <br> <br>
			Enter ZipCode : <input type="text" name="pincode" pattern="[0-9]{6}"
				placeholder="6 - digit pincode " required /> <br>
			<br>
			<button type="submit">Change</button>
		</form>
	</body>
</html>