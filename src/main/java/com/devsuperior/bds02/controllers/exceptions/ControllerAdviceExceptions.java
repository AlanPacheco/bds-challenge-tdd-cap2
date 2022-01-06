package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerAdviceExceptions {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> ResourceNotFound(ResourceNotFoundException exception, HttpServletRequest request){
		StandardError standardError = new StandardError();
		HttpStatus status = HttpStatus.NOT_FOUND;
		standardError.setTimestamp(Instant.now());
		standardError.setStatus(status.value());
		standardError.setError("Resource Not Found");
		standardError.setMessage(exception.getMessage());
		standardError.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> DatabaseViolation(DatabaseException exception, HttpServletRequest request){
		StandardError standardError = new StandardError();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		standardError.setTimestamp(Instant.now());
		standardError.setStatus(status.value());
		standardError.setError("Database exception!!");
		standardError.setMessage(exception.getMessage());
		standardError.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}
	
}
