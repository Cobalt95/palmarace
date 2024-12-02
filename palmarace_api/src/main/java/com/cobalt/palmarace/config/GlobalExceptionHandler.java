package com.cobalt.palmarace.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cobalt.palmarace.service.exc.AthleteNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AthleteNotFoundException.class)
	public ResponseEntity<String> handleAthleteNotFound(AthleteNotFoundException anfe) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(anfe.getMessage());
	}

}
