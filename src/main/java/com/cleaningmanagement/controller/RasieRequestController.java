package com.cleaningmanagement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.CategoryDAOImpl;
import com.cleaningmanagement.daoimpl.EmployeeDAOImpl;
import com.cleaningmanagement.daoimpl.RequestDAOImpl;
import com.cleaningmanagement.daoimpl.UserDAOImpl;

import com.cleaningmanagement.exception.FoundException;
import com.cleaningmanagement.model.CategoryDetails;
import com.cleaningmanagement.model.Employee;
import com.cleaningmanagement.model.Request;
import com.cleaningmanagement.model.User;




@WebServlet("/RasieRequestController")
public class RasieRequestController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		try
		{
		
		String category=request.getParameter("category");
		String location=request.getParameter("location");
		User user=(User) session.getAttribute("CurrentUser");
		Employee employee=null;
		Request req=null;
		UserDAOImpl userDaoImpl=new UserDAOImpl();
		EmployeeDAOImpl empDao = new EmployeeDAOImpl();
	    employee = empDao.findEmployee(location);
       
	    if(employee!=null && employee.getStatus().equals("active"))
	    {
	    Date sysDate=new Date(); 
		req = new Request(user, employee, category, location,sysDate);
	    }
	    else
	    {
	    	throw new FoundException();
	    	
	    }
	    List<User> userlist=userDaoImpl.showUser();
		for(User user1:userlist)
		{
			if(user1.getUserEmail().equalsIgnoreCase(user.getUserEmail()))
			{
				user=user1;
			}
		}
		
		Double amount=user.getWallet();
		session.setAttribute("amount",amount);
	    CategoryDAOImpl categorydao=new CategoryDAOImpl();
		CategoryDetails categoryDetails=categorydao.findAmount(category);
		try {
		if(user.getWallet()>=categoryDetails.getAmount())
		{
		RequestDAOImpl rd = new RequestDAOImpl();
		boolean b = rd.insertRequestDetails(req);
		
		if(b)
		{   
		    boolean flag = userDaoImpl.updateWallet(user,categoryDetails.getAmount() );
		    {   
		    	session.setAttribute("deduct", flag);
		    	response.sendRedirect("userBillController");
		    }
			
			
		}
		}
		else
		{
			throw new FoundException();
		}
		}catch(FoundException e) {
			session.setAttribute("insufficient", e.getMessage2());
			session.setAttribute("category", request.getParameter("category"));
			response.sendRedirect("raiseRequest.jsp");
			
		}
		}catch(FoundException e)
		{   
			session.setAttribute("notfound", e.getMessage());
			session.setAttribute("category", request.getParameter("category"));
			response.sendRedirect("raiseRequest.jsp");
			
			
		}
		
		
	}

	

}
