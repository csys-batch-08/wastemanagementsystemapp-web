<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.cleaningmanagement.daoimpl.UserDAOImpl"%>
<%@page import="com.cleaningmanagement.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DeleteRequest</title>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
  
}
th, td {
  padding: 15px;
  
}
body{
    
    background-color:lightyellow;
}
h1{
 text-align:center;
 color:red;
 
}
table {
  margin-left: auto; 
  margin-right: auto;
  width:100%;
  margin-top:40px
}
table tr:nth-child(even) {
    background: #0000001a;
}
.headerMenu a button {
    border: none;
    padding: 10px;
    background: black;
    color: white;
    margin: 0px 20px;
    border-radius: 3px;
}
.headerMenu a button {
    border: none;
    padding: 10px;
    background: black;
    color: white;
}

.headerMenu {
    display: flex;
    justify-content: space-around;
    background: aliceblue;
    padding: 15px;
}
.align
{
position: absolute;
top: 10px;
right: 10px;
}
</style>
</head>
<body>
<div class="header">
   <div class="headerMenu">
  <a href="viewCategoryController"><button><b>AvailableCategories</b></button></a>&nbsp; &nbsp; &nbsp; 
  <a href="rechargeWallet.jsp"><button><b>RechargeWallet</b></button></a>&nbsp; &nbsp; &nbsp;
 
 </div>
</div>

<c:if test="${deleterequest!=null }">
   <h1>Successfully Deleted!!</h1>
   <h2>Amount Refunded to your Wallet</h2>
</c:if>
 <c:remove var="deleterequest" scope="session" />

<div class="align">
<c:if test="${user!=null }">
<h3>AvailableBalance:&nbsp;${user}</h3>
</c:if>
</div>
 <table class="center">
		<tr>
			<th>RequestID</th>
			<th>UserName</th>
			<th>Category</th>
			<th>Weight</th>
			<th>Amount</th>
			<th>EmployeeName</th>
			<th>RequestDate</th>
			<th>Location</th>
			<th>Delete</th>
		</tr>
<c:forEach items="${sessionScope.list}" var="request">		   
        <tr>
			<td>${ request.get(0)}</td>
			<td>${ request.get(1)}</td>
			<td>${ request.get(2)}</td>
			<td>${ request.get(3)}</td>
			<td>${ request.get(4)}</td>
			<td>${ request.get(5)}</td>
			<td>${ request.get(6)}</td>
			<td>${ request.get(7)}</td>
			<td><a href="Deleteserv?category=${ request.get(2)}&location=${ request.get(7)}&amount=${ request.get(4)}"><button>Delete</button></a></td>
		</tr>
</c:forEach>	
</table>
</body>
</html>