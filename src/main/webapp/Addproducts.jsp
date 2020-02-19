<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ProductsAdd</title>
<center>
	<body style="background-color: gray">
		<h2>E-GROCERY PRODUCTS</h2>
		<form action="addpro">
			<table>
				<tr>
					<td>Enter ProductName :</td>

					<td><input type="text" name="pname"
						pattern="[A-Z]{1}[a-z]{3,}"
						title="First letter must be capital then followed by small"
						placeholder="Enter Productname" required autofocus /></td>
				</tr>
				<tr>
					<td>Enter ProductId :</td>
					<td><input type="number" name="pid" placeholder="Enter Pid"
						required /></td>
				</tr>
				<tr>
					<td>Enter manufacturer :</td>
					<td><input type="text" name="company"
						pattern="[A-Z]{1}[a-z]{3,}"
						title="First letter must be capital then followed by small"
						placeholder="Enter manufacturer" required /></td>
				</tr>
				<tr>
					<td>Enter Quantity :</td>
					<td><input type="number" required name="quantity"
						placeholder="Enter quantity" min="0.1" step="0.01"></td>
				</tr>
				<tr>
					<td>Enter Units :</td>
					<td><select name="units" required="required">
							<option value="KGS">KGS</option>
							<option value="GRMS">GRMS</option>
							<option value="LTRS">LTRS</option>
					</select></td>
				</tr>
				<tr>
					<td>Enter price :</td>
					<td><input type="number" name="price" min="1"
						placeholder="Enter price" required /></td>
				</tr>
				<tr>
					<td>Enter stock :</td>
					<td><input type="number" name="stock" min="0"
						placeholder="Enter stock" required /></td>
				</tr>
				<br>
				<br>
			</table>
			<br>
			<br>
			<button type="submit">Submit</button>	<button>
			<a href="adminhome.jsp">Home</a>
		</button>
		</form>
		
	
		<%
			String res = (String) request.getParameter("result");
		%>
		<%
			if (res!=null && res.equals("true")) {
		%>
		<script>
			alert("Product Added Succesfully");
		</script>
		<%
			} %>
	</body>
</html>