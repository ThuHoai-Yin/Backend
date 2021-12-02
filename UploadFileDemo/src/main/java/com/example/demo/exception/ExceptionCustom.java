package com.example.demo.exception;

import org.springframework.stereotype.Component;

public class ExceptionCustom extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionCustom(String message) {
		super(message);
	}
}
