package com.pro.vo.child;

import com.pro.vo.User;

public class Guest extends User{
	String nationality;

	public Guest() {
		super();
	}

	public Guest(String id, String name, String phoneNumber, String nationality) {
		super(id, name, phoneNumber);
		this.nationality = nationality;
	}

	public String getNationality() {
		return nationality;
	}

	public void changeNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return super.toString() + " nationality=" + nationality;
	}
	
}
