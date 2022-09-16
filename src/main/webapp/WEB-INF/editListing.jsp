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
<title>Edit ${listing.address}</title>
</head>
<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<h1 class="display-4">Edit Listing</h1>
			<a href="/home" class="nav-link mb-3">dashboard</a>
			<form:form action="/listings/${listing.id}/update" modelAttribute="listing" method="POST" class="col-5 mt-4 p-3">
				<input type="hidden" name="_method" value="PUT" />
				<div>
					<form:errors path="*" class="text-danger"/>
				</div>
				<div class="mb-3">
					<form:label path="address" >Address:</form:label>
					<form:input path="address" class="form-control" />
				</div>
				<div class="mb-3">
					<form:label path="price" >Price:</form:label>
					<form:input path="price" class="form-control" />
				</div>
				<div class="mb-3">
					<form:label path="listingDate" >Listing Date:</form:label>
					<form:input type="date" path="listingDate"  class="form-control" />
				</div>
				<button>Submit</button>
			</form:form>
		</div>
	</div>
</body>
</html>