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

import com.cleaningmanagement.daoimpl.UserDaoImpl;
import com.cleaningmanagement.model.User;


@WebServlet("/myRequestController")
public class MyRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("CurrentUser");
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		List<Object> list = userDaoImpl.userBill(user);
		session.setAttribute("list", list);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("deleteRequest.jsp");
		requestDispatcher.forward(request, response);
		
	}

}
