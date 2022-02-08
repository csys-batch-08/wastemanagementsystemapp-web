package com.cleaningmanagement.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.AdminDaoImpl;
import com.cleaningmanagement.daoimpl.EmployeeDaoImpl;
import com.cleaningmanagement.daoimpl.UserDaoImpl;
import com.cleaningmanagement.exception.FoundException;
import com.cleaningmanagement.exception.UsernameAndPasswordInvalid;
import com.cleaningmanagement.model.Admin;
import com.cleaningmanagement.model.Employee;
import com.cleaningmanagement.model.User;

@WebServlet("/adminController")
public class AdminController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String emailId = request.getParameter("emailid");
		String password = request.getParameter("password");
		Admin admin = null;
		Employee employee = null;
		User user = null;
		AdminDaoImpl ad = new AdminDaoImpl();
		admin = ad.AdminDatabase(emailId, password);
		EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
		employee = employeeDao.validation(emailId, password);
		UserDaoImpl userDao = new UserDaoImpl();
		user = userDao.validateUser(emailId, password);
		 boolean isValid=false;
		try {
			if (admin != null) {
				response.sendRedirect("adminHome.jsp");
			} else if (employee != null) {
				if (employee.getStatus().equals("active")) {
					session.setAttribute("CurrentEmployee", employee);
					response.sendRedirect("employeeHome.jsp");
				} else {
					throw new UsernameAndPasswordInvalid();
				}
			} else if (user != null) {
				session.setAttribute("CurrentUser", user);
				response.sendRedirect("userHome.jsp");
			} else {
			     isValid=true;
				throw new UsernameAndPasswordInvalid();
			}
		} catch (UsernameAndPasswordInvalid e) {
			if(isValid) {
			try {
                session.setAttribute("invalid", e.getMessage());
				response.sendRedirect("index.jsp");
			} catch (Exception u) {
				u.printStackTrace();
			}
			}
			else {
				try {
				session.setAttribute("inactive", e.getMessage1() );
				response.sendRedirect("index.jsp");
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
		

	}

}
