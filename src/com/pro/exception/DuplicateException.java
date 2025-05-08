package com.pro.exception;

public class DuplicateException extends Exception{

	public DuplicateException() {
		super("중복된 값입니다.");
	}
	public DuplicateException(String message) {
		super(message);
	}
	

}
