<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>UpdateRequestStatus</title>
<link rel="stylesheet" type="text/css" href="assets/css/updaterequeststatus.css">
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
<c:if test="${status!=null }">
<h2 class="elementToFadeInAndOut">${status}</h2>
</c:if>
<c:remove var="status" scope="session" />
<div class="loginContent">
<form action="UpdateRequestStatus" method="post">
<h1>Request Status</h1>
<input type="text" name="requeststatus" id="requeststatus" list="Status" placeholder="Select Status" autofocus>
<datalist id="Status">
            <option disabled>--select--</option>
            <option value="pending">PENDING</option>
            <option value="completed">COMPLETED</option>
</datalist>
<div class="formBtn">
<input type="submit" value="update">
</div>
</form>
</div>
</body>
</html>