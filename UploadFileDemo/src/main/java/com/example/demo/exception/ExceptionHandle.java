package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class ExceptionHandle {

	/**
	 * Handle max upload exception
	 * 
	 * @param exc
	 * @return ResponseEntity<String>
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {

		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!");

	}

	/**
	 * Handle custom exception
	 * 
	 * @param exc
	 * @return ResponseEntity<String>
	 */
	@ExceptionHandler(ExceptionCustom.class)
	public ResponseEntity<String> handleExceptionCustom(ExceptionCustom exc) {

		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(exc.getMessage());

	}

	/**
	 * Handle other exception
	 * 
	 * @param e
	 * @return ResponseEntity<String>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnwantedException(Exception e) {

		return ResponseEntity.status(500).body("Unknow error");

	}
}
