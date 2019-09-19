package coda.global.hospital.constants;

import coda.global.hospital.model.Patient;

public class PatientTestConstants {
	public static final int validId=1;
	public static final int invalidId=99;
	public static final String validfirstname="senapathi";
	
	public static final String newusername="senapathi";
	public static final String newpassword="password";
	public static final String newdoorno="77/2";
	public static final String newstreet="T Nagar";
	public static final String newcity="Chennai";
	public static final String newbloodgroup="O+ve";
	public static final String newfirstname="senapathi";
	public static final String newlastname="thirumurugan";
	public static final int newage= 75;
	public static final int newroleid= 1;
	public static final int newheight= 6;
	public static final int newweight= 55;
	
	
	public static Patient getPatient() {
		Patient patientTest = new Patient();
		patientTest.setUsername(newusername);
		patientTest.setPassword(newpassword);
		patientTest.setAge(newage);
		patientTest.setFirstName(newfirstname);
		patientTest.setLastName(newlastname);
		patientTest.setDoorNo(newdoorno);
		patientTest.setStreet(newstreet);
		patientTest.setCity(newcity);
		patientTest.setBloodGroup(newbloodgroup);
		patientTest.setPatientHeight(newheight);
		patientTest.setPatientWeight(newweight);
		patientTest.setFkRoleId(newroleid);
		return patientTest;
	}
}
