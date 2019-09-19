package coda.global.hospital.interfaces;

import coda.global.hospital.model.*;
import coda.global.hospital.exception.*;

import java.io.IOException;

public interface PatientServices {
	public boolean createPatient(Patient patient) throws IOException;

	public void readPatient(int id) throws PatientNotFoundException;

	public boolean updatePatient(int id, Patient newdata);

	public boolean deletePatient(int id);
}
