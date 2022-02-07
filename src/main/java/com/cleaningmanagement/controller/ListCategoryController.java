package com.cleaningmanagement.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.CategoryDaoImpl;
import com.cleaningmanagement.model.CategoryDetails;

@WebServlet("/listCategoryController")
public class ListCategoryController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
		List<CategoryDetails> list = categoryDaoImpl.listdetails();
		Collections.sort(list);
		request.setAttribute("list", list);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewCatgeory.jsp");
		requestDispatcher.forward(request, response);
	}

}
