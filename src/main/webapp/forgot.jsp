<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
	<center>
		<h1>E-GROCERY LOGIN</h1>
		<body style="background-color: powderblue">
		<pre>
		
		</pre>
			<form action="forgotpass">
				<center>
					<table>
						<tr>
							<td>Enter Username:</td>
							<td><input type="text" name="user"
								placeholder="Enter username " required autofocus /></td>
						</tr>
						<tr>
							<td>Enter MailId :</td>
							<td><input type="text" name="mail"
							pattern="[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}"
						title="Example:xyz@gmail.com" placeholder="Enter emailid "
							 required autofocus /></td>
						</tr>
						<tr>
							<td>Enter New Password :</td>
							<td><input type="password" name="npassword"
								placeholder="Enter Password" required /></td>
						</tr>
						<tr>
							<td>Re-type Password :</td>
							<td><input type="password" name="cpassword"
								placeholder="confirm Password" required /></td>
						</tr>
						</tr>
					</table>
					<br>	
					<button type="submit">Submit</button>
			</form>

			<%
				String res = (String) request.getParameter("result");
			%>
			<%
				if (res != null) {
			%>
			<script>
				alert("Password Mismatch Retry Again");
			</script>
			<%
				}
			%>

		</body>
</html>