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
import com.cleaningmanagement.daoimpl.UserDaoImpl;
import com.cleaningmanagement.model.CategoryDetails;
import com.cleaningmanagement.model.User;

@WebServlet("/viewCategoryController")
public class ViewCategoryController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("CurrentUser");
		CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
		List<CategoryDetails> list = categoryDaoImpl.listdetails();
		Collections.sort(list);
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		List<User> userlist = userDaoImpl.showUser();

		for (User user1 : userlist) {
			if (user1.getUserEmail().equalsIgnoreCase(user.getUserEmail())) {
				user = user1;
			}
		}

		request.setAttribute("list", list);
		session.setAttribute("wallet", user.getWallet());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("listCategory.jsp");
		requestDispatcher.forward(request, response);

	}

}
