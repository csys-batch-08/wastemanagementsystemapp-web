package com.cleaningmanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.RequestDAOImpl;
import com.cleaningmanagement.daoimpl.UserDAOImpl;
import com.cleaningmanagement.model.User;


@WebServlet("/Deleteserv")
public class DeleteRequestController extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("CurrentUser");
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		int userId = userDaoImpl.findUser(user.getUserEmail());
		String category = request.getParameter("category");
		String location = request.getParameter("location");
		int amount = Integer.parseInt(request.getParameter("amount"));
        RequestDAOImpl requestDaoImpl = new RequestDAOImpl();
		int RequestId = requestDaoImpl.findRequestID(userId, category, location);
		boolean b = requestDaoImpl.deleteRequest(RequestId);
		if (b) {
			userDaoImpl.refundWallet(user, amount);
			session.setAttribute("user", user.getWallet() + amount);
			List<List<Object>> list = userDaoImpl.userBill(user);
			session.setAttribute("list", list);
			session.setAttribute("deleterequest", b);
			response.sendRedirect("deleteRequest.jsp");
		}

	}

}
