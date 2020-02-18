<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password change</title>
</head>
<center>
	<h1>E-GROCERY CHANGE PASSWORD</h1>
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
	</br>
	<center>
		LOGIN AS
		<%=username.toUpperCase()%></center>
	<form action="changepass">
		<center>
			</br> <br /> Enter MailId : <input type="email" name="mail"
				placeholder="Enter Mail_id " required autofocus /> <br />
			<br />
		</center>
		<center>
			Enter New Password :<input type="password" name="npassword"
			pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
						title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
				placeholder="Enter Password" required /> <br />
			<br />
		</center>
		<center>
			Re-type Password :<input type="password" name="cpassword"
				placeholder="confirm Password" required /> <br />
			<br />
		</center>
		<br />
		<center>
			<button type="submit">Submit</button>
		</center>
	</form>
	<%
		String res = (String) request.getParameter("res");
	%>
	<%
		if (res != null) {
	%>
	<script>
		alert("Password Mismatch Try Again");
	</script>
	<%
		}
	%>
</body>
</html>