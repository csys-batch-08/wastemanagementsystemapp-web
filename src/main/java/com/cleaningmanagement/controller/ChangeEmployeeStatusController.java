package com.cleaningmanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.EmployeeDAOImpl;
import com.cleaningmanagement.model.Employee;


@WebServlet("/EmpstatusChange")
public class ChangeEmployeeStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String status=request.getParameter("status");
		int requestId=(int)session.getAttribute("requestId");
		EmployeeDAOImpl employeeDaoImpl=new EmployeeDAOImpl();
		boolean b=employeeDaoImpl.updateEmployeeStatus(status, requestId);
		if(b)
		{   Employee employee=(Employee)session.getAttribute("CurrentEmployee");
	        List<List<Object>> list=employeeDaoImpl.findEmployeeRequest(employee);
	        session.setAttribute("list", list);
			response.sendRedirect("showEmployeesRequest.jsp");
		}
		
		
	}

	

}
