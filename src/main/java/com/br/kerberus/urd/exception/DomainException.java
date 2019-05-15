package com.br.kerberus.urd.exception;

import com.br.kerberus.urd.resource.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public abstract class DomainException extends Exception {

	private HttpStatus httpStatus;
	private String debugMessage;
	private String userMessage;
	private int errorCod;
	private String moreInfo;

	DomainException(HttpStatus httpStatus, String debugMessage, String userMessage, int errorCod, String moreInfo) {
		super("debugMessage");
		setHttpStatus(httpStatus);
		setDebugMessage(debugMessage);
		setUserMessage(userMessage);
		setErrorCod(errorCod);
		setMoreInfo(moreInfo);
	}

	public HttpStatus getHttpStatus() { return httpStatus; }

	private void setHttpStatus(HttpStatus httpStatus) { this.httpStatus = httpStatus; }

	public String getDebugMessage() { return debugMessage; }

	private void setDebugMessage(String debugMessage) { this.debugMessage = debugMessage; }

	public String getUserMessage() { return userMessage; }

	private void setUserMessage(String userMessage) { this.userMessage = userMessage; }

	public int getErrorCod() { return errorCod; }

	private void setErrorCod(int errorCod) { this.errorCod = errorCod; }

	public String getMoreInfo() { return moreInfo; }

	private void setMoreInfo(String moreInfo) { this.moreInfo = moreInfo; }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ErrorResponse))
			return false;
		ErrorResponse that = (ErrorResponse) o;
		return getErrorCod() == that.getErrorCod() && getHttpStatus() == that.getHttpStatus()
				&& getDebugMessage().equals(that.getDebugMessage()) && getUserMessage().equals(that.getUserMessage())
				&& Objects.equals(getMoreInfo(), that.getMoreInfo());
	}

	@Override
	public int hashCode() { return Objects.hash(getHttpStatus(), getDebugMessage(), getUserMessage(), getErrorCod(), getMoreInfo()); }

	@Override
	public String toString() {
		return "ErrorResponse{" + "httpStatus:" + httpStatus + ", debugMessage:'" + debugMessage + '\'' + ", userMessage:'" + userMessage
				+ '\'' + ", errorCod:" + errorCod + ", moreInfo:" + moreInfo + '}';
	}
}