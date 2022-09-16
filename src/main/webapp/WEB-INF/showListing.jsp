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
<title>View Listing</title>
</head>
<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<a href="/home" class="nav-link mb-3">dashboard!</a>
			<main class="col-8 px-4 py-3 border border-1 border-pirmary rounded bg-light">
				<div>
					<h1 class="display-5 mb-4"><c:out value="${listing.address}" /></h1>
					<p>Address: <c:out value="${listing.address}" /> </p>
					<p class="mb-3">Listing Date: <fmt:formatDate value="${listing.listingDate}" pattern="MM/dd/yy"/></p>
					<p class="mb-2">Price: $<c:out value="${listing.price}" /></p>
				</div>
				<div class= "text-start">
					<c:if test="${listing.user.id == user.id}">
						<a href="/listings/${listing.id}/edit" class="nav-link">Edit</a>
						<a href="/listings/${listing.id}/delete" class="nav-link">Delete</a>
					</c:if>
				</div>
			</main>
		</div>
		<div class="container mx-auto mt-4">
			<main class="col-8 px-4 py-3 border border-1 border-pirmary rounded bg-light">
				<div>	
					<table>
						<thead>
							<tr>
								<th>Notes:</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="note" items="${listing.notes}">
								<tr>
									<td>
										<h4>Added by <c:out value="${note.user.firstName}"></c:out></h4>
										<p>-<c:out value="${note.text}"></c:out></p>
										<p class="mb-3">Date: <fmt:formatDate value="${note.createdAt}" pattern="MM/dd/yy"/></p>
										
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<form:form action="/listings/${listing.id} " modelAttribute="note" method="POST" class="col-5 mt-4 p-3">
					<div>
						<form:errors path="*" class="text-danger"/>
					</div>
					<div class="mb-3">
						<form:label path="text" >Notes:</form:label>
						<form:textarea path="text" type="text" class="form-control" />
					</div>
					<button>Submit</button>
				</form:form>
			</main>
		</div>
	</div>
</body>
</html>