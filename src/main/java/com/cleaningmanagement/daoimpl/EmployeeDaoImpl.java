package com.cleaningmanagement.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cleaningmanagement.dao.EmployeeDao;
import com.cleaningmanagement.model.Employee;
import com.cleaningmanagement.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	public boolean insertEmpDatabase(Employee employee) {
		boolean flag = false;
		Connection connection = ConnectionUtil.getConnection();
		String query = "insert into  WMS_employee(emp_email,emp_name,emp_pwd,location) values(?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, employee.getEmpEmail());
			preparedStatement.setString(2, employee.getEmpName());
			preparedStatement.setString(3, employee.getEmpPassWord());
			preparedStatement.setString(4, employee.getLocation());
			flag = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		return flag;

	}

	public Employee validation(String email, String passWord) {
		Connection connection = ConnectionUtil.getConnection();
		ResultSet resultSet = null;
		Employee employee = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "select emp_id,emp_email,emp_name,emp_pwd,location,status from WMS_employee "
					+ "where emp_email=? and emp_pwd=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, passWord);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = new Employee(email, resultSet.getString(3), passWord, resultSet.getString(5),
						resultSet.getString(6));
				employee.setEmpId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return employee;

	}

	public int findEmpId(Employee employee) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "select emp_id from WMS_employee where location=?";
		int id = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, employee.getLocation());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return id;
	}

	public Employee findEmployee(String location) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "select emp_id,emp_email,emp_name,emp_pwd,location,status from WMS_employee where location=?";
		Employee employee = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, location);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				employee = new Employee(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return employee;

	}

	public List<Employee> showEmployee(Employee emp) {
		Connection connection = ConnectionUtil.getConnection();
		List<Employee> list = new ArrayList<>();
		String query = "select emp_id,emp_email,emp_name,emp_pwd,location,status from WMS_employee";
		Employee employee = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee = new Employee(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6));
				list.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return list;

	}

	public List<Employee> showEmployee() {
		Connection connection = ConnectionUtil.getConnection();
		List<Employee> list = new ArrayList<>();
		String query = "select emp_id,emp_email,emp_name,emp_pwd,location,status from WMS_employee";
		Employee employee = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee = new Employee(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6));
				list.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return list;

	}

	public List<List<Object>> findEmployeeRequest(Employee employee) {
		Connection connection = ConnectionUtil.getConnection();
		String joinQuery = "select r.request_id,u.user_name,r.category,r.location,r.address,c.weight_kg,c.amount,r.request_date,r.employeestatus,r.requeststatus from WMS_request r "
				+ "join Category_details c on r.category=c.categories "
				+ "join WMS_user u on u.user_id=r.user_id where r.emp_id=?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<List<Object>> listObject = null;
		List<Object> list = null;
		try {
			preparedStatement = connection.prepareStatement(joinQuery);
			preparedStatement.setInt(1, employee.getEmpId());
			resultSet = preparedStatement.executeQuery();
			listObject = new ArrayList<>();
			while (resultSet.next()) {
				list = new ArrayList<>();
				list.add(resultSet.getInt("request_id"));
				list.add(resultSet.getString("user_name"));
				list.add(resultSet.getString("category"));
				list.add(resultSet.getString("location"));
				list.add(resultSet.getString("address"));
				list.add(resultSet.getInt("weight_kg"));
				list.add(resultSet.getInt("amount"));
				list.add(resultSet.getDate("request_date"));
				list.add(resultSet.getString("employeestatus"));
				list.add(resultSet.getString("requeststatus"));
				listObject.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return listObject;
	}

	public boolean updatestatus(String status, String emailId) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "update WMS_employee set status=?where emp_email=?";
		boolean flag = false;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			preparedStatement.setString(2, emailId);
			flag = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		return flag;

	}

	public boolean updateEmployeeStatus(String status, int reqId) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "update WMS_request set employeestatus=? where request_id=?";
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, reqId);
			flag = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		return flag;

	}

}
