package com.cleaningmanagement.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.cleaningmanagement.dao.AdminDao;
import com.cleaningmanagement.model.Admin;
import com.cleaningmanagement.model.CategoryDetails;
import com.cleaningmanagement.util.ConnectionUtil;

public class AdminDAOImpl implements AdminDao {
	public Admin AdminDatabase(String adminEmail, String passWord) {
		Admin admin = null;
		Connection con = null;
		Statement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			String insertQuery = "select * from WMS_admin where admin_email='" + adminEmail + "'and admin_pwd='"
					+ passWord + "'";

			pstmt = con.createStatement();
			rs = pstmt.executeQuery(insertQuery);
			if (rs.next()) {
				admin = new Admin(adminEmail, passWord);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(con, null, rs, pstmt);
		}
		return admin;

	}

	public int updateRequest(String status, int requestId) {
		Connection con = ConnectionUtil.getConnection();
		String updateQuery = "update WMS_request set requeststatus=? where request_id=?";

		int n = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			pstmt.setString(1, status);
			pstmt.setInt(2, requestId);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n;

	}

}
