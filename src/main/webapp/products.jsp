<%@page import="com.chainsys.grocery.model.UserDisplay"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.chainsys.grocery.dao.impl.UserProfileDaoImpl"%>
<%@page import="com.chainsys.grocery.dao.UserProfileDao"%>
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
		</br> LOGGED IN AS
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


		Search Products : <input type="text" name="field" pattern="[a-z]{1,}"
			title="Type only characters" placeholder="Enter Product Name "
			required />
		<button type="submit">Search</button>
	</form>
	<br>
	<%
		String result = (String) request.getParameter("res");
		if (result != null) {
			out.println("<center><font color=green><br>" + result + "</font></center>");
		}
	%>
	<%
		ArrayList<UserDisplay> list = (ArrayList) request.getAttribute("listpro");
		if (list.size() > 0) {
			int i=0;
	%>
	<center>
		<br>

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
						<th>Selected Items</th>
						<th>No_of_Items</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listpro}" var="ud">
						<c:set value="0" var="i"></c:set>
						<tr>
							<td><%=++i %></td>
							<td>${ud.productName}</td>
							<td>${ud.productId}</td>
							<td>${ud.manufacturer}</td>
							<td>${ud.quantity}</td>
							<td>${ud.unit}</td>
							<td>${ud.priceRS}</td>
							<td>${ud.stock}</td>
							<td>${ud.status}</td>
							<td>${ud.rating}</td>
							<td>${ud.review}</td>
							<td><input type="checkbox" name="pid"
								id="pid_${ud.productId}" onchange="disable(${ud.productId})"
								value="${ud.productId}"></td>
							<c:choose>
								<c:when test="${ud.stock == 0}">
									<td><select name="qty_${ud.productId}"
										onchange="disable(${ud.productId})" disabled>
											<option value="0"></option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
									</select></td>
								</c:when>
								<c:otherwise>
									<td><select name="qty_${ud.productId}"
										onchange="disable(${ud.productId})" autofocus>
											<option value="0"></option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
									</select></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>

					<%
						} else {
					%>
					<h3>No results found</h3><br>
					<button>
			<a href="productlist">Back</a>
		</button>
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
						function disable(productId) {
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