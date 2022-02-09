package com.cleaningmanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.UserDaoImpl;
import com.cleaningmanagement.exception.FoundException;
import com.cleaningmanagement.model.User;


@WebServlet("/Deleteserv")
public class DeleteRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        HttpSession session = request.getSession();
		User user = (User) session.getAttribute("CurrentUser");
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String requestStatus = request.getParameter("status");
		try {
		if(!requestStatus.equalsIgnoreCase("cancel")){
        boolean b = userDaoImpl.cancelRequest(requestId);
        if (b) {
			userDaoImpl.refundWallet(user, amount);
			session.setAttribute("user", user.getWallet() + amount);
			List<Object> list = userDaoImpl.userBill(user);
			session.setAttribute("list", list);
			session.setAttribute("amount", amount);
			session.setAttribute("deleterequest", b);
			response.sendRedirect("deleteRequest.jsp");
		}
		}
		else
		{
			throw new FoundException();
		}
		}catch(FoundException e)
		{
			session.setAttribute("cancel", e.alreadyCancelled());
			response.sendRedirect("deleteRequest.jsp");
		}
	}

}
