package com.cleaningmanagement.daoimpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cleaningmanagement.dao.RequestDao;
import com.cleaningmanagement.model.Employee;
import com.cleaningmanagement.model.Request;
import com.cleaningmanagement.model.User;
import com.cleaningmanagement.util.ConnectionUtil;

public class RequestDAOImpl implements RequestDao {
	public boolean insertRequestDetails(Request request) {
		boolean flag = false;
		Connection connection = ConnectionUtil.getConnection();
		String query = "insert into WMS_request (user_id,emp_id,category,location) values (?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			EmployeeDAOImpl empDao = new EmployeeDAOImpl();
			UserDAOImpl userDao = new UserDAOImpl();
			
			int empId = empDao.findEmpId(request.getEmployee());
			int userId = userDao.findUserId(request.getUser());
			
		    preparedStatement = connection.prepareStatement(query);
		    preparedStatement.setInt(1, userId);
		    preparedStatement.setInt(2, empId);
		    preparedStatement.setString(3, request.getCatogories());
		    preparedStatement.setString(4, request.getLocation());

			flag = preparedStatement.executeUpdate() > 0;
			preparedStatement.executeUpdate("commit");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	public List<Request> showRequest() {
		Connection connection = ConnectionUtil.getConnection();
		List<Request> listRequest = new ArrayList<Request>();
		String query = "select * from WMS_request";
		Request request = null;
		Statement statement;
		ResultSet resultSet;
		try {
			statement = connection.createStatement();
		    resultSet = statement.executeQuery(query);
			UserDAOImpl userDao = new UserDAOImpl();
			EmployeeDAOImpl employeedao = new EmployeeDAOImpl();
			
			while (resultSet.next()) {
				
				User user = userDao.findUser(resultSet.getInt(2));
			
				Employee employee = employeedao.findEmployee(resultSet.getString(5));
				
				request = new Request(resultSet.getInt(1), user, employee, resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
						resultSet.getString(7), resultSet.getDate(8));

				listRequest.add(request);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listRequest;

	}

	public List<Request> showRequest(String search) {
		Connection connection = ConnectionUtil.getConnection();
		List<Request> listRequest = new ArrayList<Request>();
		String que = "select * from WMS_request where category like '%" + search.toLowerCase()
				+ "%' or location like '%" + search.toLowerCase() + "%' or employeestatus  like '%"
				+ search.toLowerCase() + "%' or requeststatus like '%" + search.toLowerCase() + "%'";
		Request request = null;
		Statement statement;
		ResultSet resultSet;
		try {
		    statement = connection.createStatement();
		    resultSet = statement.executeQuery(que);
			UserDAOImpl userDao = new UserDAOImpl();
			EmployeeDAOImpl employeedao = new EmployeeDAOImpl();
			
			while (resultSet.next()) {
                User user = userDao.findUser(resultSet.getInt(2));
                Employee employee = employeedao.findEmployee(resultSet.getString(5));
                request = new Request(resultSet.getInt(1), user, employee, resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                		resultSet.getString(7), resultSet.getDate(8));
                listRequest.add(request);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return listRequest;

	}

	public int findRequestID(int userId, String category, String location) {
		Connection  connection = ConnectionUtil.getConnection();
		String query = "select request_id from WMS_request where user_id=? and category=? and location=?";
		int id = 0;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
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
		return id;
	}

	public boolean deleteRequest(int RequestId) {
		Connection connection = ConnectionUtil.getConnection();
		String deleteQuery = "delete from WMS_request where request_id=" + RequestId;
		boolean flag = false;
		Statement statement;
		try {
		    statement = connection.createStatement();
			flag = statement.executeUpdate(deleteQuery) > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	

	public ResultSet CalculateAmount(String location, Date fromDate, Date toDate) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "select sum(c.weight_kg) from Category_details c join WMS_request r on c.categories=r.category  where r.location=? and r.requeststatus='completed' and r.request_date between ? and ? group by r.location ";
		ResultSet resultSet = null;
		PreparedStatement preparedStatement;
		try {
		    preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, location);
			preparedStatement.setDate(2, new java.sql.Date(fromDate.getTime()));
			preparedStatement.setDate(3, new java.sql.Date(toDate.getTime()));
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;

	}

	@Override
	public ResultSet CalculateAmount(String location, java.sql.Date fromdate, java.sql.Date todate) {
		// TODO Auto-generated method stub
		return null;
	}

}
