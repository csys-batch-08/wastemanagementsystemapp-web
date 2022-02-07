<%@page import="com.cleaningmanagement.daoimpl.UserDaoImpl"%>
<%@page import="com.cleaningmanagement.model.User"%>
<%@page import="com.cleaningmanagement.model.CategoryDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.cleaningmanagement.daoimpl.CategoryDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>CategoryDetails</title>
<link rel="stylesheet" type="text/css" href="assets/css/viewcategory.css">
</head>
<body>
<div class="header">
 <div class="headerMenu">
      <a href="employee.jsp"><button><strong>AddEmployee</strong></button></a>
	  <a href="category.jsp"><button><strong>AddCategory</strong></button></a>
	  <a href="listEmployeeController"><button><strong>UpdateEmployeeStatus</strong></button></a>
	  <a href="viewRequestController"><button><strong>UpdateRequestStatus</strong></button></a>
	  <a href="calculateWeight.jsp"><button><strong>CalculateWeight</strong></button></a>
	  <a href="index.jsp"><button><strong>logOut</strong></button></a>
 </div>
</div>
<c:set var="count" value="1" />
<table aria-describedby="View Category" class="center">
 <tr>
   <th>SerialNumber</th>
   <th>Weight</th>
   <th>Category</th>
   <th>Amount</th>
   <th>updateRequest</th>
 </tr>
 <c:forEach items="${requestScope.list}" var="category">
 <tr>
   <td>${count}</td>
   <td>${ category.getWeightInKg()}</td>
   <td>${ category.getCategory() }</td>
   <td>${ category.getAmount() }</td>
   <td><a href="updateCategory.jsp?weight=${ category.getWeightInKg()}&category=${ category.getCategory() }&amount=${ category.getAmount() }"><button>updateRequest</button></a></td>
 </tr>
 <c:set var="count" value="${count}" />
</c:forEach>
</table>

</body>
</html>