<%@page import="com.chainsys.grocery.dao.impl.UserProfileDaoImpl"%>
<%@page import="com.chainsys.grocery.dao.impl.AdminProfileDaoImpl"%>
<%@page import="com.chainsys.grocery.model.UserProfile"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment page</title>
</head>
<center>
	<h1>E-GROCERY PAYMENT</h1>

<body style="background-color: powderblue;">

	<div align="right">
		<button>
			<a href="Logoutservlet">Log out</a>
	</div>
	</button>
	<form action="placeorder">



		<%
			String username = (String) session.getAttribute("LOG IN USER");
		%>
		<br>
			LOGGED IN AS
			<%=username.toUpperCase()%>

			<br> <br>
			<%
				ArrayList<UserProfile> items = (ArrayList) session.getAttribute("FINALCART");
				AdminProfileDaoImpl obj1 = new AdminProfileDaoImpl();
				int totalbill = obj1.bill(items);
				session.setAttribute("bill", (totalbill+50)); 
			%>
			<h3>
				Your Bill Amount :
				<%=totalbill%></br> Delivery Charge :50<br> <br> TotalBill :
				<%=totalbill + 50%></h3>
		<form>
			
				Select Payment Type :
				<input type="radio" name="Paytype" value="COD" checked required>Cash on Delivery 
		<input type="radio" name="Paytype" value="CitiWallet" >Wallet
				
				<input type="radio" name="Paytype" value="Card" disabled>Card
				
				 <br> <br>
				<button type="submit">Proceed</button>
			</form><br>
				<a href="home.jsp">Home</a>
		
</body>
</html>