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


@WebServlet("/listEmployeesRequestController")
public class listEmployeesRequestController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession();
		 Employee employee=(Employee)session.getAttribute("CurrentEmployee");
		 System.out.println(employee);
	     EmployeeDAOImpl employeeDaoImpl=new EmployeeDAOImpl();
	     List<List<Object>> list=employeeDaoImpl.findEmployeeRequest(employee);
	     session.setAttribute("list", list);
	     RequestDispatcher requestDispatcher=request.getRequestDispatcher("showEmployeesRequest.jsp");
	     requestDispatcher.forward(request, response);
	   
	}

	
	

}
