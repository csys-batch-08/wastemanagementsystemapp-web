package com.cleaningmanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.EmployeeDAOImpl;
import com.cleaningmanagement.model.Employee;



@WebServlet("/listEmployeeController")
public class listEmployeeController extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		EmployeeDAOImpl employeeDAOImpl=new EmployeeDAOImpl();
        List<Employee> list=employeeDAOImpl.showEmployee();
        session.setAttribute("list", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listEmployee.jsp");
        requestDispatcher.forward(request, response);
		
	}

}
