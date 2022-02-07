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

import com.cleaningmanagement.daoimpl.RequestDaoImpl;
import com.cleaningmanagement.model.Request;


@WebServlet("/viewRequestController")
public class ViewRequestController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 RequestDaoImpl requestDaoImpl=new RequestDaoImpl();
	     List<Request> list=requestDaoImpl.showRequest();
	     request.setAttribute("list", list);
	     RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewRequest.jsp");
	     requestDispatcher.forward(request, response);
	     
	}

	
}
