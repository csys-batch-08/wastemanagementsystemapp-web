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

@WebServlet("/EmployeeController")
public class AddEmployeeController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String emailId = request.getParameter("emailid");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String location = request.getParameter("location");
		Employee emp = new Employee(emailId, name, password, location);
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		List<Employee> listEmployee = empDao.showEmployee();
		boolean emailUsed = false;
		try {
			if (!listEmployee.isEmpty()) {
				for (int i = 0; i < listEmployee.size(); i++) {
					if (listEmployee.get(i).getEmpEmail().equals(emailId)) {
						emailUsed = true;
						throw new FoundException();
					} else if (listEmployee.get(i).getLocation().equalsIgnoreCase(location)) {
						throw new FoundException();
					}

				}
			}
			boolean b = empDao.insertEmpDatabase(emp);
			if (b) {
				List<Employee> viewEmployee = empDao.showEmployee(emp);
				request.setAttribute("list", viewEmployee);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("addedListEmployee.jsp");
				requestDispatcher.forward(request, response);

			}
		} catch (FoundException e) {
			if (emailUsed) {
				session.setAttribute("alreadyused", e.getMessage3());
				response.sendRedirect("employee.jsp");
			} else {
				session.setAttribute("locationused", e.locationUsed());
				response.sendRedirect("employee.jsp");
			}
		}

	}

}
