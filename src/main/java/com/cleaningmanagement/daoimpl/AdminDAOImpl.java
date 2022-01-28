package com.cleaningmanagement.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.cleaningmanagement.dao.AdminDao;
import com.cleaningmanagement.model.Admin;

import com.cleaningmanagement.util.ConnectionUtil;

public class AdminDAOImpl implements AdminDao {
	public Admin AdminDatabase(String adminEmail, String passWord) {
		Admin admin = null;
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionUtil.getConnection();
			String insertQuery = "select admin_email,admin_pwd from WMS_admin where admin_email=? and admin_pwd=?";
					

			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1,adminEmail );
			preparedStatement.setString(2,passWord);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				admin = new Admin(adminEmail, passWord);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement,resultSet );
		}
		return admin;

	}

	public int updateRequest(String status, int requestId) {
		Connection connection = ConnectionUtil.getConnection();
		String updateQuery = "update WMS_request set requeststatus=? where request_id=?";
		PreparedStatement preparedStatement=null;
		int n = 0;
		try {
		    preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, requestId);
			n = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,null);
		}

		return n;

	}

}
