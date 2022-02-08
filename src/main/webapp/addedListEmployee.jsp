<%@page import="com.cleaningmanagement.model.Employee"%>
<%@page import="java.util.List"%>
<%@page import="com.cleaningmanagement.daoimpl.EmployeeDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>AddedListEmployee</title>
<link rel="stylesheet" type="text/css" href="assets/css/addedlistemployee.css">
</head>
<body>
<div class="header">
 <div class="headerMenu">
      <a href="employee.jsp"><button><strong>AddEmployee</strong></button></a>
	  <a href="category.jsp"><button><strong>AddCategory</strong></button></a>
	  <a href="listEmployeeController"><button><strong>UpdateEmployeeStatus</strong></button></a>
	  <a href="viewRequestController"><button><strong>UpdateRequestStatus</strong></button></a>
	  <a href="calculateWeight.jsp"><button><strong>CalculateWeight</strong></button></a>
	  <a href="listCategoryController"><button><strong>UpdateCatgeory</strong></button></a>
	  <a href="index.jsp"><button><strong>logOut</strong></button></a>
 </div>
</div>
<c:set var="count" value="1" />
	<h1>Employee Details</h1>
	<table  aria-describedby="Employee List" class="center">
	    <tr>
		    <th>SerialNumber</th>
			<th>EmailId</th>
			<th>Name</th>
			<th>Password</th>
			<th>Location</th>
			<th>Status</th>
		</tr>
<c:forEach items="${requestScope.list}" var="employee">
		<tr>
		    <td>${count}</td>
			<td>${employee.getEmpEmail()}</td>
			<td>${employee.getEmpName() }</td>
			<td>${employee.getEmpPassWord()}</td>
			<td>${employee.getLocation() }</td>
			<td>${employee.getStatus() }</td>
		</tr>
		<c:set var="count" value="${count+1}" />
</c:forEach>
	</table>
</body>
</html>