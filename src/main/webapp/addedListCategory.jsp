<%@page import="com.cleaningmanagement.model.CategoryDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.cleaningmanagement.daoimpl.CategoryDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AddedListCategory</title>
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
	width: 50%;
}

table tr:nth-child(even) {
	background: #0000001a;
}
</style>
</head>
<body>
	<a href="adminHome.jsp"><button><b>HomePage</b></button></a>
	<h1>Category Details</h1>

	<table class="center">
		<tr>
			<th>Weight</th>
			<th>Category</th>
			<th>Amount</th>
		</tr>
<c:forEach items="${sessionScope.list}" var="categoryDetails">
		<tr>
			<td>${categoryDetails.getWeightInKg()}</td>
			<td>${categoryDetails.getCategory()}</td>
			<td>${categoryDetails.getAmount()}</td>
		</tr>
</c:forEach>
	</table>

</body>
</html>