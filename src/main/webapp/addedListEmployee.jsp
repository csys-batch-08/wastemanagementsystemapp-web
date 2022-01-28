<%@page import="com.cleaningmanagement.model.Employee"%>
<%@page import="java.util.List"%>
<%@page import="com.cleaningmanagement.daoimpl.EmployeeDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AddedListEmployee</title>
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

table.center {
	margin-left: auto;
	margin-right: auto;
}

table {
	width: 100%
}

table tr:nth-child(even) {
	background: #0000001a;
}
</style>
</head>
<body>

	<a href="adminHome.jsp"><button><b>HomePage</b></button></a>
	<h1>Employee Details</h1>
	<table class="center">
		<tr>
			<th>EmailId</th>
			<th>Name</th>
			<th>Password</th>
			<th>Location</th>
			<th>Status</th>
		</tr>
<c:forEach items="${sessionScope.list}" var="employee">
		<tr>
			<td>${employee.getEmpEmail()}</td>
			<td>${employee.getEmpName() }</td>
			<td>${employee.getEmpPassWord()}</td>
			<td>${employee.getLocation() }</td>
			<td>${employee.getStatus() }</td>
		</tr>
</c:forEach>
	</table>
</body>
</html>