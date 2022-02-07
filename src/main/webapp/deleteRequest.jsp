<%@page import="java.util.ArrayList"%>
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
<title>DeleteRequest</title>
<link rel="stylesheet" type="text/css" href="assets/css/deleterequest.css">
</head>
<body>
<div class="header">
   <div class="headerMenu">
  <a href="viewCategoryController"><button><strong>AvailableCategories</strong></button></a>&nbsp; &nbsp; &nbsp; 
  <a href="rechargeWallet.jsp"><button><strong>RechargeWallet</strong></button></a>&nbsp; &nbsp; &nbsp;
  <a href="myRequestController"><button><strong>MyRequest</strong></button></a>&nbsp; &nbsp; &nbsp;
  <a href="index.jsp"><button><strong>logOut</strong></button></a>
 </div>
</div>

<c:if test="${deleterequest!=null }">
   <c:if test="${amount!=null }">
   <h1 class="text-center elementToFadeInAndOut">Cancelled Successfully!</h1>
   <h2 class="elementToFadeInAndOut">Amount&nbsp;${amount}Rs Refunded to your Wallet</h2>
   </c:if>
</c:if>
<c:remove var="deleterequest" scope="session" />

<c:if test="${cancel!=null }">
    <h1 class="text-center elementToFadeInAndOut">${cancel}</h1>
</c:if>
<c:remove var="cancel" scope="session" />

<div class="align">
<c:if test="${user!=null }">
<h3 class="elementToFadeInAndOut">AvailableBalance:&nbsp;${user}</h3>
 <c:remove var="user" scope="session" />
</c:if>
</div>
<c:set var="count" value="1" />
 <table  aria-describedby="My Request" class="center">
 
		<tr>
			<th>SerialNumber</th>
			<th>Category</th>
			<th>Weight</th>
			<th>Amount</th>
			<th>EmployeeName</th>
			<th>RequestDate</th>
			<th>Location</th>
			<th>Address</th>
			<th>RequestStatus</th>
			<th>cancel</th>
		</tr>
<c:forEach items="${sessionScope.list}" var="request">		   
        <tr>
			<td>${count}</td>
		    <td>${ request.get(1)}</td>
			<td>${ request.get(2)}</td>
			<td>${ request.get(3)}</td>
			<td>${ request.get(4)}</td>
			<fmt:parseDate value="${request.get(5)}" pattern="yyyy-MM-dd" var="requestDate" type="date"/>	
		    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${requestDate}"/></td>
			<td>${ request.get(6)}</td>
			<td>${ request.get(7)}</td>
			<td>${ request.get(8)}</td>
			<td><a href="confirmCancel.jsp?requestId=${ request.get(0)}&category=${ request.get(1)}&weight=${ request.get(2)}&amount=${ request.get(3)}&location=${ request.get(6)}&status=${request.get(8)}">Cancel</a></td>
	
		</tr>
		<c:set var="count" value="${count+1}" />
</c:forEach>	
</table>
</body>
</html>