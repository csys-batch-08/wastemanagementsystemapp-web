<%@page import="java.sql.ResultSet"%>
<%@page import="com.cleaningmanagement.daoimpl.EmployeeDAOImpl"%>
<%@page import="com.cleaningmanagement.model.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EmployeesRequest</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
}

body {
	background-color: lightyellow;
}

h1 {
	text-align: center;
	color: red;
}

table {
	margin-left: auto;
	margin-right: auto;
	width: 100%
}

table tr:nth-child(even) {
	background: #0000001a;
}

td.buttonLink a {
	text-decoration: none;
	background: black;
	padding: 10px;
	color: white;
}
</style>
</head>
<body>
	<h1>Request</h1>
	<table class="center">
		<tr>
			<td>RequestID</td>
			<td>UserId</td>
			<td>Category</td>
			<td>Location</td>
			<td>Weight</td>
			<td>Amount</td>
			<td>RequestDate</td>
			<td>EmployeeStatus</td>
			<td>UpdateStatus</td>
		</tr>
		<c:forEach items="${sessionScope.list}" var="request">
			<tr>
				<td>${ request.get(0)}</td>
				<td>${ request.get(1)}</td>
				<td>${ request.get(2)}</td>
				<td>${ request.get(3)}</td>
				<td>${ request.get(4)}</td>
				<td>${ request.get(5)}</td>
				<td>${ request.get(6)}</td>
				<td>${ request.get(7)}</td>
				<td class="buttonLink"><a href="getIdController?requestId=${ request.get(0)}">UpdateStatus</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>