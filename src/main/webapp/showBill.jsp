<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.cleaningmanagement.daoimpl.UserDaoImpl"%>
<%@page import="com.cleaningmanagement.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>billPage</title>
<link rel="stylesheet" type="text/css" href="assets/css/showbill.css">
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
<c:if test="${deduct!=null }">
<h1 class="elementToFadeInAndOut">Amount is Deducted successfully</h1>
</c:if>
    
	<h1>Bill</h1>

	<table aria-describedby="user bill">
	
		<tr>
            <th>UserName</th>
			<th>Location</th>
			<th>Address</th>
			<th>Category</th>
			<th>Weight</th>
			<th>RequestDate</th>
			<th>Amount</th>
			<th>AvailableBalance</th>
		</tr>
<c:forEach items="${sessionScope.listObject}" var="list">
		<tr>
		    <td>${ list.get(0) }</td>
		    <td>${ list.get(1) }</td>
		    <td>${ list.get(2) }</td>
		    <td>${ list.get(3) }</td>
		    <td>${ list.get(4) }</td>
		    <fmt:parseDate value="${ list.get(5) }" pattern="yyyy-MM-dd" var="requestDate" type="date"/>	
		    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${requestDate}"/></td>
		    <td>${ list.get(6) }</td>
		    <c:if test="${amount!=null }">
		    <c:set var="total" value="${amount - list.get(6)}" />
			<td>${total }</td>
			</c:if>
        </tr>
		
</c:forEach>
	</table>
</body>
</html>