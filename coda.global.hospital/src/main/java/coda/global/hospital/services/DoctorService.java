package coda.global.hospital.services;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coda.global.hospital.dao.DoctorDao;
import coda.global.hospital.exception.DoctorNotFoundException;
import coda.global.hospital.model.Doctor;

public class DoctorService{
	Scanner scan=new Scanner(System.in);
	Logger LOGGER=LoggerFactory.getLogger(DoctorService.class);
	ResourceBundle errorConstant= ResourceBundle.getBundle("ErrorMessages");
	ResourceBundle inputConstant= ResourceBundle.getBundle("InputMessages");
	ResourceBundle outputConstant = ResourceBundle.getBundle("OutputMessages");
	ResourceBundle menuConstant= ResourceBundle.getBundle("MenuMessages");
	int input;
	Doctor doctorDetail=new Doctor();
	DoctorDao doctorDao=new DoctorDao();
	
	public boolean createDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		try {
			doctorDetail=doctorDao.createDoctor(doctor);
			if(doctorDetail!=null)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	public Doctor readDoctor(int id){
		try {
			doctorDetail=doctorDao.readDoctor(id);
			if(doctorDetail==null)
				throw new DoctorNotFoundException();
			else
				return doctorDetail;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (DoctorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Doctor> readAllDoctor(){
		try {
			List<Doctor> doctorList=null;
			doctorList=doctorDao.readAllDoctor();
			if(doctorList==null)
				throw new DoctorNotFoundException();
			else
				return doctorList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (DoctorNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public boolean updateDoctor(Doctor newdata) {
		boolean result;
		try {
			result=doctorDao.updateDoctor(newdata);
			if(result)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	public boolean deleteDoctor(int id) {
		boolean result;
		try {
			result=doctorDao.deleteDoctor(id);
			if(result)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}

