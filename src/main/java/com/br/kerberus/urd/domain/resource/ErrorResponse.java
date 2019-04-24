package com.br.kerberus.urd.domain.resource;

import java.net.URI;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.br.kerberus.urd.exception.DomainException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String developerMessage;
    private String userMessage;
    private int erroCord;
    private URI moreInfo;

    public ErrorResponse(HttpStatus httpStatus, String developerMessage, String userMessage, int erroCord) {
        this.httpStatus = httpStatus;
        this.developerMessage = developerMessage;
        this.userMessage = userMessage;
        this.erroCord = erroCord;
    }

    public ErrorResponse(HttpStatus httpStatus, String developerMessage, String userMessage, int erroCord, URI moreInfo) {
        setHttpStatus(httpStatus);
        setDeveloperMessage(developerMessage);
        setUserMessage(userMessage);
        setErroCord(erroCord);
        setMoreInfo(moreInfo);
    }

    public ErrorResponse(DomainException e) {
        setHttpStatus(e.getHttpStatus());
        setDeveloperMessage(e.getDeveloperMessage());
        setUserMessage(e.getDeveloperMessage());
        setErroCord(e.getErroCord());
        setMoreInfo(e.getMoreInfo());
    }

    public HttpStatus getHttpStatus() { return httpStatus; }

    private void setHttpStatus(HttpStatus httpStatus) { this.httpStatus = httpStatus; }

    public String getDeveloperMessage() { return developerMessage; }

    private void setDeveloperMessage(String developerMessage) { this.developerMessage = developerMessage; }

    public String getUserMessage() { return userMessage; }

    private void setUserMessage(String userMessage) { this.userMessage = userMessage; }

    public int getErroCord() { return erroCord; }

    private void setErroCord(int erroCord) { this.erroCord = erroCord; }

    public URI getMoreInfo() { return moreInfo; }

    private void setMoreInfo(URI moreInfo) { this.moreInfo = moreInfo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorResponse)) return false;
        ErrorResponse that = (ErrorResponse) o;
        return getErroCord() == that.getErroCord() &&
                getHttpStatus() == that.getHttpStatus() &&
                getDeveloperMessage().equals(that.getDeveloperMessage()) &&
                getUserMessage().equals(that.getUserMessage()) &&
                Objects.equals(getMoreInfo(), that.getMoreInfo());
    }

    @Override
    public int hashCode() { return Objects.hash(getHttpStatus(), getDeveloperMessage(), getUserMessage(), getErroCord(), getMoreInfo()); }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "httpStatus=" + httpStatus +
                ", developerMessage='" + developerMessage + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", erroCord=" + erroCord +
                ", moreInfo=" + moreInfo +
                '}';
    }
}