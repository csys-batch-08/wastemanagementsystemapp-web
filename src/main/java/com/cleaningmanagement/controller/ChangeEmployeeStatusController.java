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
import com.cleaningmanagement.exception.FoundException;
import com.cleaningmanagement.model.Employee;

@WebServlet("/EmpstatusChange")
public class ChangeEmployeeStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String status = request.getParameter("status");
		int requestId = (int) session.getAttribute("requestId");
		String requestStatus = (String) session.getAttribute("requestStatus");
		try {
			if (requestStatus.equalsIgnoreCase("pending")) {
				EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
				boolean b = employeeDaoImpl.updateEmployeeStatus(status, requestId);
				if (b) {
					Employee employee = (Employee) session.getAttribute("CurrentEmployee");
					List<List<Object>> list = employeeDaoImpl.findEmployeeRequest(employee);
					request.setAttribute("list", list);
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("showEmployeesRequest.jsp");
					requestDispatcher.forward(request, response);
				}
			} else {
				throw new FoundException();
			}
		} catch (FoundException e) {
			try {
			session.setAttribute("cancel", e.statusChange());
			response.sendRedirect("changeEmployeeStatus.jsp");
			}catch(Exception l)
			{
				l.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
