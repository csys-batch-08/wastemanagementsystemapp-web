<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" type="text/css" href="assets/css/category.css">
<head>
<meta charset="ISO-8859-1">
<title>CategoryDetails</title>
</head>
<body>
<div class="header">
 <div class="headerMenu">
 	  <a href="employee.jsp"><button><strong>AddEmployee</strong></button></a>
	  <a href="listEmployeeController"><button><strong>UpdateEmployeeStatus</strong></button></a>
	  <a href="viewRequestController"><button><strong>UpdateRequestStatus</strong></button></a>
	  <a href="calculateWeight.jsp"><button><strong>CalculateWeight</strong></button></a>
	  <a href="listCategoryController"><button><strong>UpdateCatgeory</strong></button></a>
	  <a href="index.jsp"><button><strong>logOut</strong></button></a>
	  
 </div>
</div>
<div class="loginContent">
<form action="CategoryController">
<h1>Category Details</h1>
<label for="weight"><strong>Weight</strong></label>
<input type="text" name="weight" id="weight" pattern="[0-9]+" title="invalid weight" placeholder="Weight in Kg" required autofocus><br><br>

<label for="category"><strong>Category</strong></label>
<input type="text" name="category" id="category" pattern="[a-zA-Z]+" title="invalid category" placeholder=" Enter Category" required><br><br>

<label for="amount"><strong>Amount</strong></label>
<input type="text" name="amount" id="amount" pattern="[0-9]+" title="invalid amount" placeholder="Amount in Rs" required><br><br>

<div class="formBtn">
<input type="submit" value="Add">
</div>
</form>
</div>
</body>
</html>