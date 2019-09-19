package coda.global.hospital.model;

public class User {
	String username;
	String password;
	String firstName;
	String lastName;
	int age;
	int pkUserId;
	int fkRoleId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPkUserId() {
		return pkUserId;
	}

	public void setPkUserId(int pkUserId) {
		this.pkUserId = pkUserId;
	}

	public int getFkRoleId() {
		return fkRoleId;
	}

	public void setFkRoleId(int fkRoleId) {
		this.fkRoleId = fkRoleId;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", age=" + age + ", pkUserId=" + pkUserId + ", fkRoleId=" + fkRoleId + "]";
	}

}
