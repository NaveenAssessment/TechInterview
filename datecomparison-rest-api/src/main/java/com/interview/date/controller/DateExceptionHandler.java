package com.interview.date.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author naveen
 * Exception Handler class
 */

@ControllerAdvice(basePackages = { "com.interview.date.controller" })
public class DateExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleException(final IllegalArgumentException e) {
		return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

}
