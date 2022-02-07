<%@page import="com.cleaningmanagement.model.Request"%>
<%@page import="java.util.List"%>
<%@page import="com.cleaningmanagement.daoimpl.RequestDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>search</title>
<link rel="stylesheet" type="text/css" href="assets/css/searchlocation.css">
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
<h1>RequestDetails</h1>
 <table aria-describedby="Request List" class="center">
  
  <tr>
    <th>RequestId</th>
    <th>UserName</th>
    <th>EmployeeName</th>
    <th>Category</th>
    <th>Location</th>
    <th>RequestStatus</th>
    <th>EmpoyeeStatus</th>
    <th>RequestDate</th>
    <th>Update</th>
    
  </tr>
 
    <c:forEach items="${sessionScope.list}" var="request">
    <tr>
     
     <td>${request.getRequestId() }</td>
     <td>${request.getUser().getUserName() }</td>
     <td>${request.getEmployee().getEmpName() }</td>
     <td>${request.getCatogories() }</td>
     <td>${request.getLocation() }</td>
     <td>${request.getStatus() }</td>
     <td>${request.getEmployeestatus() }</td>
     <td>${request.getRequestDate() }</td>
     
     <td><a href="getRequestIdController?Rid=${request.getRequestId() }&empstatus=${request.getEmployeestatus() }"><button>UpdateStatus</button></a></td>
     
    </tr> 
  </c:forEach>
 </table>
</body>
</html>