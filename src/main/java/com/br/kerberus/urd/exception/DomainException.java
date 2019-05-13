package com.br.kerberus.urd.exception;

import com.br.kerberus.urd.resource.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public abstract class DomainException extends Exception {

	private HttpStatus httpStatus;
	private String developerMessage;
	private String userMessage;
	private int erroCord;
	private String moreInfo;

	DomainException(HttpStatus httpStatus, String developerMessage, String userMessage, int erroCord) {
		super("developerMessage");
		setHttpStatus(httpStatus);
		setDeveloperMessage(developerMessage);
		setUserMessage(userMessage);
		setErroCord(erroCord);
	}

	DomainException(HttpStatus httpStatus, String developerMessage, String userMessage, int erroCord, String moreInfo) {
		super("developerMessage");
		setHttpStatus(httpStatus);
		setDeveloperMessage(developerMessage);
		setUserMessage(userMessage);
		setErroCord(erroCord);
		setMoreInfo(moreInfo);
	}

	public HttpStatus getHttpStatus() { return httpStatus; }

	private void setHttpStatus(HttpStatus httpStatus) { this.httpStatus = httpStatus; }

	public String getDeveloperMessage() { return developerMessage; }

	private void setDeveloperMessage(String developerMessage) { this.developerMessage = developerMessage; }

	public String getUserMessage() { return userMessage; }

	private void setUserMessage(String userMessage) { this.userMessage = userMessage; }

	public int getErroCord() { return erroCord; }

	private void setErroCord(int erroCord) { this.erroCord = erroCord; }

	public String getMoreInfo() { return moreInfo; }

	private void setMoreInfo(String moreInfo) { this.moreInfo = moreInfo; }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ErrorResponse))
			return false;
		ErrorResponse that = (ErrorResponse) o;
		return getErroCord() == that.getErroCord() && getHttpStatus() == that.getHttpStatus()
				&& getDeveloperMessage().equals(that.getDebugMessage()) && getUserMessage().equals(that.getUserMessage())
				&& Objects.equals(getMoreInfo(), that.getMoreInfo());
	}

	@Override
	public int hashCode() { return Objects.hash(getHttpStatus(), getDeveloperMessage(), getUserMessage(), getErroCord(), getMoreInfo()); }

	@Override
	public String toString() {
		return "ErrorResponse{" + "httpStatus:" + httpStatus + ", developerMessage:'" + developerMessage + '\'' + ", userMessage:'" + userMessage
				+ '\'' + ", erroCord:" + erroCord + ", moreInfo:" + moreInfo + '}';
	}
}