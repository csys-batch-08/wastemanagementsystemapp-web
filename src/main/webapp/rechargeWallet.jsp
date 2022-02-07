<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>WalletRecharge</title>
<link rel="stylesheet" type="text/css" href="assets/css/recharge.css">
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
<div class="loginContent">
<c:if test="${recharge!=null }" >
   <c:if test="${amount!=null }" >
     <h1 class="text-center elementToFadeInAndOut">${amount}Rs&nbsp;Recharge Successfully</h1>
   </c:if>
</c:if>
 <c:remove var="recharge" scope="session" />
<div class="loginContent">
<form action="RechargeWalletController" >
<h1>RechargeWallet</h1>
 
 <label for="amount"><strong>Enter Amount</strong></label>
 <input type="text" name="amount" id="amount" pattern="[0-9]+" title="invalid input" placeholder="Amount in Rs" autofocus required>
 <div class="formBtn">
 <input type="submit" value="Recharge">
</div>
</form>
</div>
</div>
</body>
</html>