package com.cleaningmanagement.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.RequestDaoImpl;
import com.cleaningmanagement.exception.FoundException;
import com.cleaningmanagement.model.Request;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String search=request.getParameter("search");
		RequestDaoImpl requestdao=new RequestDaoImpl();
		List<Request> requestList=requestdao.showRequest(search);
		try {
		if(!requestList.isEmpty()) {
			session.setAttribute("list", requestList);
			response.sendRedirect("searchLocation.jsp");
		}
		
		else
		{
			throw new FoundException();
		}
		}catch(FoundException e)
		{
			session.setAttribute("noresult", e.noResultFound());
			response.sendRedirect("viewRequest.jsp");
		}
		
		
	}

	

}
