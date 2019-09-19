package coda.global.hospital;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import coda.global.hospital.constants.ErrorConstants;
import coda.global.hospital.constants.InputConstants;
import coda.global.hospital.constants.MenuConstants;
import coda.global.hospital.constants.OutputConstants;
import coda.global.hospital.exception.InvalidAgeException;
import coda.global.hospital.exception.InvalidNameException;
import coda.global.hospital.model.Doctor;
import coda.global.hospital.model.Patient;
import coda.global.hospital.model.User;
import coda.global.hospital.services.AuthenticationService;
import coda.global.hospital.services.DoctorService;
import coda.global.hospital.services.PatientService;

public class ApplicationDriver {
	Scanner scan = new Scanner(System.in);
	Logger LOGGER = LoggerFactory.getLogger(ApplicationDriver.class);
	ResourceBundle errorConstant = ResourceBundle.getBundle("ErrorMessages");
	ResourceBundle inputConstant = ResourceBundle.getBundle("InputMessages");
	ResourceBundle outputConstant = ResourceBundle.getBundle("OutputMessages");
	ResourceBundle menuConstant = ResourceBundle.getBundle("MenuMessages");
	int input;
	AuthenticationService authenticationService = new AuthenticationService();
	User userDetail = null;

	/**
	 * login- gets username and password from user
	 * 
	 */
	public void login() {
		String username, password;
		try {
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_010));
			username = scan.nextLine();
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_011));
			password = scan.nextLine();
			userDetail = authenticationService.validate(username, password);
			if (userDetail != null) {
				switch (userDetail.getFkRoleId()) {
				case 1: {
					patientDriver();
					break;
				}
				case 2: {
					doctorDriver();
					break;
				}
				case 3: {
					adminDriver();
					break;
				}
				}
			}
		} finally {
			scan.close();
		}
	}

	/**
	 * inputPatient- gets patientdetails from user
	 * 
	 */
	public Patient inputPatient() {

		Patient patient = new Patient();
		int patientAge, patientHeight, patientWeight;
		String firstName, doorNo, city, street, lastName, username, password, bloodGroup;
		try {
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_010));// username
			username = scan.nextLine();
			patient.setUsername(username);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_011));// password
			password = scan.nextLine();
			patient.setPassword(password);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_002));// Patient first name
			firstName = scan.nextLine();
			if (!firstName.matches("^[a-zA-Z ]*$"))
				throw new InvalidNameException(errorConstant.getString(ErrorConstants.HM_ERROR_003));
			patient.setFirstName(firstName);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_012));// last name
			lastName = scan.nextLine();
			if (!lastName.matches("^[a-zA-Z ]*$"))
				throw new InvalidNameException(errorConstant.getString(ErrorConstants.HM_ERROR_003));
			patient.setLastName(lastName);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_003));// Patient age
			patientAge = Integer.parseInt(scan.nextLine());
			if (patientAge < 0 || patientAge > 110)
				throw new InvalidAgeException(errorConstant.getString(ErrorConstants.HM_ERROR_004));
			patient.setAge(patientAge);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_004));// Patient address
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_005));// door no
			doorNo = scan.nextLine();
			patient.setDoorNo(doorNo);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_006));// street
			street = scan.nextLine();
			patient.setStreet(street);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_007));// city
			city = scan.nextLine();
			patient.setCity(city);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_008));// Patient height
			patientHeight = Integer.parseInt(scan.nextLine());
			patient.setPatientHeight(patientHeight);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_009));// Patient Weight
			patientWeight = Integer.parseInt(scan.nextLine());
			patient.setPatientWeight(patientWeight);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_014));// Blood Group
			bloodGroup = scan.nextLine();
			patient.setBloodGroup(bloodGroup);
			patient.setFkRoleId(1);
			return patient;
		} catch (InvalidAgeException e) {
			LOGGER.error(e.getMessage());
		} catch (InvalidNameException e) {
			LOGGER.error(e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(errorConstant.getString(ErrorConstants.HM_ERROR_005));
		}
		return null;
	}

	public Doctor inputDoctor() {

		Doctor doctor = new Doctor();
		int age, experience;
		String firstName, lastName, username, password, specialisation;
		try {
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_010));// username
			username = scan.nextLine();
			doctor.setUsername(username);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_011));// password
			password = scan.nextLine();
			doctor.setPassword(password);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_002));// Doctor first name
			firstName = scan.nextLine();
			if (!firstName.matches("^[a-zA-Z ]*$"))
				throw new InvalidNameException(errorConstant.getString(ErrorConstants.HM_ERROR_003));
			doctor.setFirstName(firstName);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_012));// last name
			lastName = scan.nextLine();
			if (!lastName.matches("^[a-zA-Z ]*$"))
				throw new InvalidNameException(errorConstant.getString(ErrorConstants.HM_ERROR_003));
			doctor.setLastName(lastName);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_003));// Patient age
			age = Integer.parseInt(scan.nextLine());
			if (age < 0 || age > 110)
				throw new InvalidAgeException(errorConstant.getString(ErrorConstants.HM_ERROR_004));
			doctor.setAge(age);
			doctor.setFkRoleId(2);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_015));// specialisation
			specialisation = scan.nextLine();
			doctor.setSpecialisation(specialisation);
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_016));// experience
			experience = Integer.parseInt(scan.nextLine());
			doctor.setExperience(experience);
			return doctor;
		} catch (InvalidAgeException e) {
			LOGGER.error(e.getMessage());
		} catch (InvalidNameException e) {
			LOGGER.error(e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(errorConstant.getString(ErrorConstants.HM_ERROR_005));
		}
		return null;
	}

	public void patientDriver() {
		int choice, updateChoice;
		Patient newdata = null;
		boolean flag = true;
		PatientService patientService = new PatientService();
		while (flag) {
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_006));// read patient
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_007));// update patient
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_012));// logout
			choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case 1: {
				newdata = patientService.readPatient(userDetail.getPkUserId());
				if (newdata != null)
					LOGGER.info(newdata.toString());
				break;
			}
			case 2: {
				newdata = patientService.readPatient(userDetail.getPkUserId());
				LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_008));// update password
				LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_009));// update height
				LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_010));// update weight
				LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_011));// update Age
				updateChoice = Integer.parseInt(scan.nextLine());
				switch (updateChoice) {
				case 1: {
					LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_011));
					newdata.setPassword(scan.nextLine());
					if (patientService.updatePatient(newdata))
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_002));
					else
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_005));
					break;
				}
				case 2: {
					LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_008));
					newdata.setPatientHeight(Integer.parseInt(scan.nextLine()));
					if (patientService.updatePatient(newdata))
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_002));
					else
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_005));
					break;
				}
				case 3: {
					LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_009));
					newdata.setPatientWeight(Integer.parseInt(scan.nextLine()));
					if (patientService.updatePatient(newdata))
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_002));
					else
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_005));
					break;
				}
				case 4: {
					LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_003));
					newdata.setAge(Integer.parseInt(scan.nextLine()));
					if (patientService.updatePatient(newdata))
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_002));
					else
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_005));
					break;
				}
				}
				break;
			}
			case 3: {
				flag = false;
				break;
			}

			}
		}
	}

	public void doctorDriver() {
		int choice, updateChoice;
		Doctor newdata = null;
		boolean flag = true;
		DoctorService doctorService = new DoctorService();
		while (flag) {
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_006));// read patient
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_007));// update patient
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_012));// logout
			choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case 1: {
				newdata = doctorService.readDoctor(userDetail.getPkUserId());
				if (newdata != null)
					LOGGER.info(newdata.toString());
				break;
			}
			case 2: {
				newdata = doctorService.readDoctor(userDetail.getPkUserId());
				LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_008));// update password
				LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_013));// update specialisation
				LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_014));// update experience
				LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_011));// update Age
				updateChoice = Integer.parseInt(scan.nextLine());
				switch (updateChoice) {
				case 1: {
					LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_011));
					newdata.setPassword(scan.nextLine());
					if (doctorService.updateDoctor(newdata))
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_008));
					else
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_011));
					break;
				}
				case 2: {
					LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_015));
					newdata.setSpecialisation(scan.nextLine());
					if (doctorService.updateDoctor(newdata))
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_008));
					else
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_011));
					break;
				}
				case 3: {
					LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_016));
					newdata.setExperience(Integer.parseInt(scan.nextLine()));
					if (doctorService.updateDoctor(newdata))
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_008));
					else
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_011));
					break;
				}
				case 4: {
					LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_003));
					newdata.setAge(Integer.parseInt(scan.nextLine()));
					if (doctorService.updateDoctor(newdata))
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_008));
					else
						LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_011));
					break;
				}
				}
				break;
			}
			case 3: {
				flag = false;
				break;
			}
			}
		}
	}

	public void adminDriver() {
		int choice;
		boolean flag = true;
		while (flag) {
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_015));// manage patient
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_016));// manage doctor
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_012));// logout
			choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case 1: {
				managePatient();
				break;
			}
			case 2: {
				manageDoctor();
				break;
			}
			case 3: {
				flag = false;
				break;
			}
			}
		}
	}

	public void managePatient() {
		int choice;
		int patientId;
		Patient patient = null;
		PatientService patientService = new PatientService();
		List<Patient> patientList = null;
		LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_017));
		LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_018));
		LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_019));
		LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_020));
		LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_021));
		choice = Integer.parseInt(scan.nextLine());
		switch (choice) {
		case 1: {
			patient = inputPatient();
			if (patientService.createPatient(patient))
				LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_007));
			else
				LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_010));
			break;
		}
		case 2: {
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_017));
			patientId = Integer.parseInt(scan.nextLine());
			patient = patientService.readPatient(patientId);
			if (patient != null)
				LOGGER.info(patient.toString());
			break;
		}
		case 3: {
			patientList = patientService.readAllPatient();
			if (patientList != null) {
				for (Patient patientIterator : patientList) {
					LOGGER.info(patientIterator.toString());
					System.out.println();
				}
			}
			break;
		}
		case 4: {
			int updateChoice;
			Patient newdata = null;
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_017));
			patientId = Integer.parseInt(scan.nextLine());
			newdata = patientService.readPatient(patientId);
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_008));// update password
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_009));// update height
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_010));// update weight
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_011));// update Age
			updateChoice = Integer.parseInt(scan.nextLine());
			switch (updateChoice) {
			case 1: {
				LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_011));
				newdata.setPassword(scan.nextLine());
				if (patientService.updatePatient(newdata))
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_002));
				else
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_005));
				break;
			}
			case 2: {
				LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_008));
				newdata.setPatientHeight(Integer.parseInt(scan.nextLine()));
				if (patientService.updatePatient(newdata))
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_002));
				else
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_005));
				break;
			}
			case 3: {
				LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_009));
				newdata.setPatientWeight(Integer.parseInt(scan.nextLine()));
				if (patientService.updatePatient(newdata))
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_002));
				else
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_005));
				break;
			}
			case 4: {
				LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_003));
				newdata.setAge(Integer.parseInt(scan.nextLine()));
				if (patientService.updatePatient(newdata))
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_002));
				else
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_005));
				break;
			}
			}
			break;
		}
		case 5: {
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_017));
			patientId = Integer.parseInt(scan.nextLine());
			if (patientService.deletePatient(patientId)) {
				LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_009));
			} else {
				LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_012));
			}
			break;
		}
		}

	}

	public void manageDoctor() {
		int choice;
		int doctorId;
		Doctor doctor = null;
		DoctorService doctorService = new DoctorService();
		List<Doctor> doctorList = null;
		LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_017));
		LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_018));
		LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_019));
		LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_020));
		LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_021));
		choice = Integer.parseInt(scan.nextLine());
		switch (choice) {
		case 1: {
			doctor = inputDoctor();
			if (doctorService.createDoctor(doctor))
				LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_007));
			else
				LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_010));
			break;
		}
		case 2: {
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_017));
			doctorId = Integer.parseInt(scan.nextLine());
			doctor = doctorService.readDoctor(doctorId);
			if (doctor != null)
				LOGGER.info(doctor.toString());
			break;
		}
		case 3: {
			doctorList = doctorService.readAllDoctor();
			if (doctorList != null) {
				for (Doctor doctorIterator : doctorList) {
					LOGGER.info(doctorIterator.toString());
					System.out.println();
				}
			}
			break;
		}
		case 4: {
			int updateChoice;
			Doctor newdata = null;
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_017));
			doctorId = Integer.parseInt(scan.nextLine());
			newdata = doctorService.readDoctor(doctorId);
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_008));// update password
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_013));// update specialisation
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_014));// update experience
			LOGGER.info(menuConstant.getString(MenuConstants.HM_MENU_011));// update Age
			updateChoice = Integer.parseInt(scan.nextLine());
			switch (updateChoice) {
			case 1: {
				LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_011));
				newdata.setPassword(scan.nextLine());
				if (doctorService.updateDoctor(newdata))
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_008));
				else
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_011));
				break;
			}
			case 2: {
				LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_015));
				newdata.setSpecialisation(scan.nextLine());
				if (doctorService.updateDoctor(newdata))
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_008));
				else
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_011));
				break;
			}
			case 3: {
				LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_016));
				newdata.setExperience(Integer.parseInt(scan.nextLine()));
				if (doctorService.updateDoctor(newdata))
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_008));
				else
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_011));
				break;
			}
			case 4: {
				LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_003));
				newdata.setAge(Integer.parseInt(scan.nextLine()));
				if (doctorService.updateDoctor(newdata))
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_008));
				else
					LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_011));
				break;
			}
			}
			break;
		}
		case 5: {
			LOGGER.info(inputConstant.getString(InputConstants.HM_INPUT_017));
			doctorId = Integer.parseInt(scan.nextLine());
			if (doctorService.deleteDoctor(doctorId)) {
				LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_009));
			} else {
				LOGGER.info(outputConstant.getString(OutputConstants.HM_OUTPUT_012));
			}
			break;
		}
		}
	}

}