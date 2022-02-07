<%@page import="com.cleaningmanagement.daoimpl.UserDaoImpl"%>
<%@page import="com.cleaningmanagement.model.User"%>
<%@page import="com.cleaningmanagement.model.CategoryDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.cleaningmanagement.daoimpl.CategoryDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>CategoryDetails</title>
<link rel="stylesheet" type="text/css" href="assets/css/listcategory.css">
</head>
<body>
 <div class="header">
   <div class="headerMenu">
  <a href="viewCategoryController"><button><strong>AvailableCategories</strong></button></a>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <a href="rechargeWallet.jsp"><button><strong>RechargeWallet</strong></button></a>&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
  <a href="myRequestController"><button><strong>MyRequest</strong></button></a>&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
  <a href="index.jsp"><button><strong>logOut</strong></button></a>
 </div>
</div>
<h1>Category Details</h1>
<c:if test="${wallet!=null }">
   <h3>AvailableBalance:&nbsp;${wallet}</h3>
</c:if>
<c:set var="count" value="1" />
<table aria-describedby="Category List" class="center">

 <tr>
   <th>SerialNumber</th>
   <th>Weight</th>
   <th>Category</th>
   <th>Amount</th>
   <th>RaiseRequest</th>
 </tr>
 <c:forEach items="${requestScope.list}" var="category">
 <tr>
   <td>${count}</td>
   <td>${ category.getWeightInKg()}</td>
   <td>${ category.getCategory() }</td>
   <td>${ category.getAmount() }</td>
   <td><a href="raiseRequest.jsp?category=${ category.getCategory() }"><button>RaiseRequest</button></a></td>
 </tr>
 <c:set var="count" value="${count+1}" />
</c:forEach>
</table>

</body>
</html>