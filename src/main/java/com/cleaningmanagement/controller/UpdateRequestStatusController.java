package com.cleaningmanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.AdminDAOImpl;
import com.cleaningmanagement.daoimpl.RequestDAOImpl;
import com.cleaningmanagement.exception.FoundException;
import com.cleaningmanagement.model.Request;


@WebServlet("/UpdateRequestStatus")
public class UpdateRequestStatusController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		try {
		String EmployeeStatus=(String)session.getAttribute("empstatus");
		if(EmployeeStatus.equalsIgnoreCase("done") ) {
		String status=request.getParameter("requeststatus");
		int requestId=(int) session.getAttribute("requestId");
		RequestDAOImpl requestDaoImpl=new RequestDAOImpl();  
		AdminDAOImpl admindao=new AdminDAOImpl();
		int n=admindao.updateRequest(status, requestId);
		if(n>0)
		{   
			List<Request> list=requestDaoImpl.showRequest();
		    session.setAttribute("list", list);
			response.sendRedirect("viewRequest.jsp");
		}
		}
		else
		{
			throw new FoundException();
		}
		}catch(FoundException e)
		{
		    session.setAttribute("status", e.getMessage5());
			response.sendRedirect("viewRequest.jsp");
		}
		
	}

	

}
