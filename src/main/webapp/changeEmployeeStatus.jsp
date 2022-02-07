<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>ChangeEmployeeStatus</title>
<link rel="stylesheet" type="text/css" href="assets/css/changeemployeestatus.css">
</head>
<body>
<a href="index.jsp"><button><strong>logOut</strong></button></a>
 <c:if test="${cancel!=null }">
        <h2>${cancel}</h2>
        </c:if>
        <c:remove var="cancel" scope="session" />

<div class="loginContent">
<form action="EmpstatusChange" method="post">
<h1>Status</h1>
<input type="text" name="status" id="status" list="EmployeeStatus" placeholder="Select Status" autofocus >
<datalist id="EmployeeStatus">
            <option disabled>--select--</option>
            <option value="pending">PENDING</option>
            <option value="inprogress">INPROGRESS</option>
            <option value="done">DONE</option>
</datalist>
<div class="formBtn">
<input type="submit" value="update">
</div>
</form>
</div>
</body>
</html>