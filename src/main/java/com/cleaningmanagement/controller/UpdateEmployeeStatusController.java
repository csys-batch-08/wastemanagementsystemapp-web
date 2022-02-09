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


@WebServlet("/UpdateEmployeeMessage")
public class UpdateEmployeeStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		EmployeeDaoImpl employeeDaoImpl=new EmployeeDaoImpl();
        String status = request.getParameter("status");
	    String emailId=(String)session.getAttribute("email");
		boolean b = employeeDaoImpl.updatestatus(status, emailId);
		try {
		if (b) {
			List<Employee> list=employeeDaoImpl.showEmployee();
	        request.setAttribute("list", list);
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listEmployee.jsp");
			requestDispatcher.forward(request, response);
        }
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	
	

}
