package com.cleaningmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/getIdController")
public class getIdController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int requestId=Integer.parseInt(request.getParameter("requestId"));
		session.setAttribute("requestId", requestId);
		response.sendRedirect("changeEmployeeStatus.jsp");
		
	}

	
	
}
