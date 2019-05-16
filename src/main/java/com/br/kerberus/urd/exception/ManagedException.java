package com.br.kerberus.urd.exception;

import com.br.kerberus.urd.log.LogError;

public class ManagedException extends DomainException {

	public ManagedException(LogError logError) {
		super(logError.getError().getHttpStatus(), logError.getError().getDebugMessage(),
				logError.getError().getUserMessage(), logError.getError().getErrorCod(), logError.getError().getMoreInfo());
	}
}
