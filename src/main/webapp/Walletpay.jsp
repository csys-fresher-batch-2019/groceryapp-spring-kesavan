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
	<center>
		<h1>E-GROCERY WALLLET PAYMENT</h1>

		<%
			String username = (String) session.getAttribute("LOG IN USER");
		%>
		<br> LOGGED IN AS
		<%=username.toUpperCase()%>
		<form action="paywallet">
			<table>
				<tr>
					<td>Citipay Wallet MobileNumber :</td>
					<td><input type="tel" name="mobileno"
						title="Please enter 10 digit mobile number"
						pattern="[6-9]{1}[0-9]{9}" placeholder=" Enter Mobile_num"
						required /></td></tr><br>
					<tr><td> Enter your Pin :</td>
					<td><input type="text" name="pin" 
					title="Please enter 4 digit Pin number"
					pattern="[0-9]{4}" placeholder=" Enter Pin" required /></td>
				</tr>
				<br>
				<br>

			</table>
			<br>

			<button type="submit">Submit</button>
		</form>
</body>
</html>