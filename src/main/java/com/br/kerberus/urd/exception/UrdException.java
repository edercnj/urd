package com.br.kerberus.urd.exception;

import com.br.kerberus.urd.log.LogError;

public class UrdException extends DomainException {

	public UrdException(LogError logError) {
		super(logError.getError().getHttpStatus(), logError.getError().getDebugMessage(),
				logError.getError().getUserMessage(), logError.getError().getErrorCod(), logError.getError().getMoreInfo());
	}
}
