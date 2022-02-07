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

import com.cleaningmanagement.daoimpl.EmployeeDaoImpl;
import com.cleaningmanagement.model.Employee;


@WebServlet("/listEmployeesRequestController")
public class ListEmployeesRequestController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession();
		 Employee employee=(Employee)session.getAttribute("CurrentEmployee");
		 EmployeeDaoImpl employeeDaoImpl=new EmployeeDaoImpl();
	     List<List<Object>> list=employeeDaoImpl.findEmployeeRequest(employee);
	     request.setAttribute("list", list);
	     RequestDispatcher requestDispatcher=request.getRequestDispatcher("showEmployeesRequest.jsp");
	     requestDispatcher.forward(request, response);
	     
	   
	}

	
	

}
