<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>UserRegister</title>
<link rel="stylesheet" type="text/css" href="assets/css/userregister.css">
</head>
<body>
<c:if test="${email!=null }">
        <h1 class="elementToFadeInAndOut">${email}</h1>
</c:if>
<c:remove var="email" scope="session" />


<div class="loginContent">
  <form action="UserRegisterController" method="post">
   <h1>User Register</h1>
   <label for="emailid"><strong>Email Id</strong></label>
   <input type="email" name="emailid" id="emailid" placeholder="abc@gmail.com" autofocus required>
   <label for="name"><strong>Name</strong></label>
   <input type="text" name="name" id="name" pattern="[a-zA-Z.]+" title="the name should be in the format of (a-zA-Z.)" placeholder="Enter Name" required><br>
   <label for="password"><strong>Password</strong></label>
   <input type="password" name="password" id="password" pattern="[a-zA-z0-9&@#$_]{8,15}" title="8 or more characters may includes(&@#$_)" placeholder="Enter Password" required>
   <label for="address"><strong>Address</strong></label>
   <input type="text" name="address" id="address" placeholder="Enter Address" required>
   <label for="number"><strong>Number</strong></label>
   <input type="text" name="mobilenumber" id="number" pattern="[0-9]{10}" title="must have 10digits" placeholder="Enter MobileNumber" required>
   	<div class="formBtn">
   	<input type="submit" value="register">
  	</div>
  </form>
 </div>
</body>
</html>