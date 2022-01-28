package com.cleaningmanagement.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.AdminDAOImpl;
import com.cleaningmanagement.daoimpl.EmployeeDAOImpl;
import com.cleaningmanagement.daoimpl.UserDAOImpl;
import com.cleaningmanagement.exception.FoundException;
import com.cleaningmanagement.exception.UsernameAndPasswordInvalid;
import com.cleaningmanagement.model.Admin;
import com.cleaningmanagement.model.Employee;
import com.cleaningmanagement.model.User;


@WebServlet("/adminController")
public class AdminController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String emailId = request.getParameter("emailid");
		String password = request.getParameter("password");
		Admin admin = null;
		Employee employee = null;
		User user = null;
		AdminDAOImpl ad = new AdminDAOImpl();
		admin = ad.AdminDatabase(emailId, password);
		EmployeeDAOImpl ed = new EmployeeDAOImpl();
		employee = ed.validation(emailId, password);
		UserDAOImpl ud = new UserDAOImpl();
		user = ud.validateUser(emailId, password);
		
		
		try {
			try {
				if (admin != null) {
					response.sendRedirect("adminHome.jsp");
				}

				else if (employee != null) {

					if (employee.getStatus().equals("active")) {
						HttpSession session = request.getSession();
						session.setAttribute("CurrentEmployee", employee);
						response.sendRedirect("employeeHome.jsp");
					} else {
						throw new FoundException();
					}
				}

				else if (user != null) {
					HttpSession session = request.getSession();
					session.setAttribute("CurrentUser", user);
					response.sendRedirect("userHome.jsp");
				}

				else {
					throw new UsernameAndPasswordInvalid();
				}

			} catch (FoundException e) {
				HttpSession session = request.getSession();
				session.setAttribute("inactive", e.getMessage4());
				response.sendRedirect("index.jsp");
			}
		} catch (UsernameAndPasswordInvalid e) {
			HttpSession session = request.getSession();
			session.setAttribute("invalid", e.getMessage());
			response.sendRedirect("index.jsp");
		}

	}

	

}
