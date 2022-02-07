package com.cleaningmanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.dao.AdminDao;
import com.cleaningmanagement.daoimpl.AdminDaoImpl;
import com.cleaningmanagement.daoimpl.CategoryDaoImpl;
import com.cleaningmanagement.model.CategoryDetails;


@WebServlet("/updateCategoryController")
public class UpdateCategoryController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int weight = Integer.parseInt(request.getParameter("weight"));
		String category = request.getParameter("category");
		int amount = Integer.parseInt(request.getParameter("amount"));
		AdminDao adminDao=new AdminDaoImpl();
		boolean b=adminDao.updateCategory(weight, amount, category);
		if(b)
		{   
			CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
			List<CategoryDetails> list = categoryDaoImpl.listdetails();
			request.setAttribute("list", list);
			response.sendRedirect("listCategoryController");
		}
	
		
	}

	
}
