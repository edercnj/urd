package com.br.kerberus.urd.exception;

import com.br.kerberus.urd.entity.LogError;
import org.springframework.http.HttpStatus;

public class NoManagedException extends DomainException {
    public NoManagedException(Exception e) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "Internal Server Error", 666,
                LogError.INTERNAL_SERVER_ERROR.getError().getMoreInfo());
    }
}
