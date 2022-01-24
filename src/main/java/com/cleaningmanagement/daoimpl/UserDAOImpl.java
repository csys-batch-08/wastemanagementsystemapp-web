package com.cleaningmanagement.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cleaningmanagement.dao.UserDao;
import com.cleaningmanagement.model.CategoryDetails;
import com.cleaningmanagement.model.Employee;
import com.cleaningmanagement.model.Request;
import com.cleaningmanagement.model.User;
import com.cleaningmanagement.util.ConnectionUtil;

public class UserDAOImpl implements UserDao {
	public boolean insertUserDatabase(User user) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "insert into  WMS_user(user_email,user_name,user_pwd,Address,mobile_no) values(?,?,?,?,?)";
		boolean b = false;
		PreparedStatement preparedStatement;
		try {
		    preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getUserEmail());
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getUserPwd());
			preparedStatement.setString(4, user.getUserAddress());
			preparedStatement.setLong(5, user.getUserMobileNo());
			b = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return b;

	}

	public User validateUser(String email, String passWord) {
		User user = null;
		Connection connection = ConnectionUtil.getConnection();
		String query = "select * from WMS_user where user_email='" + email + "' and user_pwd='" + passWord + "'";
		Statement statement;
		ResultSet resultSet;
		try {
			statement = connection.createStatement();
		    resultSet = statement.executeQuery(query);
			if (resultSet.next()) {

				user = new User(email, resultSet.getString(3), passWord, resultSet.getString(5), resultSet.getLong(6), resultSet.getDouble(7));
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return user;
	}

	public int findUserId(User user) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "select user_id from WMS_user where user_email= '" + user.getUserEmail() + "'";
		int id = 0;
		Statement statement;
		ResultSet resultSet;
		try {
			statement = connection.createStatement();
		    resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return id;

	}

	public User findUser(int id)

	{
		Connection connection = ConnectionUtil.getConnection();
		String query = "select * from WMS_user where user_id=" + id;
		User user = null;
		Statement statement;
		ResultSet resultSet;
		try {
		    statement = connection.createStatement();
		    resultSet = statement.executeQuery(query);

			if (resultSet.next()) {
				user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6),
						resultSet.getDouble(7));
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return user;

	}

	public int findUser(String email)

	{
		Connection connection = ConnectionUtil.getConnection();
		String query = "select user_id from WMS_user where user_email='" + email + "'";
		int n = 0;
		Statement statement;
		ResultSet resultSet;
		try {
			 statement = connection.createStatement();
			 resultSet = statement.executeQuery(query);

			if (resultSet.next()) {
				n = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return n;

	}

	public ResultSet userBill(User user) {
		UserDAOImpl userDao = new UserDAOImpl();
		int userid = userDao.findUserId(user);
		
		Connection connection = ConnectionUtil.getConnection();
		String joinQuery = "select r.request_id,u.user_name,r.category,c.weight_kg,c.amount,e.emp_name,r.request_date,r.location from WMS_request r "
				+ "join Category_details c on r.category=c.categories "
				+ "join  WMS_user u on u.user_id=r.user_id "
				+ "join  WMS_employee e on e.emp_id=r.emp_id "
				+ "where r.user_id="+userid+ "and r.employeestatus in ('pending' , 'inprogress')";
		ResultSet resultSet = null;
		Statement statement;
		try {
		    statement = connection.createStatement();
		    resultSet = statement.executeQuery(joinQuery);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	public boolean rechargeWallet(User user) {
		Connection connection = ConnectionUtil.getConnection();
		String updateQuery = "update WMS_user set wallet=? where user_id=?";
		UserDAOImpl userDao = new UserDAOImpl();
		int userId = userDao.findUserId(user);
		boolean flag = false;
		PreparedStatement preparedStatement;
		try {
		    preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setDouble(1, user.getWallet());
			preparedStatement.setInt(2, userId);
			flag = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return flag;

	}

	public boolean updateWallet(User user, int amount) {
		Connection connection = ConnectionUtil.getConnection();
		UserDAOImpl userDao = new UserDAOImpl();
		int userId = userDao.findUserId(user);
		String updateQuery = "update WMS_user set Wallet=" + (user.getWallet() - amount) + "where user_id=" + userId;
		boolean flag = false;
		Statement statement;
		try {
		    statement = connection.createStatement();
			flag = statement.executeUpdate(updateQuery) > 0;

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return flag;

	}

	public boolean refundWallet(User user, int amount) {
		Connection connection = ConnectionUtil.getConnection();
		UserDAOImpl userDao = new UserDAOImpl();
		int userId = userDao.findUserId(user);
		String updateQuery1 = "update WMS_user set Wallet=" + (user.getWallet() + amount) + "where user_id=" + userId;
		boolean flag = false;
		Statement statement;
		try {
		    statement = connection.createStatement();
			flag = statement.executeUpdate(updateQuery1) > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}

	public List<User> showUser() {
		Connection connection = ConnectionUtil.getConnection();
		List<User> listUser = new ArrayList<User>();
		String query = "select * from WMS_user";
		User user = null;
		Statement statement;
		ResultSet resultSet;
		try {
		    statement = connection.createStatement();
		    resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6),
						resultSet.getDouble(7));
				listUser.add(user);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUser;

	}

	public ResultSet showbill(User user)
	{   Connection connection = ConnectionUtil.getConnection();
	    String query="select u.user_name,r.location,r.category,c.weight_kg,r.request_date,c.amount from WMS_user u "
	    		+ "join wms_request r on u.user_id=r.user_id "
	    		+ "join Category_details c on r.category=c.categories "
	    		+ "where u.user_email='"+user.getUserEmail()+"' order by r.request_id desc";
	    ResultSet resultSet=null;
	    Statement statement;
	    try {
			 statement = connection.createStatement();
			 resultSet=statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
		
	}
}
