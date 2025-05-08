package com.pro.exception;

public class InvalidTransactionException extends Exception{

	public InvalidTransactionException() {
		super("올바른 값이 아닙니다. ");
	}
	public InvalidTransactionException(String message) {
		super(message);
	}

}
