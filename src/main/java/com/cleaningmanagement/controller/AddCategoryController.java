package com.cleaningmanagement.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cleaningmanagement.daoimpl.CategoryDaoImpl;
import com.cleaningmanagement.model.CategoryDetails;


@WebServlet("/CategoryController")
public class AddCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	    int weight = Integer.parseInt(request.getParameter("weight"));
		String category = request.getParameter("category");
		int amount = Integer.parseInt(request.getParameter("amount"));
		CategoryDetails categoryDetails = new CategoryDetails(weight, category, amount);
		CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
		int k = categoryDaoImpl.insertCategoryDetails(categoryDetails);
		if (k > 0) {
			List<CategoryDetails> list = categoryDaoImpl.listdetails();
			request.setAttribute("list", list);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("addedListCategory.jsp");
			requestDispatcher.forward(request, response);
		} else {
			response.sendRedirect("category.jsp");
		}

	}

	

}
