package com.cleaningmanagement.controller;

import java.io.IOException;
import java.sql.ResultSet;
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


@WebServlet("/userBillController")
public class UserBillController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 HttpSession session=request.getSession();
		 User user = (User) session.getAttribute("CurrentUser");
		 UserDaoImpl userDaoImpl = new UserDaoImpl();
		 List<List<Object>> listObject= userDaoImpl.showbill(user);
		 session.setAttribute("listObject",listObject);
		 RequestDispatcher requestDispatcher=request.getRequestDispatcher("showBill.jsp");
		 requestDispatcher.forward(request, response);
		
		 
	
}
}