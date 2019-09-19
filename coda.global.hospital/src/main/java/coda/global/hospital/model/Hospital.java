package coda.global.hospital.model;

public class Hospital {
	int pkHospitalId;
	String hospitalName;
	public int getPkHospitalId() {
		return pkHospitalId;
	}
	public void setPkHospitalId(int pkHospitalId) {
		this.pkHospitalId = pkHospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	@Override
	public String toString() {
		return "Hospital [pkHospitalId=" + pkHospitalId + ", hospitalName=" + hospitalName + "]";
	}
	
	
}
