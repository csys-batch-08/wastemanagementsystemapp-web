<%@page import="com.cleaningmanagement.model.Employee"%>
<%@page import="com.cleaningmanagement.daoimpl.EmployeeDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>status</title>
<link rel="stylesheet" type="text/css" href="assets/css/changestatus.css">
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
<div class="loginContent">
<form action="UpdateEmployeeMessage" method="post">
<h1>Status</h1>
<input type="text" name="status" list="EmployeeStatus" placeholder="Select Status" autofocus>
<datalist id="EmployeeStatus">
            <option disabled>--select--</option>
            <option value="active">ACTIVE</option>
            <option value="inactive">INACTIVE</option>
</datalist>
<div class="formBtn">
<input type="submit" value="update">
</div>
</form>
</div>
</body>
</html>