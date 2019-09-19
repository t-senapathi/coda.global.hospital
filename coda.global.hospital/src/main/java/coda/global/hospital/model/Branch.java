package coda.global.hospital.model;

public class Branch {
	private int fkHospitalId,pkBranchId;
	private String branchLocation;
	public int getFkHospitalId() {
		return fkHospitalId;
	}
	public void setFkHospitalId(int fkHospitalId) {
		this.fkHospitalId = fkHospitalId;
	}
	public int getPkBranchId() {
		return pkBranchId;
	}
	public void setPkBranchId(int pkBranchId) {
		this.pkBranchId = pkBranchId;
	}
	public String getBranchLocation() {
		return branchLocation;
	}
	public void setBranchLocation(String branchLocation) {
		this.branchLocation = branchLocation;
	}
	@Override
	public String toString() {
		return "Branch [fkHospitalId=" + fkHospitalId + ", pkBranchId=" + pkBranchId + ", branchLocation="
				+ branchLocation + "]";
	}
	
}
