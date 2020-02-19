<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.chainsys.grocerymaven.AdminProfile"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>products</title>
</head>
<center>
	<h1>E-GROCERY PRODUCT LIST</h1>

	<body style="background-color: powderblue;">

		<div align="right">
			<button>
				<a href="index.jsp">Log out</a>
		</div>
		</button>
		<%
			ArrayList<AdminProfile> list = (ArrayList) request.getAttribute("res");
		%>
		<center>

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
						<th>Click to UpdateStock</th>

					</tr>
				</thead>
				<tbody>
					<%
						int i = 1;
						for (AdminProfile ud : list) {
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
						<td><a href=updatestock.jsp?id=<%=ud.getProductId()%>>Updatestock</a></td>
						</tr>
						<%
							}
						%>
					
				</tbody>
			</table>
	</body>
</html>