package coda.global.hospital.patientdaotest;

import static org.testng.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import coda.global.hospital.constants.PatientTestConstants;
import coda.global.hospital.dao.PatientDao;
import coda.global.hospital.model.Patient;

public class PatientDaoTest {
	Logger logger = LoggerFactory.getLogger(PatientDaoTest.class);

	@Test
	public void readPatientTestValidId() {
		try {
			PatientDao patientDao = new PatientDao();
			Patient patient = patientDao.readPatient(PatientTestConstants.validId);
			assertEquals(patient.getFirstName(), PatientTestConstants.validfirstname);
		} catch (Exception error) {
			logger.error(error.getMessage());
		}
	}
	@Test
	public void readPatientTestInvalidId() {
		try {
			PatientDao patientDao = new PatientDao();
			Patient patient = patientDao.readPatient(PatientTestConstants.invalidId);
			assertEquals(patient, null);
		} catch (Exception error) {
			logger.error(error.getMessage());
		}
	}
	@Test
	public void createPatientTestRepeatedUsername() {
		try {
			PatientDao patientDao = new PatientDao();
			patientDao.createPatient(PatientTestConstants.getPatient());
		} catch (Exception error) {
			assertEquals(error.getClass().getName(),"java.sql.SQLException");
		}
	}
	@Test
	public void createPatientTestValidUsername() {
		try {
			PatientDao patientDao = new PatientDao();
			Patient patient=patientDao.createPatient(PatientTestConstants.getPatient());
			assertEquals(patient.getUsername(),PatientTestConstants.newusername);
		} catch (Exception error) {
			
		}
	}
}
