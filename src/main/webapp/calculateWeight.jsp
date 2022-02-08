<%@page import="java.sql.ResultSet"%>
<%@page import="com.cleaningmanagement.daoimpl.RequestDaoImpl"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>WeightCalculation</title>
<link rel="stylesheet" type="text/css" href="assets/css/calculateweight.css">
</head>
<body>
<div class="header">
 <div class="headerMenu">
      <a href="employee.jsp"><button><strong>AddEmployee</strong></button></a>
	  <a href="category.jsp"><button><strong>Category</strong></button></a>
	  <a href="listEmployeeController"><button><strong>UpdateEmployeeStatus</strong></button></a>
	  <a href="viewRequestController"><button><strong>UpdateRequestStatus</strong></button></a>
	  <a href="listCategoryController"><button><strong>UpdateCatgeory</strong></button></a>
	  <a href="index.jsp"><button><strong>logOut</strong></button></a>
 </div>
</div>
 
 <c:if test="${weight!=null }">
 <h1 class="elementToFadeInAndOut">Weight:${weight}Kg</h1>
 </c:if>
 <c:remove var="weight" scope="session" />
 
 <c:if test="${invalid!=null }">
 <h1 class="elementToFadeInAndOut">We can't Calculate Weight Since the status is in pending</h1>
 </c:if>
 <c:remove var="invalid" scope="session" />
   
<div class="loginContent">
  <form action="CalculateWeight" method="post">
  
  <h1>CalculateWeight</h1>
  
  <label for="fromdate"><strong>FromDate</strong></label>
  <input type="date" name="fromdate" id="fromdate" required ><br><br>
  
  <label for="todate"><strong>ToDate</strong></label>
  <input type="date" name="todate" id="todate" required ><br><br>
  
  <label for="location"><strong>Location</strong></label>
  <input type="text" name="location" id="location" pattern="[a-zA-Z ]+" title="invalid location" required ><br><br>
  
  <div class="formBtn">
  <input type="submit" value="Calculate">
  </div>
 </form>
 </div>
 <script>
  var today = new Date();
  var dd = today.getDate()-7;
  var mm = today.getMonth() +1;
  var yyyy = today.getFullYear();
  if(dd<10){
    dd='0'+dd
  } 
  if(mm<10){
    mm='0'+mm
  } 

  today = yyyy+'-'+mm+'-'+dd;
  
  document.getElementById("fromdate").setAttribute("min", today);
  document.getElementById("todate").setAttribute("min", today);
   today = new Date();
  dd = today.getDate();
  mm = today.getMonth() +1;
  yyyy = today.getFullYear();
   if(dd<10){
	    dd='0'+dd
	} 
   if(mm<10){
	    mm='0'+mm
	} 

  today = yyyy+'-'+mm+'-'+dd;
  document.getElementById("fromdate").setAttribute("max", today);
  document.getElementById("todate").setAttribute("max", today); 
  </script>
</body>
</html>