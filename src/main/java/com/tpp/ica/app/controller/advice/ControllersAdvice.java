package com.tpp.ica.app.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tpp.ica.app.exception.InValidJsonInputMessage;

@ControllerAdvice
public class ControllersAdvice {
	
	  @ExceptionHandler(value = {JsonMappingException.class, JsonProcessingException.class,InValidJsonInputMessage.class })
	  public ResponseEntity<ErrorMessage> invalidInputExceptions(Throwable ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(ex);
	    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	  }
	  
	  @ExceptionHandler(value = {Exception.class })
	  public ResponseEntity<ErrorMessage> allExceptions(Throwable ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(ex);
	    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
