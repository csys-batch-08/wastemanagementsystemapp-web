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

import com.cleaningmanagement.daoimpl.RequestDAOImpl;
import com.cleaningmanagement.model.Request;


@WebServlet("/viewRequestController")
public class viewRequestController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session=request.getSession();
		 RequestDAOImpl requestDaoImpl=new RequestDAOImpl();
	     List<Request> list=requestDaoImpl.showRequest();
	     session.setAttribute("list", list);
	     RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewRequest.jsp");
	     requestDispatcher.forward(request, response);
	}

	
}
