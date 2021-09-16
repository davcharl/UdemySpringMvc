<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="com.luv2code.springdemo.utility.SortUtils" %>

<!DOCTYPE html>
<html>
	<head>
		<title>List Customers</title>
		<!-- reference the stylesheet -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
			<div>
				<p></p>
				<input  class="add-button"
						type="button"
						value="add customer"
						onclick="window.location.href='showCustomerForm'; return false;" />
				<!-- Add Search box -->
				<form:form action="search" method="GET">
					Search customer: <input type="text" name="theSearchName" />
					<input type="submit" value="Search" class="add-button" />
				</form:form>
			</div>
			<div id="container">
				<div id="content">
					<!-- add out html table here -->
					<table>
						<!-- Construct url for sort links -->
							<c:url var="sortFirstName" value="/customer/list" >
								<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" ></c:param>
							</c:url>
							<c:url var="sortLastName" value="/customer/list" >
								<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" ></c:param>
							</c:url>
							<c:url var="sortEmail" value="/customer/list" >
								<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" ></c:param>
							</c:url>
						<tr>
							<th><a href="${sortFirstName}">First Name</a></th>
							<th><a href="${sortLastName}">Last Name</a></th>
							<th><a href="${sortEmail}">Email</a></th>
							<th>Action</th>
						</tr>
						<!-- loop over customers and print out a row for each customer -->
						<c:forEach var="tempCustomer" items="${customers}">
							
							<!-- construct an "update" link with the customer id -->
							<c:url var="updateLink" value="/customer/showFormForUpdate">
								<c:param name="customerId" value="${tempCustomer.id}"></c:param>
							</c:url>
							<c:url var="deleteLink" value="/customer/delete">
								<c:param name="customerId" value="${tempCustomer.id}"></c:param>
							</c:url>
							
							
							
							<tr>
								<td> ${tempCustomer.firstName} </td>
								<td> ${tempCustomer.lastName} </td>
								<td> ${tempCustomer.email} </td>
								<td>
									<a href="${updateLink}">Update</a>
									|
									<a href="${deleteLink}" onclick="if(!(confirm('Are you sure that you want to delete this customer?')))return false">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>