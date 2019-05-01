package com.br.kerberus.urd.domain.resource;

import com.br.kerberus.urd.exception.DomainException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@JsonInclude(value = Include.NON_NULL)
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String debugMessage;
    private String userMessage;
    private int erroCord;
    private String moreInfo;

    public ErrorResponse(HttpStatus httpStatus, String debugMessage, String userMessage, int erroCord) {
        this.httpStatus = httpStatus;
        this.debugMessage = debugMessage;
        this.userMessage = userMessage;
        this.erroCord = erroCord;
    }

    public ErrorResponse(HttpStatus httpStatus, String debugMessage, String userMessage, int erroCord, String moreInfo) {
        setHttpStatus(httpStatus);
        setDebugMessage(debugMessage);
        setUserMessage(userMessage);
        setErroCord(erroCord);
        setMoreInfo(moreInfo);
    }

    public ErrorResponse(DomainException e) {
        setHttpStatus(e.getHttpStatus());
        setDebugMessage(e.getDeveloperMessage());
        setUserMessage(e.getDeveloperMessage());
        setErroCord(e.getErroCord());
        setMoreInfo(e.getMoreInfo());
    }

    public HttpStatus getHttpStatus() { return httpStatus; }

    private void setHttpStatus(HttpStatus httpStatus) { this.httpStatus = httpStatus; }

    public String getDebugMessage() { return debugMessage; }

    private void setDebugMessage(String debugMessage) { this.debugMessage = debugMessage; }

    public String getUserMessage() { return userMessage; }

    private void setUserMessage(String userMessage) { this.userMessage = userMessage; }

    public int getErroCord() { return erroCord; }

    private void setErroCord(int erroCord) { this.erroCord = erroCord; }

    public String getMoreInfo() { return moreInfo; }

    private void setMoreInfo(String moreInfo) { this.moreInfo = moreInfo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorResponse)) return false;
        ErrorResponse that = (ErrorResponse) o;
        return getErroCord() == that.getErroCord() &&
                getHttpStatus() == that.getHttpStatus() &&
                getDebugMessage().equals(that.getDebugMessage()) &&
                getUserMessage().equals(that.getUserMessage()) &&
                Objects.equals(getMoreInfo(), that.getMoreInfo());
    }

    @Override
    public int hashCode() { return Objects.hash(getHttpStatus(), getDebugMessage(), getUserMessage(), getErroCord(), getMoreInfo()); }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "httpStatus=" + httpStatus +
                ", debugMessage='" + debugMessage + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", erroCord=" + erroCord +
                ", moreInfo=" + moreInfo +
                '}';
    }
}
