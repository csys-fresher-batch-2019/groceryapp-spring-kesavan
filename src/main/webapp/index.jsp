<html>
<head>
<title>Login</title>
<style>
	body{
		background-color:#85C1E9;
		background-repeat:no-repeat;
		background-image:url("c.gif");
		}
		.nav
		{
			width:100%;
			height:50px;
			background-color:#273746;
			color:#FDFEFE;
			margin-top:0px;
		}
		*{
			box-sizing:border-box;
		}
		button{
			background-color:#52B0F7;
			color:#010314;
			width:100px;
			height:50px;
			border-radius:10px;	
			display:inline-end;	
		}
		form
		{
			width:40%;
			float:right;
			margin-right:90px;
			display:block;
			background-color:#95999B;
			border-radius:15px;
			opacity:0.9;
			font-size:30px;	
		}
		input[type=text],input[type=password]{
			width:60%;
			border:2px solid #ccc;
			border-radius:7px;
			height:30px;
			padding:12px;
			}
			label
			{
				width:60%;
				padding:12px,12px,12px,0;
				display:inline-block;
				margin-top:10px;
			}
		
</style>
</head>

<center>
	<div class="nav">
	<h1>E-GROCERY LOGIN</h1></div>
	<body >
		<div align="right">

			<h4>
				New User ?<br>
				<a href="Register.jsp">Register Here</a>
			</h4>
		</div>
		<div align="left">
<h3>
			<a href="adminindex.jsp">AdminLogin</a>
		</h3>
		</div>
		<br>
		<div class = "y">
		</div>
		<form action="login" method=post>
			<table>
				<tr><label>Enter UserName :</label>
				</tr>
				<tr>
					<input type="text" name="Username" placeholder="Enter Username"
						required autofocus />
				</tr>
				<tr>
					<pre></pre>
					<label>Enter Password : </label>
					<input type="password" name="password" placeholder="Enter Password"
						required />
				</tr>
				<br>
				<br>
				<button type="submit">Submit</button>

			</table>
			<a href="forgot.jsp">Forgot Password</a>
			<%
			String result = (String) request.getParameter("res");
			if (result != null) {
				out.println("<center><font color=red><br>" + result + "</font></center>");
			}
		%>
		</form>
			
		<br>
		<br>


		
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