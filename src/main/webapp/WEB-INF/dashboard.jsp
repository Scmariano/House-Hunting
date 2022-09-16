<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>House Hunter Dashboard</title>
</head>
<body>

	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<header class="row justify-content-between align-items-center">
				<div class="col-5 text-start">
					<h4>Hello, ${user.firstName}!</h4>
				</div>
				<div class="col-5 text-end">
					<a href="/logout" class="nav-link">Logout</a>
				</div>
			</header>
		</div>
		<div class="row mx-auto mt-3">
			<table class="table table-striped table-bordered caption-top">
				<thead class="table-info">
					<tr class="align-middle">
						<th>Address</th>
						<th>Listed On</th>
						<th>Added By</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listing" items="${listings}">
						<tr>
							<td><a href="/listings/${listing.id}" class="nav-link"><c:out value="${listing.address}"/></a></td>
							<td><fmt:formatDate value="${listing.listingDate}" pattern="MM/dd/yy"/>						
							<td><c:out value="${listing.user.firstName}" /></td>
							<td>$<c:out value="${listing.price}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-5 text-start">
			<a href="/listings/new" class="nav-link">+Add Listing</a>
		</div>
		
	</div>
	

</body>
</html>