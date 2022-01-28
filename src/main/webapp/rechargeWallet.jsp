<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WalletRecharge</title>
<style>
body{
background-image: url('images/background1.jpg');
    margin: 0px;
}
.loginContent {
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
}
.loginContent form {
    background: white;
    padding: 15px;
    text-align: left;
}
.loginContent form label {
    font-weight: bold;
}
.loginContent form h1 {
    margin: 0px 0px 12px;
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
	position:absolute;
    color: red;
    font-weight: bold;
    top:150px;
    left:515px;
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
</style>
</head>
<body>
<div class="header">
   <div class="headerMenu">
  <a href="viewCategoryController"><button><b>AvailableCategories</b></button></a>&nbsp; &nbsp; &nbsp; 
  <a href="myRequestController"><button><b>MyRequest</b></button></a>&nbsp; &nbsp; &nbsp;
 </div>
</div>
<div class="loginContent">
<c:if test="${recharge!=null }" >
   <c:if test="${amount!=null }" >
     <h1 class="text-center">${amount}Rs&nbsp;Recharge Successfully</h1>
   </c:if>
</c:if>
 <c:remove var="recharge" scope="session" />
<div class="loginContent">
<form action="RechargeWalletController" >
<h1>RechargeWallet</h1>
 
 <label for="amount"><b>Enter Amount</b></label>
 <input type="text" name="amount" id="amount" pattern="[0-9]+" title="amount should be in positive" autofocus required>
 <div class="formBtn">
 <input type="submit" value="Recharge">
</div>
</form>
</div>
</div>
</body>
</html>