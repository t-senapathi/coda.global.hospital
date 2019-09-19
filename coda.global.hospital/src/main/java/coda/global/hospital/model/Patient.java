package coda.global.hospital.model;

public class Patient extends User {
	private int pkPatientId;
	private int fkUserId;
	private int patientHeight;
	private int patientWeight;
	private String street;
	private String city;
	private String doorNo;
	private String bloodGroup;
	
	public int getPkPatientId() {
		return pkPatientId;
	}
	public void setPkPatientId(int pkPatientId) {
		this.pkPatientId = pkPatientId;
	}
	public int getFkUserId() {
		return fkUserId;
	}
	public void setFkUserId(int fkUserId) {
		this.fkUserId = fkUserId;
	}
	public int getPatientHeight() {
		return patientHeight;
	}
	public void setPatientHeight(int patientHeight) {
		this.patientHeight = patientHeight;
	}
	public int getPatientWeight() {
		return patientWeight;
	}
	public void setPatientWeight(int patientWeight) {
		this.patientWeight = patientWeight;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	@Override
	public String toString() {
		return "Patient [pkPatientId=" + pkPatientId + ", fkUserId=" + fkUserId + ", patientHeight=" + patientHeight
				+ ", patientWeight=" + patientWeight + ", street=" + street + ", city=" + city + ", doorNo=" + doorNo
				+ ", bloodGroup=" + bloodGroup + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ",  age=" + age + ", pkUserId=" + pkUserId
				+ ", fkRoleId=" + fkRoleId + "]";
	}
	
	
	
}
