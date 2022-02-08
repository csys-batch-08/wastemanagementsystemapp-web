package com.cleaningmanagement.controller;

import java.io.IOException;
import java.util.Date;

import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.RequestDaoImpl;

@WebServlet("/CalculateWeight")
public class CalculateWeightController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			HttpSession session = request.getSession();
			String location = request.getParameter("location");
			Date fromdate = sdf.parse(request.getParameter("fromdate"));
			Date todate = sdf.parse(request.getParameter("todate"));
			RequestDaoImpl requestDao = new RequestDaoImpl();
			int weight = requestDao.calculateWeight(location, fromdate, todate);
			if (weight != 0) {
				session.setAttribute("weight", weight);
				response.sendRedirect("calculateWeight.jsp");
            } else {
			   	session.setAttribute("invalid", weight);
				response.sendRedirect("calculateWeight.jsp");
            }
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
