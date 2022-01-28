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
<title>updateStatus</title>
<style>
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
.headerMenu a button {
    border: none;
    padding: 10px;
    background: black;
    color: white;
}

.headerMenu {
    display: flex;
    justify-content: space-around;
    background: aliceblue;
    padding: 15px;
}
.headerMenu a button {
    border: none;
    padding: 10px;
    background: black;
    color: white;
    margin: 0px 20px;
    border-radius: 3px;
}
.serach input {
    padding: 10px;
    font-size: 15px;
    text-transform: capitalize;
    font-weight: bold;
}
.serach {
    text-align: right;
    margin-bottom: 12px;
}
.headerMenu input {
    border: none;
    padding: 10px;
    background: black;
    color: white;
}
</style>
</head>
<body>

<div class="header">
 <div class="headerMenu">
 	  <a href="employee.jsp"><button><b>AddEmployee</b></button></a>
	  <a href="category.jsp"><button><b>AddCategory</b></button></a>
	  <a href="listEmployeeController"><button><b>UpdateEmployeeStatus</b></button></a>
	  <a href="calculateWeight.jsp"><button><b>CalculateWeight</b></button></a>
 </div>
</div>
   <h1>RequestDetails</h1>
   <form action="SearchController" class="serach">
<label for="search"></label>
<input type="text" name="search" id="search" placeholder="search">
<input type="submit" value="search">
</form>
<c:if test="${status!=null }">
<p>${status}<p>
</c:if>
<c:remove var="status" scope="session" />
 <table class="center">
  
  <tr>
    <th>RequestId</th>
    <th>UserName</th>
    <th>EmployeeName</th>
    <th>Category</th>
    <th>Location</th>
    <th>RequestStatus</th>
    <th>EmployeeStatus</th>
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