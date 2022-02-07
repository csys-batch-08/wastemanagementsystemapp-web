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

public class UserDaoImpl implements UserDao {
	public boolean insertUserDatabase(User user) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "insert into  WMS_user(user_email,user_name,user_pwd,Address,mobile_no) values(?,?,?,?,?)";
		boolean b = false;
		PreparedStatement preparedStatement=null;
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
		finally {
			ConnectionUtil.close(connection, preparedStatement,null );
		}
		return b;

	}

	public User validateUser(String email, String passWord) {
		User user = null;
		Connection connection = ConnectionUtil.getConnection();
		String query = "select user_id,user_email,user_name,user_pwd,Address,mobile_no,wallet from WMS_user where user_email=? and user_pwd=?";
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, passWord);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				user = new User(email, resultSet.getString(3), passWord, resultSet.getString(5), resultSet.getLong(6),
						resultSet.getDouble(7));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return user;
	}

	public int findUserId(User user) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "select user_id,wallet from WMS_user where user_email=?";
		int id = 0;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement = connection.prepareStatement(query);
			System.out.println(user.getUserEmail());
			preparedStatement.setString(1, user.getUserEmail() );
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
				user.setWallet(resultSet.getDouble(2));
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return id;

	}

	public User findUser(int id)

	{
		Connection connection = ConnectionUtil.getConnection();
		String query = "select user_id,user_email,user_name,user_pwd,Address,mobile_no,wallet from WMS_user where user_id=?";
		User user = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getLong(6), resultSet.getDouble(7));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return user;

	}

	public int findUser(String email)

	{
		Connection connection = ConnectionUtil.getConnection();
		String query = "select user_id from WMS_user where user_email=?";
		int n = 0;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				n = resultSet.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return n;

	}

	public List<Object> userBill(User user) {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		int userid = userDaoImpl.findUserId(user);

		Connection connection = ConnectionUtil.getConnection();
		String joinQuery = "select r.request_id,r.category,c.weight_kg,c.amount,e.emp_name,r.request_date,r.location,r.address,r.requeststatus from WMS_request r "
				+ "join Category_details c on r.category=c.categories "  
				+ "join  WMS_employee e on e.emp_id=r.emp_id " 
				+ "where r.user_id=? and r.employeestatus in ('pending' , 'inprogress')";
				 
		ResultSet resultSet = null;
		PreparedStatement preparedStatement=null;
		List<Object> list = null;
		List<Object> listObject=null;
		try {
			preparedStatement = connection.prepareStatement(joinQuery);
			preparedStatement.setInt(1, userid);
			resultSet = preparedStatement.executeQuery();

		    listObject = new ArrayList<>();
			while (resultSet.next()) {
				list = new ArrayList<>();
				list.add(resultSet.getInt("request_id"));
				list.add(resultSet.getString("category"));
				list.add(resultSet.getInt("weight_kg"));
				list.add(resultSet.getInt("amount"));
				list.add(resultSet.getString("emp_name"));
				list.add(resultSet.getDate("request_date"));
				list.add(resultSet.getString("location"));
				list.add(resultSet.getString("address"));
				list.add(resultSet.getString("requeststatus"));
				listObject.add(list);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return listObject;
	}

	public boolean rechargeWallet(User user) {
		Connection connection = ConnectionUtil.getConnection();
		String updateQuery = "update WMS_user set wallet=? where user_id=?";
		UserDaoImpl userDao = new UserDaoImpl();
		Double wallet = user.getWallet();
		int userId = userDao.findUserId(user);
		boolean flag = false;
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setDouble(1,wallet);
			preparedStatement.setInt(2, userId);
			flag = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,null);
		}
		return flag;

	}

	public boolean updateWallet(User user, int amount) {
		Connection connection = ConnectionUtil.getConnection();
		UserDaoImpl userDao = new UserDaoImpl();
		int userId = userDao.findUserId(user);
		String updateQuery = "update WMS_user set Wallet=? where user_id=?";
		boolean flag = false;
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setDouble(1, user.getWallet() - amount );
			preparedStatement.setInt(2,  userId);
			flag = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,null);
		}
		return flag;

	}

	public boolean refundWallet(User user, int amount) {
		Connection connection = ConnectionUtil.getConnection();
		UserDaoImpl userDao = new UserDaoImpl();
		int userId = userDao.findUserId(user);
		String updateQuery = "update WMS_user set Wallet=wallet + ? where user_id=?";
		boolean flag = false;
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setDouble(1, amount);
			preparedStatement.setInt(2, userId);
			flag = preparedStatement.executeUpdate() > 0;
            
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,null);
		}
		return flag;

	}

	public List<User> showUser() {
		Connection connection = ConnectionUtil.getConnection();
		List<User> listUser = new ArrayList<>();
		String query = "select user_id,user_email,user_name,user_pwd,Address,mobile_no,wallet from WMS_user";
		User user = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getLong(6), resultSet.getDouble(7));
				listUser.add(user);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,resultSet);
		}
		return listUser;

	}

	public List<List<Object>> showbill(User user) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "select u.user_name,r.location,r.address,r.category,c.weight_kg,r.request_date,c.amount from WMS_user u "
				+ "join WMS_request r on u.user_id=r.user_id " 
				+ "join Category_details c on r.category=c.categories "
				+ "where u.user_email=? order by r.request_id desc fetch first 1 row only";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement=null;
		List<Object> list = null;
		List<List<Object>> listObject=null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getUserEmail());
			resultSet = preparedStatement.executeQuery();
			listObject = new ArrayList<>();
			while(resultSet.next())
			{
				list=new ArrayList<>();
				list.add(resultSet.getString("user_name"));
				list.add(resultSet.getString("location"));
				list.add(resultSet.getString("address"));
				list.add(resultSet.getString("category"));
				list.add(resultSet.getInt("weight_kg"));
				list.add(resultSet.getDate("request_date"));
				list.add(resultSet.getInt("amount"));
				listObject.add(list);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,resultSet);
		}
		return listObject;

	}
	
	public boolean cancelRequest(int requestId) {
		Connection connection = ConnectionUtil.getConnection();
		String updateQuery = "update WMS_request set requeststatus='cancel' where request_id=?";
		PreparedStatement preparedStatement=null;
		boolean b=false;
		try {
		    preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(1, requestId);
			b = preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,null);
		}

		return b;

	}
}
