<%@page import="com.chainsys.grocerymaven.UserDisplay"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>

<%@page import="com.chainsys.grocerymaven.UserProfileDaoImpl"%>
<%@page import="com.chainsys.grocerymaven.UserProfileDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products page</title>
</head>
<center>
	<h1>E-GROCERY PRODUCT LIST</h1>

	<body style="background-color: powderblue;">

		<div align="right">
			<button>
				<a href="Logoutservlet">Log out</a>
		</div>
		</button>

		<%
			String username = (String) session.getAttribute("LOG IN USER");
		%>
		</br> LOGIN AS
		<%=username.toUpperCase()%>
</center>

</div>
<form action="sort">
	<form>
		Sort by type : <select name="type" required="required">
			<option value="">None</option>
			<option value="order by  p.price_rs asc">Sort by Price Low
				to High</option>
			<option value="order by  p.price_rs desc">Sort by Price High
				to Low</option>
			<option value=" order by pr.rating asc">Sort by Rating Low
				to High</option>
			<option value=" order by pr.rating desc">Sort by Rating High
				to Low</option>
		</select>

		<button type="submit">Sort</button>

	</form>

	<div align="right">
		<button>
			<a href="mycart.jsp">Cart</a>
		</button>

	</div>
	<br>
	<form action="search">


		Search Products : <input type="text" name="field" pattern="[a-z]"
			title="Type only characters" placeholder="Enter Product Name "
			required />
		<button type="submit">Search</button>
	</form>
	<br> <br>
	<%
		ArrayList<UserDisplay> list = (ArrayList) request.getAttribute("listpro");
		if (list.size() > 0) {
	%>			<center>
	
	<form action="cart">
		<table border="1">
			<thead>
				<tr>
					<th>S.no</th>
					<th>ProductName</th>
					<th>ProductId</th>
					<th>Manufacturer</th>
					<th>Quantity</th>
					<th>Unit</th>
					<th>Price(RS)</th>
					<th>Stock</th>
					<th>Status</th>
					<th>Rating</th>
					<th>Review</th>
					<th>Click to Order</th>
					<th>No_of_Items</th>
				</tr>
			</thead>
			<tbody>
				<%
					int i = 1;
						for (UserDisplay ud : list) {
				%>
				<tr>

					<td><%=i++%></td>
					<td><%=ud.getProductName()%></td>
					<td><%=ud.getProductId()%></td>
					<td><%=ud.getManufacturer()%></td>
					<td><%=ud.getQuantity()%></td>
					<td><%=ud.getUnit()%></td>
					<td><%=ud.getPriceRS()%></td>
					<td><%=ud.getStock()%></td>
					<td><%=ud.getStatus()%></td>
					<td><%=ud.getRating()%></td>
					<td><%=ud.getReview()%></td>
					<td><input type="checkbox" name="pid"
						id="pid_<%=ud.getProductId()%>"
						onchange="disable(<%=ud.getProductId()%>)"
						value="<%=ud.getProductId()%>"></td>
					<%
						if ((int) ud.getStock() == 0) {
					%>
					<td><select name="qty_<%=ud.getProductId()%>"
						onchange="disable(<%=ud.getProductId()%>)" disabled>
							<option value="0"></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
					</select></td>
					<%
						} else {
					%>
					<td><select name="qty_<%=ud.getProductId()%>"
						onchange="disable(<%=ud.getProductId()%>)" autofocus>
							<option value="0"></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
					</select></td>
				</tr>
				<%
					}
						}
					} else {
				%>
				<h3>No results found</h3>
				<%
					}
				%>
			</tbody>
		</table>
		<br> <br>

		<button type="submit">Add to Cart</button>

	</form>
	<br>
	<button>
		<a href="home.jsp">Home</a>
	</button>
	<script>
	$(document).ready(function(){
	    $("form").submit(function(){
	 if ($('input:checkbox').filter(':checked').length < 1){
	        alert("Check at least one Game!");
	 return false;
	 }
	    });
	});</script>
	<script>
						function disable(productId) {
								 
								var i=0;
								var ch = document.getElementById("pid_" + productId);
								if (ch.checked){
									ch.checked=false;
								}
								else{ 
									ch.checked=true;
								}
							}
	</script>
	</body>
</html>