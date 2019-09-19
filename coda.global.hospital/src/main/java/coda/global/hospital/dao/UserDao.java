package coda.global.hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coda.global.hospital.model.User;

public class UserDao {
	DbConnection dbconnection = new DbConnection();
	Connection connection;
	ResultSet resultSet;
	public User getUser(String username) {
		PreparedStatement statement;
		try {
			Connection connection = dbconnection.getConnection();
			statement = connection.prepareStatement("select * from t_user where username=? AND is_deleted=0");
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				User userDetails= new User();
				userDetails.setUsername(resultSet.getString(1));
				userDetails.setPassword(resultSet.getString(2));
				userDetails.setPkUserId(resultSet.getInt(3));
				userDetails.setFkRoleId(resultSet.getInt(4));
				userDetails.setFirstName(resultSet.getString(5));
				userDetails.setLastName(resultSet.getString(6));
				userDetails.setAge(resultSet.getInt(7));
				return userDetails;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null)
				dbconnection.closeConnection();
		}
		return null;
	}
}
