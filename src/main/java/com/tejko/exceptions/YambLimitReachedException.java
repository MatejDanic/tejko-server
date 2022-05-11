package com.tejko.exceptions;

public class YambLimitReachedException extends RuntimeException {

	public YambLimitReachedException(String message) {
		super(message);
	}

}