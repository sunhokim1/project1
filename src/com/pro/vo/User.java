package com.pro.vo;

public abstract class User {
	String id;
	String name;
	String phoneNumber;

	public User() {}
	
	public User(String id, String name, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone number=" + phoneNumber + "]";
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void changePhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
