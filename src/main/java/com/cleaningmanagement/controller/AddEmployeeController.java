package com.cleaningmanagement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.EmployeeDAOImpl;
import com.cleaningmanagement.model.Employee;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class AddEmployeeController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String emailId = request.getParameter("emailid");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String location = request.getParameter("location");
		Employee emp = new Employee(emailId, name, password, location);
		EmployeeDAOImpl empdao = new EmployeeDAOImpl();
		boolean b = empdao.insertEmpDatabase(emp);
		if (b) {
			EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
			List<Employee> list = employeeDAOImpl.showEmployee();
			session.setAttribute("list", list);
			response.sendRedirect("addedListEmployee.jsp");
		} else {
			response.sendRedirect("employee.jsp");
		}
	}

	

}
