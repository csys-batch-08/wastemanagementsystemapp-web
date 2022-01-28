package com.cleaningmanagement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.CategoryDAOImpl;
import com.cleaningmanagement.model.CategoryDetails;


@WebServlet("/CategoryController")
public class AddCategoryController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		int weight = Integer.parseInt(request.getParameter("weight"));
		String category = request.getParameter("category");
		int amount = Integer.parseInt(request.getParameter("amount"));
		CategoryDetails categoryDetails = new CategoryDetails(weight, category, amount);
		CategoryDAOImpl categoryDaoImpl = new CategoryDAOImpl();
		int k = categoryDaoImpl.insertCategoryDetails(categoryDetails);
		if (k > 0) {
			List<CategoryDetails> list = categoryDaoImpl.listdetails();
			session.setAttribute("list", list);
			response.sendRedirect("addedListCategory.jsp");
		} else {
			response.sendRedirect("category.jsp");
		}

	}

	

}
