<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.cleaningmanagement.daoimpl.UserDAOImpl"%>
<%@page import="com.cleaningmanagement.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>billPage</title>
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
</style>
</head>
<body>
<c:if test="${deduct!=null }">
<h1>Amount is Deducted successfully</h1>
</c:if>
    <a href="userHome.jsp"><button><b>HomePage</b></button></a>
	<h1>Bill</h1>

	<table>
		<tr>
            <th>UserName</th>
			<th>Location</th>
			<th>Category</th>
			<th>Weight</th>
			<th>RequestDate</th>
			<th>Amount</th>
			<th>AvailableBalance</th>
		</tr>
<c:forEach items="${sessionScope.listObject}" var="list">
		<tr>
		    <td>${ list.get(0) }</td>
		    <td>${ list.get(1) }</td>
		    <td>${ list.get(2) }</td>
		    <td>${ list.get(3) }</td>
		    <td>${ list.get(4) }</td>
		    <td>${ list.get(5) }</td>
		    <c:if test="${amount!=null }">
		    <c:set var="total" value="${amount - list.get(5)}" />
			<td>${total }</td>
			</c:if>
        </tr>
		
</c:forEach>
	</table>
</body>
</html>