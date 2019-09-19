package coda.global.hospital.model;

public class Doctor extends User {
	private int pkDoctorId;
	private int fkUserId;
	private int experience;
	private String specialisation;

	public int getPkDoctorId() {
		return pkDoctorId;
	}

	public void setPkDoctorId(int pkDoctorId) {
		this.pkDoctorId = pkDoctorId;
	}

	public int getFkUserId() {
		return fkUserId;
	}

	public void setFkUserId(int fkUserId) {
		this.fkUserId = fkUserId;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	@Override
	public String toString() {
		return "Doctor [pkDoctorId=" + pkDoctorId + ", fkUserId=" + fkUserId + ", experience=" + experience
				+ ", specialisation=" + specialisation + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", pkUserId=" + pkUserId
				+ ", fkRoleId=" + fkRoleId + "]";
	}

}
