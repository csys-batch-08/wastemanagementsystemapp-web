package com.cleaningmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/getIdController")
public class GetIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int requestId=Integer.parseInt(request.getParameter("requestId"));
		String requestStatus=request.getParameter("requestStatus");
		session.setAttribute("requestId", requestId);
		session.setAttribute("requestStatus", requestStatus);
		response.sendRedirect("changeEmployeeStatus.jsp");
	}

	
	
}
