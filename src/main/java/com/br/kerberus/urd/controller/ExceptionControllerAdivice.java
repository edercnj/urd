package com.br.kerberus.urd.controller;

import com.br.kerberus.urd.exception.ManagedException;
import com.br.kerberus.urd.entity.LogError;
import com.br.kerberus.urd.entity.core.LogHttpMessages;
import com.br.kerberus.urd.resource.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	@LogHttpMessages
	public ResponseEntity<ErrorResponse> exceptionHandlerException(Exception e) {

		ErrorResponse responseError = new ErrorResponse(LogError.INTERNAL_SERVER_ERROR);

		return ResponseEntity.status(responseError.getHttpStatus()).body(responseError);
	}

	@ExceptionHandler(value = ManagedException.class)
	@LogHttpMessages
	public ResponseEntity<ErrorResponse> exceptionHandlerUrdException(ManagedException e) {

		ErrorResponse responseError = new ErrorResponse(e);

		return ResponseEntity.status(responseError.getHttpStatus()).body(responseError);
	}

	public Logger getLog() { return log; }

	public void setLog(Logger log) { this.log = log; }
}