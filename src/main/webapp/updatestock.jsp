<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock update</title>
</head>
	<center>
		<h2>E-GROCERY UPDATE PRODUCT STOCK</h2>

	<br><br><div align="right">
			<button>
				<a href="index.jsp">Logout</a>
		</div>
		</button>
	<body style="background-color: powderblue">
	<%
	int id=Integer.parseInt(request.getParameter("id"));
	%> 
	<form action="updatestockserv">
	Selected Product_id :<input type="number" name="pid" value="<%=id%>" readonly ><br><br>
	Enter Stock value : <input type="number" name="stock"  placeholder="Enter stock"
						required >
				<br><br><button type="submit">ChangeStock</button>
						
	</form>
						
</html>