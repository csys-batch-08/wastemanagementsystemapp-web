<%@page import="java.sql.ResultSet"%>
<%@page import="com.cleaningmanagement.daoimpl.EmployeeDaoImpl"%>
<%@page import="com.cleaningmanagement.model.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>EmployeesRequest</title>
<link rel="stylesheet" type="text/css" href="assets/css/showemployeesrequest.css">
</head>
<body>
<c:set var="count" value="1" />
	<h1>Request</h1>
	<table  aria-describedby="Request List" class="center">
	    <tr>
			<th>SerialNumber</th>
			<th>UserName</th>
			<th>Category</th>
			<th>Location</th>
			<th>Address</th>
			<th>Weight</th>
			<th>Amount</th>
			<th>RequestDate</th>
			<th>EmployeeStatus</th>
			<th>RequestStatus</th>
			<th>UpdateStatus</th>
		</tr>
		
		<c:forEach items="${requestScope.list}" var="request">
			<tr>
				<td>${count}</td>
				<td>${ request.get(1)}</td>
				<td>${ request.get(2)}</td>
				<td>${ request.get(3)}</td>
				<td>${ request.get(4)}</td>
				<td>${ request.get(5)}</td>
				<td>${ request.get(6)}</td>
				<fmt:parseDate value="${request.get(7)}" pattern="yyyy-MM-dd" var="requestDate" type="date"/>	
		        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${requestDate}"/></td>
				<td>${ request.get(8)}</td>
				<td>${ request.get(9)}</td>
				<c:if test="${request.get(8)!='done'}">
				<td class="buttonLink"><a href="getIdController?requestId=${ request.get(0)}&requestStatus=${ request.get(9)}">UpdateStatus</a></td>
			</c:if>
			</tr>
			
			<c:set var="count" value="${count+1}" />
		</c:forEach>
	</table>
</body>
</html>