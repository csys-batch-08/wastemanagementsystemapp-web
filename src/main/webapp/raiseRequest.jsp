
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>RaiseRequest</title>
</head>
<link rel="stylesheet" type="text/css" href="assets/css/raiserequest.css">
<body>
 <div class="header">
   <div class="headerMenu">
  <a href="viewCategoryController"><button><strong>AvailableCategories</strong></button></a>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <a href="rechargeWallet.jsp"><button><strong>RechargeWallet</strong></button></a>&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
  <a href="myRequestController"><button><strong>MyRequest</strong></button></a>&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
  <a href="index.jsp"><button><strong>logOut</strong></button></a>
 </div>
</div>
<div class="loginContent">
    <c:if test="${notfound!=null }">
        <h2 class="elementToFadeInAndOut">${notfound}</h2>
        </c:if>
        <c:remove var="notfound" scope="session" />
        
    <c:if test="${insufficient!=null }">
        <h2 class="elementToFadeInAndOut">${insufficient}</h2>
        </c:if>
        <c:remove var="insufficient" scope="session" />
 
<c:set var="category" value="${param.category}" />
<c:if test="${param.category==null}">
<c:set var="category" value="${param.category}" />
</c:if>
 <form action="RasieRequestController">
  <h1>Raise The Request</h1>
  
  <label for="category">Category</label>
  <input type="text" name="category" id="category"  value ="${category }" required><br/><br/>


  <label for="location">Location</label> 
  <input type="text" name="location" id="location" pattern="[a-zA-Z ]+" title="invalid location" placeholder="Enter Location" required ><br/><br/>
  
  <label for="address">Address</label>
  <input type="text" name="address" id="address" placeholder="Enter Address" required>
  
  <div class="formBtn">
  <input type="submit" value="RaiseRequest">
  </div>
 
 </form>
</div>
</body>
</html>