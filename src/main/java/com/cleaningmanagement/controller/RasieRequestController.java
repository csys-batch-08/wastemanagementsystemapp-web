package com.cleaningmanagement.controller;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.CategoryDaoImpl;
import com.cleaningmanagement.daoimpl.EmployeeDaoImpl;
import com.cleaningmanagement.daoimpl.RequestDaoImpl;
import com.cleaningmanagement.daoimpl.UserDaoImpl;

import com.cleaningmanagement.exception.FoundException;
import com.cleaningmanagement.model.CategoryDetails;
import com.cleaningmanagement.model.Employee;
import com.cleaningmanagement.model.Request;
import com.cleaningmanagement.model.User;

@WebServlet("/RasieRequestController")
public class RasieRequestController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean exception=false;
		try {
			String category = request.getParameter("category");
			String location = request.getParameter("location");
			String address = request.getParameter("address");
			User user = (User) session.getAttribute("CurrentUser");
            Employee employee = null;
			Request req = null;
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			EmployeeDaoImpl empDao = new EmployeeDaoImpl();
			employee = empDao.findEmployee(location);
            
			if (employee != null && employee.getStatus().equals("active")) {
				Date sysDate = new Date();
				req = new Request(user, employee, category, location, sysDate, address);
			} else {
				exception=true;
				throw new FoundException();

			}
			List<User> userlist = userDaoImpl.showUser();
			for (User user1 : userlist) {
				if (user1.getUserEmail().equalsIgnoreCase(user.getUserEmail())) {
					user = user1;
				}
			}
			Double amount = user.getWallet();
			session.setAttribute("amount", amount);
			CategoryDaoImpl categorydao = new CategoryDaoImpl();
			CategoryDetails categoryDetails = categorydao.findAmount(category);
			
				if (user.getWallet() >= categoryDetails.getAmount()) {
					RequestDaoImpl rd = new RequestDaoImpl();
					boolean b = rd.insertRequestDetails(req);

					if (b) {
						boolean flag = userDaoImpl.updateWallet(user, categoryDetails.getAmount());
						{

							session.setAttribute("deduct", flag);
							response.sendRedirect("userBillController");
						}

					}
				} else {
					throw new FoundException();
				}
				
			} catch (FoundException e) {
				if(exception) {
					session.setAttribute("notfound", e.getMessage());
				    response.sendRedirect("raiseRequest.jsp");
			}
				else {
					session.setAttribute("insufficient", e.getMessage2());
					response.sendRedirect("raiseRequest.jsp");
				}
				
			
			
		}

	}

}
