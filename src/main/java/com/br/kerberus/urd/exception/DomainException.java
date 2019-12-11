package com.br.kerberus.urd.exception;

public abstract class DomainException extends Exception {

    private String debugMessage;
    private String userMessage;
    private int errorCod;
    private String moreInfo;

    DomainException(String debugMessage, String userMessage, int errorCod, String moreInfo) {
        super("debugMessage");
        setDebugMessage(debugMessage);
        setUserMessage(userMessage);
        setErrorCod(errorCod);
        setMoreInfo(moreInfo);
    }

    public String getDebugMessage() { return debugMessage; }

    private void setDebugMessage(String debugMessage) { this.debugMessage = debugMessage; }

    public String getUserMessage() { return userMessage; }

    private void setUserMessage(String userMessage) { this.userMessage = userMessage; }

    public int getErrorCod() { return errorCod; }

    private void setErrorCod(int errorCod) { this.errorCod = errorCod; }

    public String getMoreInfo() { return moreInfo; }

    private void setMoreInfo(String moreInfo) { this.moreInfo = moreInfo; }

}