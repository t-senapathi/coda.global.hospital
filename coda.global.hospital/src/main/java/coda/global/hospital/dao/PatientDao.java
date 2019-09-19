package coda.global.hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import coda.global.hospital.model.Patient;

public class PatientDao {
	DbConnection dbconnection=new DbConnection();
	Patient patient = null;
	boolean result;

	public Patient createPatient(Patient patient) throws SQLException {
		PreparedStatement userStatement, patientStatement;
		Connection connection=null;
		try {
			connection = dbconnection.getConnection();
			connection.setAutoCommit(false);
			userStatement = connection.prepareStatement(
					"insert into t_user (username,password,fk_role_id,first_name,last_name,age) values(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			userStatement.setString(1, patient.getUsername());
			userStatement.setString(2, patient.getPassword());
			userStatement.setInt(3, 1);
			userStatement.setString(4, patient.getFirstName());
			userStatement.setString(5, patient.getLastName());
			userStatement.setInt(6, patient.getAge());
			int rowsAffected = userStatement.executeUpdate();
			if (rowsAffected == 1) {
				ResultSet keySetUser = userStatement.getGeneratedKeys();
				int userId = 0;
				if (keySetUser.next()) {
					userId = keySetUser.getInt(1);
				}
				patientStatement = connection.prepareStatement(
						"insert into t_patient (fk_user_id,patient_height,patient_weight,door_no,street,city,blood_group) values(?,?,?,?,?,?,?)");
				patientStatement.setInt(1, userId);
				patientStatement.setInt(2, patient.getPatientHeight());
				patientStatement.setInt(3, patient.getPatientWeight());
				patientStatement.setString(4, patient.getDoorNo());
				patientStatement.setString(5, patient.getStreet());
				patientStatement.setString(6, patient.getCity());
				patientStatement.setString(7, patient.getBloodGroup());
				int rowsAffectedPatient = patientStatement.executeUpdate();
				if (rowsAffectedPatient!=0) {
					connection.commit();
					patient.setPkUserId(userId);
					patient.setFkUserId(userId);
					return patient;
				} else {
					connection.rollback();
				}
			} else {
				connection.rollback();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection.rollback();
			throw new SQLException(e.getMessage());

		}
		finally
		{
			if(connection!=null)
				dbconnection.closeConnection();
		}
		return null;
	}

	public Patient readPatient(int id) throws SQLException {// change to getPatient
		Connection connection = dbconnection.getConnection();
		ResultSet resultSet;
		PreparedStatement userStatement;
		try {
			connection.setAutoCommit(false);
			userStatement = connection.prepareStatement("select * from t_user join t_patient on t_user.pk_user_id=t_patient.fk_user_id where pk_user_id=? and t_user.is_deleted=0");
			userStatement.setInt(1, id);
			resultSet = userStatement.executeQuery();
			if (resultSet.next()) {
				patient = new Patient();
				patient.setUsername(resultSet.getString(1));
				patient.setPassword(resultSet.getString(2));
				patient.setPkUserId(resultSet.getInt(3));
				patient.setFkRoleId(resultSet.getInt(4));
				patient.setFirstName(resultSet.getString(5));
				patient.setLastName(resultSet.getString(6));
				patient.setAge(resultSet.getInt(7));
				patient.setPkPatientId(resultSet.getInt(11));
				patient.setFkUserId(resultSet.getInt(12));
				patient.setPatientHeight(resultSet.getInt(13));
				patient.setPatientWeight(resultSet.getInt(14));
				patient.setDoorNo(resultSet.getString(18));
				patient.setStreet(resultSet.getString(19));
				patient.setCity(resultSet.getString(20));
				patient.setBloodGroup(resultSet.getString(21));
			}
			return patient;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException();

		}finally
		{
			if(connection!=null)
				dbconnection.closeConnection();
		}
	}

	public List<Patient> readAllPatient() throws SQLException {
		Connection connection = dbconnection.getConnection();
		ResultSet resultSet;
		PreparedStatement userStatement;
		List<Patient> patientlist = new ArrayList<Patient>();
		try {
			connection.setAutoCommit(false);
			userStatement = connection
					.prepareStatement("select * from t_user join t_patient on t_user.pk_user_id=t_patient.fk_user_id where t_user.is_deleted=0");
			resultSet = userStatement.executeQuery();
			while (resultSet.next()) {
				patient = new Patient();
				patient.setUsername(resultSet.getString(1));
				patient.setPassword(resultSet.getString(2));
				patient.setPkUserId(resultSet.getInt(3));
				patient.setFkRoleId(resultSet.getInt(4));
				patient.setFirstName(resultSet.getString(5));
				patient.setLastName(resultSet.getString(6));
				patient.setAge(resultSet.getInt(7));
				patient.setFkUserId(resultSet.getInt(11));
				patient.setPatientHeight(resultSet.getInt(12));
				patient.setPatientWeight(resultSet.getInt(13));
				patient.setDoorNo(resultSet.getString(17));
				patient.setStreet(resultSet.getString(18));
				patient.setCity(resultSet.getString(19));
				patient.setBloodGroup(resultSet.getString(20));
				patientlist.add(patient);
			}
			return patientlist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e);

		}finally
		{
			if(connection!=null)
				dbconnection.closeConnection();
		}
	}
	public boolean updatePatient(Patient newdata) throws SQLException {
		Connection connection = dbconnection.getConnection();

		PreparedStatement userStatement, patientStatement;
		try {
			connection.setAutoCommit(false);
			userStatement = connection.prepareStatement("update t_user SET password=?,age=? where pk_user_id=? and is_deleted=0",
					Statement.RETURN_GENERATED_KEYS);
			userStatement.setString(1, newdata.getPassword());
			userStatement.setInt(2, newdata.getAge());
			userStatement.setInt(3, newdata.getPkUserId());
			int rowsAffected = userStatement.executeUpdate();
			if (rowsAffected == 1) {
				patientStatement = connection.prepareStatement("update t_patient SET patient_height=?,patient_weight=? where fk_user_id=? and is_deleted=0");
				patientStatement.setInt(1, newdata.getPatientHeight());
				patientStatement.setInt(2, newdata.getPatientWeight());
				patientStatement.setInt(3, newdata.getPkUserId());
				int rowsAffectedPatient = patientStatement.executeUpdate();
				if (rowsAffectedPatient == 1) {
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


	public boolean deletePatient(int id) throws SQLException {
		Connection connection = dbconnection.getConnection();

		PreparedStatement userStatement, patientStatement;
		try {
			connection.setAutoCommit(false);
			userStatement = connection.prepareStatement("update t_user SET is_deleted=1 where pk_user_id=?",
					Statement.RETURN_GENERATED_KEYS);
			userStatement.setInt(1, id);
			int rowsAffected = userStatement.executeUpdate();
			if (rowsAffected == 1) {
				patientStatement = connection.prepareStatement("update t_patient SET is_deleted=1 where fk_user_id=?");
				patientStatement.setInt(1, id);
				int rowsAffectedPatient = patientStatement.executeUpdate();
				if (rowsAffectedPatient == 1) {
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
