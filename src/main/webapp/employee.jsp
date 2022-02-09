<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add Employee</title>
<link rel="stylesheet" type="text/css" href="assets/css/employee.css">
</head>
<body>
<div class="align">
<div class="header">
 <div class="headerMenu">
 	 
	  <a href="category.jsp"><button><strong>AddCategory</strong></button></a>
	  <a href="listEmployeeController"><button><strong>UpdateEmployeeStatus</strong></button></a>
	  <a href="viewRequestController"><button><strong>UpdateRequestStatus</strong></button></a>
	  <a href="calculateWeight.jsp"><button><strong>CalculateWeight</strong></button></a>
	  <a href="listCategoryController"><button><strong>UpdateCatgeory</strong></button></a>
	  <a href="index.jsp"><button><strong>logOut</strong></button></a>
 </div>
</div>
 <c:if test="${alreadyused!=null }">
        <h3 class="elementToFadeInAndOut">${alreadyused}</h3>
        </c:if>
<c:remove var="alreadyused" scope="session" />

<c:if test="${locationused!=null }">
        <h3>${locationused}</h3>
        </c:if>
<c:remove var="locationused" scope="session" />
<div class="loginContent">

<form action="EmployeeController">
<h1>Add Employee</h1>
<label for="emailid"><strong>Email Id:</strong></label>
<input type="email" name="emailid" id="emailid" placeholder="abc@gmail.com" autofocus required><br><br>

<label for="name"><strong>Name:</strong></label>
<input type="text" name="name" id="name" pattern="[a-zA-Z]+" title="invalid name" placeholder="Enter Name" required><br><br>

<label for="password"><strong>Password:</strong></label>
<input type="password" name="password" id="password" pattern ="[a-zA-z0-9&@#$_]{8,15}" title="8 or more charecters may includes(&@#$_)" placeholder="Enter Password" required><br><br>

<label for="location"><strong>Location:</strong></label>
<input type="text" name="location" id="location" pattern="[a-zA-Z ]+" title="invalid location" placeholder="Enter Location" required><br><br>
<div class="formBtn">
<input type="submit" value="Add">
</div>
</form>
</div>
</div>
</body>
</html>