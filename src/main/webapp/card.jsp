<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Card payment</title>
</head>
<center>
	<h1>E-GROCERY CARD PAYMENT</h1>
</center>

<body style="background-color: powderblue;">

	<div align="right">
		<button>
			<a href="Logoutservlet">Log out</a>
	</div>
	</button>
<form action="cardpay">
<center>
			Enter Card Number : <input type="number" name="cardnumber"
				placeholder="Enter card_no " required autofocus /> <br /> <br />
		</center>
		<center>
			Enter cvv :<input type="number" name="cvv"
				placeholder="Enter cvv_no" required /> <br />
		</center>
		<br />
		<center>
			Enter Expiry Month/Year : <input type="text" name="expiry"
				placeholder="Enter expiry " required autofocus /> <br /> <br />
		</center>
		<center>
			Enter Purpose : <input type="text" name="purpose"
				placeholder="For what" required autofocus /> <br /> <br />
		</center>
		<br />
		<center>
			<button type="submit">Submit</button>
		</center>
	
</form>

</body>
</html>