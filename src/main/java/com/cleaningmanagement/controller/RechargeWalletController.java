package com.cleaningmanagement.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cleaningmanagement.daoimpl.UserDAOImpl;
import com.cleaningmanagement.model.User;



@WebServlet("/RechargeWalletController")
public class RechargeWalletController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Double wallet=Double.parseDouble(request.getParameter("amount"));
		User user=(User) session.getAttribute("CurrentUser");
		user.setWallet(user.getWallet() + wallet);
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		boolean b = userDaoImpl.rechargeWallet(user);
		if (b) {
			session.setAttribute("amount", wallet);
			session.setAttribute("recharge", true);
			response.sendRedirect("rechargeWallet.jsp");
		}
		}
		
	    
	

	

}
