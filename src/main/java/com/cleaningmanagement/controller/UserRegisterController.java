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

import com.cleaningmanagement.daoimpl.UserDAOImpl;
import com.cleaningmanagement.exception.FoundException;
import com.cleaningmanagement.model.User;


@WebServlet("/UserRegisterController")
public class UserRegisterController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailId=request.getParameter("emailid");
		try {
		UserDAOImpl userdao=new UserDAOImpl();
		List<User> user=userdao.showUser();
		for(int i=0;i<user.size();i++)
		{
			if(user.get(i).getUserEmail().equals(emailId))
			{
				throw new FoundException();
			}
		}
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String address=request.getParameter("address");
		long number=Long.parseLong(request.getParameter("mobilenumber"));
		User user1=new User(emailId,name,password,address,number,0.0);
		UserDAOImpl ud = new UserDAOImpl();
		boolean b = ud.insertUserDatabase(user1);
		if(b==true)
		{
			response.sendRedirect("index.jsp");
		}
		}catch(FoundException e)
		{
			HttpSession session=request.getSession();
			session.setAttribute("email", e.getMessage3());
			response.sendRedirect("userRegister.jsp");
		}
		
		
	}

	

}
