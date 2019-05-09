package com.br.kerberus.urd.controller;

import com.br.kerberus.urd.log.LogControllerAdvice;
import com.br.kerberus.urd.resource.ErrorResponse;
import com.br.kerberus.urd.exception.UrdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionControllerAdivice {

	private Logger log;

	public ExceptionControllerAdivice() { this.setLog(LoggerFactory.getLogger(ExceptionControllerAdivice.class)); }

	@ExceptionHandler
	@LogControllerAdvice
	public ResponseEntity<ErrorResponse> exceptionHandlerException(Exception e) {

		ErrorResponse responseError = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage(),
				"Could not make request. Try later.", 666);

		return ResponseEntity.status(responseError.getHttpStatus()).body(responseError);
	}

	@ExceptionHandler(value = UrdException.class)
	@LogControllerAdvice
	public ResponseEntity<ErrorResponse> exceptionHandlerUrdException(UrdException e) {

		ErrorResponse responseError = new ErrorResponse(e);

		return ResponseEntity.status(responseError.getHttpStatus()).body(responseError);
	}

	public Logger getLog() { return log; }

	public void setLog(Logger log) { this.log = log; }
}