package com.example.demo.exception;

import org.springframework.stereotype.Component;

public class ExceptionCustom extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor with error message
	 * 
	 * @param message
	 */
	public ExceptionCustom(String message) {
		super(message);
	}
}
