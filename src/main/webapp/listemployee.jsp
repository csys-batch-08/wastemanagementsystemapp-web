<%@page import="com.cleaningmanagement.model.Employee"%>
<%@page import="java.util.List"%>
<%@page import="com.cleaningmanagement.daoimpl.EmployeeDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ListEmployee</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
}

body {
	background-color: lightyellow;
}

h1 {
	text-align: center;
	color: red;
}

table.center {
	margin-left: auto;
	margin-right: auto;
	width: 100%;
}

button {
	color: blue;
	border: none;
}

table tr:nth-child(even) {
	background: #0000001a;
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

.headerMenu a button {
	border: none;
	padding: 10px;
	background: black;
	color: white;
	margin: 0px 20px;
	border-radius: 3px;
}
</style>
</head>
<body>
	<div class="header">
		<div class="headerMenu">
			<a href="employee.jsp"><button><b>Add Employee</b></button></a> 
			<a href="category.jsp"><button><b>Add Category</b></button></a> 
			
			<a href="viewrequest.jsp"><button><b>updateRequestStatus</b></button></a>
			<a href="CalculateWeight.jsp"><button><b>CalculateWeight</b></button></a>
					
				
		</div>
	</div>
	<h1>Employee Details</h1>
	
	<table class="center">
		<tr>
			<th>EmailId</th>
			<th>Name</th>
			<th>Password</th>
			<th>Location</th>
			<th>Status</th>
			<th>UpdateStatus</th>
		</tr>
		<% 
		
  List<Employee> list=(List<Employee>)session.getAttribute("list");
  for (int j = 0; j < list.size(); j++) {
		Employee employee=list.get(j);
%>
		<tr>
			<td><%=employee.getEmpEmail() %></td>
			<td><%=employee.getEmpName() %></td>
			<td><%=employee.getEmpPassWord() %></td>
			<td><%=employee.getLocation() %></td>
			<td><%=employee.getStatus() %></td>
			<td><a
				href="ChangeStatus.jsp?email=<%=employee.getEmpEmail() %>"><button>UpdateStatus</button></a></td>
		</tr>
		<%}%>
	</table>

</body>
</html>