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

public class EmployeeDAOImpl implements EmployeeDao {
	public boolean insertEmpDatabase(Employee employee) {
		boolean flag = false;
		Connection connection = ConnectionUtil.getConnection();
		String query = "insert into  WMS_employee(emp_email,emp_name,emp_pwd,location) values(?,?,?,?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, employee.getEmpEmail());
			preparedStatement.setString(2, employee.getEmpName());
			preparedStatement.setString(3, employee.getEmpPassWord());
			preparedStatement.setString(4, employee.getLocation());
			flag = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;

	}

	public Employee validation(String email, String passWord) {
		Connection connection = ConnectionUtil.getConnection();
		ResultSet resultSet = null;
		Employee employee = null;
		Statement statement;
		try {
			String query = "select * from WMS_employee where emp_email='" + email + "' and emp_pwd='" + passWord + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				employee = new Employee(email, resultSet.getString(3), passWord, resultSet.getString(5),
						resultSet.getString(6));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return employee;

	}

	public int findEmpId(Employee employee) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "select emp_id from WMS_employee where location= '" + employee.getLocation() + "'";
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

	public Employee findEmployee(String location) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "select * from WMS_employee where location= '" + location + "'";
		Employee employee = null;
		Statement statement;
		ResultSet resultSet;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			if (resultSet.next()) {

				employee = new Employee(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return employee;

	}

	public List<Employee> showEmployee() {
		Connection connection = ConnectionUtil.getConnection();
		List<Employee> list = new ArrayList<Employee>();
		String query = "select emp_id,emp_email,emp_name,emp_pwd,location,status from WMS_employee";
		Employee employee = null;
		Statement statement=null;
		ResultSet resultSet=null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				employee = new Employee(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6));
				list.add(employee);
             }
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, null,resultSet ,statement);
		}
		return list;

	}

	public ResultSet findEmployeeRequest(Employee employee) {
		Connection connection = ConnectionUtil.getConnection();
		EmployeeDAOImpl employeeDao = new EmployeeDAOImpl();
		int empId = employeeDao.findEmpId(employee);

		String joinQuery = "select r.request_id,r.user_id,r.category,r.location,c.weight_kg,c.amount,r.request_date,r.employeestatus from WMS_request r join Category_details c on r.category=c.categories "
				+ "where r.emp_id=" + empId;
		Statement statement;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(joinQuery);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	public boolean updatestatus(String status, String emailId) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "update WMS_employee set status=?where emp_email=?";
		boolean flag = false;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			preparedStatement.setString(2, emailId);
			flag = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return flag;

	}

	public boolean updateEmployeeStatus(String status, int reqId) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "update WMS_request set employeestatus=? where request_id=?";
		PreparedStatement preparedStatement;
		boolean flag = false;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, reqId);
			flag = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return flag;

	}

}
