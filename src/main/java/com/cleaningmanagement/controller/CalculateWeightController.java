package com.cleaningmanagement.controller;

import java.io.IOException;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.RequestDAOImpl;


@WebServlet("/CalculateWeight")
public class CalculateWeightController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			HttpSession session = request.getSession();
			String location = request.getParameter("location");
			Date fromdate = sdf.parse(request.getParameter("fromdate"));
			Date todate = sdf.parse(request.getParameter("todate"));
			RequestDAOImpl requestDao=new RequestDAOImpl();
			int weight=requestDao.CalculateAmount(location, fromdate, todate);
			if(weight!=0)
			{   
				 session.setAttribute("weight", weight);
				 RequestDispatcher requestDispatcher = request.getRequestDispatcher("calculateWeight.jsp");
				 requestDispatcher.forward(request, response);
			}
			else
			{
				
			}
			
			

		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}

	

}
