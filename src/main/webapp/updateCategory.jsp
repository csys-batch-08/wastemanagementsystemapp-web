<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" type="text/css" href="assets/css/updatecategory.css">
<head>
<meta charset="ISO-8859-1">
<title>updateCatgeory</title>
</head>
<body>
<c:set var="weight" value="${param.weight}" />
<c:set var="category" value="${param.category}" />
<c:set var="amount" value="${param.amount}" />
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
<div class="loginContent">
<form action="updateCategoryController">
<h1>Category Details</h1>
<label for="weight"><strong>Weight</strong></label>
<input type="text" name="weight" id="weight" pattern="[0-9]+" title="invalid weight" value="${weight}"  autofocus required><br><br>

<label for="category"><strong>Category</strong></label>
<input type="text" name="category" id="category" pattern="[a-zA-Z]+" title="invalid category" value="${category}" required><br><br>

<label for="amount"><strong>Amount</strong></label>
<input type="text" name="amount" id="amount" pattern="[0-9]+" title="invalid price" value="${amount}" required><br><br>

<div class="formBtn">
<input type="submit" value="Update">
</div>
</form>
</div>
</body>
</html>