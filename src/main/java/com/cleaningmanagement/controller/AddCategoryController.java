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

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class AddCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCategoryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession();
		int weight = Integer.parseInt(request.getParameter("weight"));
		String category = request.getParameter("category");
		int amount = Integer.parseInt(request.getParameter("amount"));
		CategoryDetails categoryDetails = new CategoryDetails(weight, category, amount);
		CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
		int k = categoryDAOImpl.insertCategoryDetails(categoryDetails);
		if (k > 0) {
			List<CategoryDetails> list = categoryDAOImpl.listdetails();
			session.setAttribute("list", list);
			response.sendRedirect("AddedListCategory.jsp");
		} else {
			response.sendRedirect("category.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
