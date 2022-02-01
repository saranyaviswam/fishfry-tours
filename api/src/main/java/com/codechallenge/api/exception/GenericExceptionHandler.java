package com.codechallenge.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler{

	/**
	 * Handle record not found errors.
	 * @param e exception
	 * @return REST error response
	 */
	@ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NoDataFoundException e) {
				
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(e.getMessage());
		
		return new ResponseEntity<>(errorResponse,  HttpStatus.NOT_FOUND);
    }
	
}
