<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin login</title>

<center>
	<body style="background-color: gray">
		<h2>E-GROCERY ADMIN PORTAL</h2>
		<form action="Adminlogin">
			<br> <br> <br>
			<table>
				<tr>Enter UserName :
				</tr>
				<tr>
					<input type="text" name="Username" placeholder="Enter Username"
						required autofocus />
				</tr>
				<tr>
					<pre></pre>
					Enter Password :
					<input type="password" name="password" placeholder="Enter Password"
						required />
				</tr>
				<br>
				<br>
				<button type="submit">Submit</button>

			</table>
		</form>
		<%
			String result = (String) request.getParameter("res");
			if (result != null) {
				out.println("<center><font color=red><br>" + result + "</font></center>");
			}
		%>
</body>
</html>