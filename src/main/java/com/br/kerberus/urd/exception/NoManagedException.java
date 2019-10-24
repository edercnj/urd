package com.br.kerberus.urd.exception;

public class NoManagedException extends DomainException {
    public NoManagedException(Exception e , String moreInfo) {
        super(e.getMessage(), "Internal Server Error", 0001,
                moreInfo);
    }
}
