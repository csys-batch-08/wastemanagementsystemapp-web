package com.cleaningmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/getRequestIdController")
public class GetRequestIdController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		int requestId=Integer.parseInt(request.getParameter("Rid"));
		String employeeStatus=request.getParameter("empstatus");
		session.setAttribute("requestId", requestId);
		session.setAttribute("empstatus", employeeStatus);
		response.sendRedirect("updateRequestStatus.jsp");
		
	}

	

}
