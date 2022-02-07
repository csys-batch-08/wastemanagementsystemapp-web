<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>login</title>
<link rel="stylesheet" type="text/css" href="assets/css/index.css">
</head>
<body class="indeximage">
		<h1 id="title">Waste Management System</h1>

<div class="loginContent">
    <form action="adminController" method = "post">
		<h1>Login</h1>
	    
	    <label for="emailid"><strong>Email Id</strong></label>
		<input type="email" name="emailid" id="emailid" title="must follow the email format" autofocus required>
	    <label for="password"><strong>Password</strong></label>
	    <input type="password" id="password" name="password" pattern="[a-zA-z0-9&@#$_]{8,15}" title="8 or more characters may includes(&@#$_)" required>
	    
	    <c:if test="${inactive!=null }">
        <p class="elementToFadeInAndOut">${inactive}<p>
        </c:if>
        <c:remove var="inactive" scope="session" />
	    
	    <c:if test="${invalid!=null }">
        <p class="elementToFadeInAndOut">${invalid}<p>
        </c:if>
        <c:remove var="invalid" scope="session" />
		
        <div class="formBtn">
		    <input type="submit" value="login"> 
		    <a href="userRegister.jsp"><input type="button" value="Register"></a>
	    </div>
	
	</form>
	
	
	</div>
</body>
</html>