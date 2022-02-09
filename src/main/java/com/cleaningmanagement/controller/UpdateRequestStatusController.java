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

import com.cleaningmanagement.daoimpl.AdminDaoImpl;
import com.cleaningmanagement.daoimpl.RequestDaoImpl;
import com.cleaningmanagement.exception.FoundException;
import com.cleaningmanagement.model.Request;


@WebServlet("/UpdateRequestStatus")
public class UpdateRequestStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		try {
		String employeeStatus=(String)session.getAttribute("empstatus");
		if(employeeStatus.equalsIgnoreCase("done") ) {
		String status=request.getParameter("requeststatus");
		int requestId=(int) session.getAttribute("requestId");
		RequestDaoImpl requestDaoImpl=new RequestDaoImpl();  
		AdminDaoImpl admindao=new AdminDaoImpl();
		int n=admindao.updateRequest(status, requestId);
		if(n>0)
		{   
			List<Request> list=requestDaoImpl.showRequest();
		    request.setAttribute("list", list);
		    RequestDispatcher requestDispatcher=request.getRequestDispatcher("viewRequest.jsp");
		    requestDispatcher.forward(request, response);
			
		}
		}
		else
		{
			throw new FoundException();
		}
		}catch(FoundException e)
		{
		    session.setAttribute("status", e.getMessage5());
			response.sendRedirect("updateRequestStatus.jsp");
		}
		
	}

	

}
