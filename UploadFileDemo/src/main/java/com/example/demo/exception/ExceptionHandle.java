package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class ExceptionHandle {


	  @ExceptionHandler(MaxUploadSizeExceededException.class)
	  public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
		    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!");
		  }
	  @ExceptionHandler(ExceptionCustom.class)
	  public ResponseEntity<String> handleExceptionCustom(ExceptionCustom exc) {
		    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(exc.getMessage());
		  }

	  @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleUnwantedException(Exception e) {
	        // Log lỗi ra và ẩn đi message thực sự (xem phần 3.2)
	        e.printStackTrace();  // Thực tế người ta dùng logger
	        return ResponseEntity.status(500).body("Unknow error");
	    }}
