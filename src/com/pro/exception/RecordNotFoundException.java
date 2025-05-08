package com.pro.exception;

public class RecordNotFoundException extends Exception{

	public RecordNotFoundException() {
		super("찾는 정보가 없습니다. ");
	}
	public RecordNotFoundException(String message) {
		super(message);
	}

}
