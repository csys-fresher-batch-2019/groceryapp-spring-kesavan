<html>
<head>
<title>Login</title>
</head>
<center>
	<h1>E-GROCERY LOGIN</h1>
	<body style="background-color: powderblue">
		<div align="right">

			<h4>
				New User ?<br>
				<a href="Register.jsp">Register Here</a>
			</h4>
		</div>
		<br>
		<form action="login" method=post>
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
		<button>
			<a href="forgot.jsp">Forgot Password</a>
		</button>
		<br>
		<br>


		<%
			String result = (String) request.getParameter("res");
			if (result != null) {
				out.println("<center><font color=red><br>" + result + "</font></center>");
			}
		%>
		<%
			String res = (String) request.getParameter("result");
		%>
		<%
			if (res != null) {
		%>
		<script>
			alert("Invalid Email Id Retry Again");
		</script>
		<%
			}
		%>

		<%
			String res1 = (String) request.getParameter("stat");
		%>
		<%
			if (res1 != null) {
		%>
		<script>
			alert("Password Updated Successfully\n Please Login Again");
		</script>
		<%
			}
		%>

	</body>
</html>