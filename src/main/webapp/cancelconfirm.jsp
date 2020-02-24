<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel Page</title>
</head>
<center>
	<h1>CANCEL CONFIRMATION</h1>
</center>
<body style="background-color: powderblue;">

	<div align="right">
		<button>
			<a href="Logoutservlet">Log out</a>
	</div>
	</button>

	<%
	   String selectedOrderId = request.getParameter("order_id");
	   String selectedTransId = request.getParameter("trans_id");

		String username = (String) session.getAttribute("LOG IN USER");
	%>
	</br>
	<center>
		LOGIN AS
		<%=username.toUpperCase()%></center>
		</br>
		<form action="cancelorder">		
		<input type="hidden" name="order_id" value="<%=selectedOrderId %>" readonly/>
		<input type="hidden" name="trans_id" value="<%=selectedTransId %>" readonly/>
		
		<center> Click Confirm to Cancel Order</center></br>
		<center><input type="radio" name ="confirm" value="yes" required>Yes</center>
				<center><input type="radio" name ="confirm" value="no" required>No</center>
				</br>
				<center><button type="SUBMIT">Confirm</button></center>
</form>		
</body>
</html>