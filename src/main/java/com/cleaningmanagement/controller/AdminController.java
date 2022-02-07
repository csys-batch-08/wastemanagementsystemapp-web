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

		String emailId = request.getParameter("emailid");
		String password = request.getParameter("password");
		Admin admin = null;
		Employee employee = null;
		User user = null;
		AdminDaoImpl ad = new AdminDaoImpl();
		admin = ad.AdminDatabase(emailId, password);
		boolean isValid = false;
		try {
			if (admin != null) {
				isValid = true;
				response.sendRedirect("adminHome.jsp");
			} else {
				EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
				employee = employeeDao.validation(emailId, password);
			}
			if (employee != null) {
				isValid = true;
				if (employee.getStatus().equals("active")) {
					HttpSession session = request.getSession();
					session.setAttribute("CurrentEmployee", employee);
					response.sendRedirect("employeeHome.jsp");
				} else {
					throw new FoundException();
				}
			}

			else {
				UserDaoImpl userDao = new UserDaoImpl();
				user = userDao.validateUser(emailId, password);
				if (user != null) {
					isValid = true;
					HttpSession session = request.getSession();
					session.setAttribute("CurrentUser", user);
					response.sendRedirect("userHome.jsp");
				}
			}

			if (!isValid) {
				throw new UsernameAndPasswordInvalid();
			}

		} catch (UsernameAndPasswordInvalid e) {
			try {
				HttpSession session = request.getSession();
				session.setAttribute("invalid", e.getMessage());
				response.sendRedirect("index.jsp");
			} catch (Exception u) {
				u.printStackTrace();
			}
		} catch (FoundException e) {
			try {
				HttpSession session = request.getSession();
				session.setAttribute("inactive", e.getMessage4());
				response.sendRedirect("index.jsp");
			} catch (Exception l) {
				l.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
