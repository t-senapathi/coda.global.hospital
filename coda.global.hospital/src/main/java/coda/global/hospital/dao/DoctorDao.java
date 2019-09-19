package coda.global.hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import coda.global.hospital.model.Doctor;

public class DoctorDao {
	DbConnection dbconnection=new DbConnection();
	Doctor doctor = null;
	boolean result;

	public Doctor createDoctor(Doctor doctor) throws SQLException {
		Connection connection = dbconnection.getConnection();

		PreparedStatement userStatement, doctorStatement;
		try {
			connection.setAutoCommit(false);
			userStatement = connection.prepareStatement(
					"insert into t_user (username,password,fk_role_id,first_name,last_name,age) values(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			userStatement.setString(1, doctor.getUsername());
			userStatement.setString(2, doctor.getPassword());
			userStatement.setInt(3, 2);
			userStatement.setString(4, doctor.getFirstName());
			userStatement.setString(5, doctor.getLastName());
			userStatement.setInt(6, doctor.getAge());
			int rowsAffected = userStatement.executeUpdate();
			if (rowsAffected == 1) {
				ResultSet keySetUser = userStatement.getGeneratedKeys();
				int userId = 0;
				if (keySetUser.next()) {
					userId = keySetUser.getInt(1);
				}
				doctorStatement = connection.prepareStatement(
						"insert into t_doctor (fk_user_id,doctor_specialisation,experience) values(?,?,?)");
				doctorStatement.setInt(1, userId);
				doctorStatement.setString(2, doctor.getSpecialisation());
				doctorStatement.setInt(3, doctor.getExperience());
				int rowsAffectedDoctor = doctorStatement.executeUpdate();
				if (rowsAffectedDoctor == 1) {
					connection.commit();
					doctor.setPkUserId(userId);
					doctor.setFkUserId(userId);
					return doctor;
				} else {
					connection.rollback();
				}
			} else {
				connection.rollback();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection.rollback();
			throw new SQLException();

		}finally
		{
			if(connection!=null)
				dbconnection.closeConnection();
		}
		return null;
	}

	public Doctor readDoctor(int id) throws SQLException {
		Connection connection = dbconnection.getConnection();
		ResultSet resultSet;
		PreparedStatement userStatement;
		try {
			connection.setAutoCommit(false);
			userStatement = connection.prepareStatement("select * from t_user join t_doctor on t_user.pk_user_id=t_doctor.fk_user_id where pk_user_id=? and t_user.is_deleted=0");
			userStatement.setInt(1, id);
			resultSet = userStatement.executeQuery();
			if (resultSet.next()) {
				doctor = new Doctor();
				doctor.setUsername(resultSet.getString(1));
				doctor.setPassword(resultSet.getString(2));
				doctor.setPkUserId(resultSet.getInt(3));
				doctor.setFkRoleId(resultSet.getInt(4));
				doctor.setFirstName(resultSet.getString(5));
				doctor.setLastName(resultSet.getString(6));
				doctor.setAge(resultSet.getInt(7));
				doctor.setPkDoctorId(resultSet.getInt(11));
				doctor.setFkUserId(resultSet.getInt(13));
				doctor.setSpecialisation(resultSet.getString(12));
				doctor.setExperience(resultSet.getInt(17));
			}
			return doctor;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e.getMessage());

		}finally
		{
			if(connection!=null)
				dbconnection.closeConnection();
		}
	}

	public List<Doctor> readAllDoctor() throws SQLException {
		Connection connection = dbconnection.getConnection();
		ResultSet resultSet;
		PreparedStatement userStatement;
		List<Doctor> doctorlist = new ArrayList<Doctor>();
		try {
			connection.setAutoCommit(false);
			userStatement = connection
					.prepareStatement("select * from t_user join t_doctor on t_user.pk_user_id=t_doctor.fk_user_id where t_user.is_deleted=0");
			resultSet = userStatement.executeQuery();
			while (resultSet.next()) {
				doctor = new Doctor();
				doctor.setUsername(resultSet.getString(1));
				doctor.setPassword(resultSet.getString(2));
				doctor.setPkUserId(resultSet.getInt(3));
				doctor.setFkRoleId(resultSet.getInt(4));
				doctor.setFirstName(resultSet.getString(5));
				doctor.setLastName(resultSet.getString(6));
				doctor.setAge(resultSet.getInt(7));
				doctor.setPkDoctorId(resultSet.getInt(11));
				doctor.setFkUserId(resultSet.getInt(13));
				doctor.setSpecialisation(resultSet.getString(12));
				doctor.setExperience(resultSet.getInt(17));
				doctorlist.add(doctor);
			}
			return doctorlist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e);

		}finally
		{
			if(connection!=null)
				dbconnection.closeConnection();
		}
	}
	public boolean updateDoctor(Doctor newdata) throws SQLException {
		Connection connection = dbconnection.getConnection();

		PreparedStatement userStatement, doctorStatement;
		try {
			connection.setAutoCommit(false);
			userStatement = connection.prepareStatement("update t_user SET password=?,age=? where pk_user_id=? and is_deleted=0",
					Statement.RETURN_GENERATED_KEYS);
			userStatement.setString(1, newdata.getPassword());
			userStatement.setInt(2, newdata.getAge());
			userStatement.setInt(3, newdata.getPkUserId());
			int rowsAffected = userStatement.executeUpdate();
			if (rowsAffected == 1) {
				doctorStatement = connection.prepareStatement("update t_doctor SET doctor_specialisation=?,experience=? where fk_user_id=? and is_deleted=0");
				doctorStatement.setString(1, newdata.getSpecialisation());
				doctorStatement.setInt(2, newdata.getExperience());
				doctorStatement.setInt(3, newdata.getPkUserId());
				int rowsAffectedDoctor = doctorStatement.executeUpdate();
				if (rowsAffectedDoctor == 1) {
					connection.commit();
					return true;
				} else {
					connection.rollback();
					return false;
				}
			} else {
				connection.rollback();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection.rollback();
			throw new SQLException();

		}finally
		{
			if(connection!=null)
				dbconnection.closeConnection();
		}
		return false;
	}


	public boolean deleteDoctor(int id) throws SQLException {
		Connection connection = dbconnection.getConnection();

		PreparedStatement userStatement, doctorStatement;
		try {
			connection.setAutoCommit(false);
			userStatement = connection.prepareStatement("update t_user SET is_deleted=1 where pk_user_id=?",
					Statement.RETURN_GENERATED_KEYS);
			userStatement.setInt(1, id);
			int rowsAffected = userStatement.executeUpdate();
			if (rowsAffected == 1) {
				doctorStatement = connection.prepareStatement("update t_doctor SET is_deleted=1 where fk_user_id=?");
				doctorStatement.setInt(1, id);
				int rowsAffectedDoctor = doctorStatement.executeUpdate();
				if (rowsAffectedDoctor == 1) {
					connection.commit();
					return true;
				} else {
					connection.rollback();
					return false;
				}
			} else {
				connection.rollback();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection.rollback();
			throw new SQLException();

		}finally
		{
			if(connection!=null)
				dbconnection.closeConnection();
		}
		return false;
	}

}

