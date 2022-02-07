package com.cleaningmanagement.daoimpl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.cleaningmanagement.dao.RequestDao;
import com.cleaningmanagement.model.Employee;
import com.cleaningmanagement.model.Request;
import com.cleaningmanagement.model.User;
import com.cleaningmanagement.util.ConnectionUtil;

public class RequestDaoImpl implements RequestDao {
	public boolean insertRequestDetails(Request request) {
		boolean flag = false;
		Connection connection = ConnectionUtil.getConnection();
		String query = "insert into WMS_request (user_id,emp_id,category,location,address) values (?,?,?,?,?)";
		PreparedStatement preparedStatement=null;
		try {
			EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			int empId = employeeDaoImpl.findEmpId(request.getEmployee());
			int userId = userDaoImpl.findUserId(request.getUser());
			preparedStatement = connection.prepareStatement(query);
		    preparedStatement.setInt(1, userId);
		    preparedStatement.setInt(2, empId);
		    preparedStatement.setString(3, request.getCatogories());
		    preparedStatement.setString(4, request.getLocation());
		    preparedStatement.setString(5, request.getAddress());
            flag = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,null );
		}
		return flag;

	}

	public List<Request> showRequest() {
		Connection connection = ConnectionUtil.getConnection();
		List<Request> listRequest = new ArrayList<>();
		String query = "select request_id,user_id,emp_id,category,location,requeststatus,request_date,employeestatus,address from WMS_request";
		Request request = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement = connection.prepareStatement(query);
		    resultSet = preparedStatement.executeQuery();
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
			
			while (resultSet.next()) {
				
				User user = userDaoImpl.findUser(resultSet.getInt(2));
			
				Employee employee = employeeDaoImpl.findEmployee(resultSet.getString(5));
				
				request = new Request(resultSet.getInt(1), user, employee, resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
						resultSet.getDate(7),resultSet.getString(8),resultSet.getString(9));
                listRequest.add(request);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,resultSet);
		}
		return listRequest;

	}

	public List<Request> showRequest(String search) {
		Connection connection = ConnectionUtil.getConnection();
		List<Request> listRequest = new ArrayList<>();
		String query = "select request_id,user_id,emp_id,category,location,requeststatus,request_date,employeestatus,requeststatus from WMS_request "
				+ "where category like ? or location like ? or employeestatus  like ? or "
				+ "requeststatus like ?";
		Request request = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + search.toLowerCase() + "%");
			preparedStatement.setString(2, "%" + search.toLowerCase() + "%");
			preparedStatement.setString(3, "%" + search.toLowerCase() + "%");
			preparedStatement.setString(4, "%" + search.toLowerCase() + "%");
		    resultSet = preparedStatement.executeQuery();
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
			
			while (resultSet.next()) {
                User user = userDaoImpl.findUser(resultSet.getInt(2));
                Employee employee = employeeDaoImpl.findEmployee(resultSet.getString(5));
                request = new Request(resultSet.getInt(1), user, employee, resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
			    resultSet.getDate(7),resultSet.getString(8),resultSet.getString(9));
                listRequest.add(request);

			} 

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,resultSet );
		}
		return listRequest;

	}

	public int findRequestID(int userId, String category, String location) {
		Connection  connection = ConnectionUtil.getConnection();
		String query = "select request_id from WMS_request where user_id=? and category=? and location=?";
		int id = 0;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
		    preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, category);
			preparedStatement.setString(3, location);
		    resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,resultSet );
		}
		return id;
	}

	public boolean deleteRequest(int requestId) {
		Connection connection = ConnectionUtil.getConnection();
		String deleteQuery = "delete from WMS_request where request_id=?";
		boolean flag = false;
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, requestId);
			flag = preparedStatement.executeUpdate() > 0;
			connection.commit();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,null );
		}
		return flag;

	}

	

	public int calculateWeight(String location, Date fromDate, Date toDate) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "select sum(c.weight_kg) from Category_details c join WMS_request r on c.categories=r.category  "
				+ "where r.location=? and r.requeststatus='completed' "
				+ "and r.request_date between ? and ? group by r.location ";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement=null;
		int weight=0;
		try {
		    preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, location);
			preparedStatement.setDate(2, new java.sql.Date(fromDate.getTime()));
			preparedStatement.setDate(3, new java.sql.Date(toDate.getTime()));
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				weight=resultSet.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,resultSet);
		}
		return weight;

	}

	@Override
	public int calculateWeight(String location, java.sql.Date fromDate, java.sql.Date toDate) {

		return 0;
	}

	
	

	
	
}
