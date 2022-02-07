<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>ConfirmCancel</title>
</head>
<body>
<c:set var="requestId" value="${param.requestId}" />
<c:set var="category" value="${param.category}" />
<c:set var="weight" value="${param.weight}" />
<c:set var="amount" value="${param.amount}" />
<c:set var="location" value="${param.location}" />
<c:set var="status" value="${param.status}" />
<p>Category:${category}</p>
<p>Weight:${weight}</p>
<p>Amount:${amount}</p>
<p>Location:${location}</p>
<h2>Do you Want to Cancel the request</h2>
<a href="Deleteserv?requestId=${requestId}&amount=${amount}&status=${status}"><button>YES</button></a>
<a href="deleteRequest.jsp"><button>NO</button></a>
</body>
</html>