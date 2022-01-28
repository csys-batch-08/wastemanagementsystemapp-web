<%@page import="com.cleaningmanagement.model.Request"%>
<%@page import="java.util.List"%>
<%@page import="com.cleaningmanagement.daoimpl.RequestDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>search</title>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
  
}
th, td {
  padding: 15px;
}
table.center {
  margin-left: auto; 
  margin-right: auto;
  width:100%
}
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
  
}
th, td {
  padding: 15px;
}
body{
    
    background-color:lightyellow;
}

table.center {
  margin-left: auto; 
  margin-right: auto;
  width:100%
}
h1
{ 
  text-align:center;
  color:red;
}
table tr:nth-child(even) {
    background: #0000001a;
}
h1
{ 
  text-align:center;
  color:red;
}
</style>
</head>
<body>
<a href="viewRequest.jsp"><button><b>Back</b></button></a>
<h1>RequestDetails</h1>
 <table class="center">
  
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