package com.cleaningmanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cleaningmanagement.daoimpl.EmployeeDaoImpl;
import com.cleaningmanagement.model.Employee;



@WebServlet("/listEmployeeController")
public class ListEmployeeController extends HttpServlet {
   private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		EmployeeDaoImpl employeeDAOImpl=new EmployeeDaoImpl();
        List<Employee> list=employeeDAOImpl.showEmployee();
        request.setAttribute("list", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listEmployee.jsp");
        requestDispatcher.forward(request, response);
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
	}

}
