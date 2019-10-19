package com.shinhan.solo.user;

public class User {
	private String name;
	private String uuid;
	private String idNumber;
	private String password;
	
	public User(String name, String uuid, String idNumber, String password) {
		super();
		this.name = name;
		this.uuid = uuid;
		this.idNumber = idNumber;
		this.password = password;
	}

	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
