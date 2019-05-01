package com.br.kerberus.urd.exception;

import org.springframework.http.HttpStatus;

import java.net.URI;

public class UrdException extends DomainException {

	public UrdException(HttpStatus httpStatus, String developerMessage, String userMessage, int erroCord) {
		super(httpStatus, developerMessage, userMessage, erroCord);
	}

	public UrdException(HttpStatus httpStatus, String developerMessage, String userMessage, int erroCord, String moreInfo) {
		super(httpStatus, developerMessage, userMessage, erroCord, moreInfo);
	}
}
