package com.cleaningmanagement.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cleaningmanagement.dao.CategoryDao;
import com.cleaningmanagement.model.CategoryDetails;
import com.cleaningmanagement.model.User;
import com.cleaningmanagement.util.ConnectionUtil;

public class CategoryDAOImpl implements CategoryDao {
	public int insertCategoryDetails(CategoryDetails categoryDetails) {
		Connection connection = ConnectionUtil.getConnection();
		String insertQuery = "insert into Category_details values(?,?,?)";
		int i = 0;
		PreparedStatement preparedStatement=null;
		try {
		    preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, categoryDetails.getWeightInKg());
			preparedStatement.setString(2, categoryDetails.getCategory());
			preparedStatement.setInt(3, categoryDetails.getAmount());
			
			i = preparedStatement.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,null);
		}
		return i;

	}

	public CategoryDetails findAmount(String category) {
		Connection connection = ConnectionUtil.getConnection();
		String query = "select amount from Category_details where categories=?";
		CategoryDetails categoryDetails = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
		    preparedStatement = connection.prepareStatement(query);
		    preparedStatement.setString(1, category );
		    resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				categoryDetails = new CategoryDetails(0, null, resultSet.getInt(1));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection, preparedStatement,resultSet);
		}
		return categoryDetails;

	}

	public List<CategoryDetails> listdetails() {
		Connection connection = ConnectionUtil.getConnection();
		List<CategoryDetails> list = new ArrayList<CategoryDetails>();
		String query = "select weight_kg,categories,amount from Category_details ";
		CategoryDetails categoryDetails = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			preparedStatement= connection.prepareStatement(query);
		    resultSet = preparedStatement.executeQuery();
			while ( resultSet.next()) {
				categoryDetails= new CategoryDetails( resultSet.getInt(1),  resultSet.getString(2),  resultSet.getInt(3));
				list.add(categoryDetails);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(connection,preparedStatement ,resultSet);
		}
		return list;

	}
}
