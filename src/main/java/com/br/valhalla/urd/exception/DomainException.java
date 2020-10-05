package com.br.valhalla.urd.exception;

public abstract class DomainException extends Exception {

    private final String debugMessage;
    private final String userMessage;
    private final int errorCod;
    private final String moreInfo;

    public DomainException(String message, String debugMessage, String userMessage, int errorCod, String moreInfo) {
        super(message);
        this.debugMessage = debugMessage;
        this.userMessage = userMessage;
        this.errorCod = errorCod;
        this.moreInfo = moreInfo;
    }

    public String getDebugMessage() { return debugMessage; }

    public String getUserMessage() { return userMessage; }

    public int getErrorCod() { return errorCod; }

    public String getMoreInfo() { return moreInfo; }

    @Override
    public String toString() {
        return "DomainException{" +
                "debugMessage='" + getDebugMessage() + '\'' +
                ", userMessage='" + getUserMessage() + '\'' +
                ", errorCod=" + getErrorCod() +
                ", moreInfo='" + getMoreInfo() + '\'' +
                '}';
    }
}