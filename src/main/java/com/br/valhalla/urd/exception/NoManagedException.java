package com.br.valhalla.urd.exception;

public class NoManagedException extends DomainException {
    public NoManagedException(Exception e, String moreInfo) {
        super(e.getMessage(), "Internal Server Error", 1, moreInfo);
    }
}
