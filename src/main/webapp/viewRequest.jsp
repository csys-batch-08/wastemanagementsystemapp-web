<%@page import="com.cleaningmanagement.model.Request"%>
<%@page import="java.util.List"%>
<%@page import="com.cleaningmanagement.daoimpl.RequestDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>updateStatus</title>
<link rel="stylesheet" type="text/css" href="assets/css/viewrequest.css">
</head>
<body>

<div class="header">
 <div class="headerMenu">
 	  <a href="employee.jsp"><button><strong>AddEmployee</strong></button></a>
	  <a href="category.jsp"><button><strong>AddCategory</strong></button></a>
	  <a href="listEmployeeController"><button><strong>UpdateEmployeeStatus</strong></button></a>
	  <a href="calculateWeight.jsp"><button><strong>CalculateWeight</strong></button></a>
	  <a href="index.jsp"><button><strong>logOut</strong></button></a>
 </div>
</div>
   <h1>RequestDetails</h1>
   <form action="SearchController" class="serach">
<label for="search"></label>
<input type="text" name="search" id="search" placeholder="Search">
<input type="submit" value="search">
</form>


<c:if test="${noresult!=null }">
<h3>${noresult}</h3>
</c:if>
<c:remove var="noresult" scope="session" />
<c:set var="count" value="1" />
 <table  aria-describedby="View Request" class="center">
 
  <tr>
    <th>SerialNumber</th>
    <th>UserName</th>
    <th>EmployeeName</th>
    <th>Category</th>
    <th>Location</th>
    <th>RequestStatus</th>
    <th>RequestDate</th>
    <th>EmployeeStatus</th>
    <th>Address</th>
    <th>Update</th>
    
  </tr>
  <c:forEach items="${requestScope.list}" var="request">
    <tr>
     <td>${count}</td>
     <td>${request.getUser().getUserName() }</td>
     <td>${request.getEmployee().getEmpName() }</td>
     <td>${request.getCatogories() }</td>
     <td>${request.getLocation() }</td>
     <td>${request.getStatus() }</td>
     <fmt:parseDate value="${request.getRequestDate() }" pattern="yyyy-MM-dd" var="requestDate" type="date"/>	
     <td><fmt:formatDate pattern="dd/MM/yyyy" value="${requestDate}"/></td>
     <td>${request.getEmployeestatus() }</td>
     <td>${request.getAddress() }</td>
     <td><a href="getRequestIdController?Rid=${request.getRequestId() }&empstatus=${request.getEmployeestatus() }"><button>UpdateStatus</button></a></td>
    </tr>
    <c:set var="count" value="${count+1}" /> 
  </c:forEach>
 </table>
</body>
</html>