package com.br.kerberus.urd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.br.kerberus.urd.domain.resource.ErrorResponse;
import com.br.kerberus.urd.exception.UrdException;

@ControllerAdvice
@RestController
public class ExceptionControllerAdivice {

	private Logger log;

	public ExceptionControllerAdivice() { this.setLog(LoggerFactory.getLogger(ExceptionControllerAdivice.class)); }

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> exceptionHandlerException(Exception e) {

		ErrorResponse responseError = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage(),
				"Bad Request", 666);

		return ResponseEntity.status(responseError.getHttpStatus()).body(responseError);
	}

	@ExceptionHandler(value = UrdException.class)
	public ResponseEntity<ErrorResponse> exceptionHandlerUrdException(UrdException e) {

		ErrorResponse responseError = new ErrorResponse(e);

		return ResponseEntity.status(responseError.getHttpStatus()).body(responseError);
	}

	public Logger getLog() { return log; }

	public void setLog(Logger log) { this.log = log; }
}