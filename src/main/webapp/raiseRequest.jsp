
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RaiseRequest</title>
<style>
body{
background-image: url('images/background1.jpg');
    margin: 0px;
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
}
/* .loginContent {
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
} */
.loginContent form {
    background: white;
    padding: 15px;
    text-align: left;
}
.loginContent form label {
    font-weight: bold;
}
.loginContent form h1 {
    margin: 0px 0px 7px;
    text-align: center;
}
.loginContent form input {
    width: 95%;
    border: none;
    background: aliceblue;
    padding: 10px;
    margin: 10px 0px;
}
.loginContent form {
    background: white;
    padding: 15px;
    text-align: left;
    
}
.formBtn {
    display: flex;
    justify-content: center;
}
.formBtn input {
    margin: 4px 4px;
    width: auto !important;
    padding: 10px 25px !important;
    background: black !important;
    color: white;
    font-weight: bold;
}
h1.text-center {
    text-align: center;
    color: red;
    font-weight:bold
}
.tss{
position: absolute;
top: 10px;
left: 10px;
}
</style>
</head>
<body>
<div class="tss">
<a href="userHome.jsp"><button><b>HomePage</b></button></a>
</div>
<div class="loginContent">
    <c:if test="${notfound!=null }">
        <h1 class="text-center">${notfound}</h1>
        </c:if>
        <c:remove var="notfound" scope="session" />
        
    <c:if test="${insufficient!=null }">
        <h1 class="text-center">${insufficient}</h1>
        </c:if>
        <c:remove var="insufficient" scope="session" />
 
<c:set var="category" value="${param.category}" />
 
 <form action="RasieRequestController">
  <h1>Raise The Request</h1>
  <label for="category">Category</label>
  <input type="text" name="category" id="category"  pattern="[a-zA-Z]+" title="category should be in letters" value ="${category }" required><br><br>
  

  <label for="location">Location</label> 
  <input type="text" name="location" id="location" pattern="[a-zA-Z]+" title="location should be in letters" required ><br><br>
 
  <div class="formBtn">
  <input type="submit" value="RaiseRequest">
  </div>
 
 </form>
</div>
</body>
</html>