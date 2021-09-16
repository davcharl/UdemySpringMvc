<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />

<head>
	
</head>
<body>
	
	<h2>Customer form</h2>
	
	<form:form action="saveCustomer" modelAttribute="customer" method="POST">
		
		<!-- associate data with customer -->
		<form:hidden path="id"/>
		
		<table>
			<tbody>
				<tr>
					<td><label>First name:</label></td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td><label>Last name:</label></td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td><label>Email:</label></td>
					<td><form:input path="email" /></td>
				</tr>
			</tbody>
		</table>
		
		<input type="submit" value="Save" class="save" />
	
	
	
	</form:form>
	
	<p></p>
	<a href="${pageContext.request.contextPath}/customer/list"><button class="add-button">return to main</button></a>
	
</body>

</html>