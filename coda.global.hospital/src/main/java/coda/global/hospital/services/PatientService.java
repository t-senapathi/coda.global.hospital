package coda.global.hospital.services;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coda.global.hospital.dao.PatientDao;
import coda.global.hospital.exception.DoctorNotFoundException;
import coda.global.hospital.exception.PatientNotFoundException;
import coda.global.hospital.model.Patient;

public class PatientService{
	Scanner scan=new Scanner(System.in);
	Logger LOGGER=LoggerFactory.getLogger(PatientService.class);
	ResourceBundle errorConstant= ResourceBundle.getBundle("ErrorMessages");
	ResourceBundle inputConstant= ResourceBundle.getBundle("InputMessages");
	ResourceBundle outputConstant = ResourceBundle.getBundle("OutputMessages");
	ResourceBundle menuConstant= ResourceBundle.getBundle("MenuMessages");
	int input;
	Patient patientDetail=new Patient();
	PatientDao patientDao=new PatientDao();
	
	public boolean createPatient(Patient patient){
		// TODO Auto-generated method stub
		try {
			patientDetail=patientDao.createPatient(patient);
			if(patientDetail!=null)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	public Patient readPatient(int id){
		try {
			patientDetail=patientDao.readPatient(id);
			if(patientDetail==null)
				throw new PatientNotFoundException();
			else
				return patientDetail;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (PatientNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Patient> readAllPatient(){
		try {
			List<Patient> patientList=null;
			patientList=patientDao.readAllPatient();
			if(patientList==null)
				throw new DoctorNotFoundException();
			else
				return patientList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (DoctorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public boolean updatePatient(Patient newdata) {
		boolean result;
		try {
			result=patientDao.updatePatient(newdata);
			if(result)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	public boolean deletePatient(int id) {
		boolean result;
		try {
			result=patientDao.deletePatient(id);
			if(result)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
