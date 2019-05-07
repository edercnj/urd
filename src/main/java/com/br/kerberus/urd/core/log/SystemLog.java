package com.br.kerberus.urd.core.log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

public class SystemLog {

	private String serverName;
	@Value("${spring.application.name}")
	private String serviceName;
	private Date dateTime;
	private int errorCord;
	private String debugMessage;
	private String userMessage;
	private String moreInfo;
	private HttpStatus httpStatus;

	public SystemLog(String serverName, String serviceName, int errorCord, String debugMessage, String userMessage, String moreInfo,
			HttpStatus httpStatus) {
		setDateTime();
		this.serverName = serverName;
		this.serviceName = serviceName;
		this.errorCord = errorCord;
		this.debugMessage = debugMessage;
		this.userMessage = userMessage;
		this.moreInfo = moreInfo;
		this.httpStatus = httpStatus;
	}

	public SystemLog(int errorCord, String debugMessage, String userMessage, String moreInfo, HttpStatus httpStatus) {
		setDateTime();
		setServerName();
		this.errorCord = errorCord;
		this.debugMessage = debugMessage;
		this.userMessage = userMessage;
		this.moreInfo = moreInfo;
		this.httpStatus = httpStatus;
	}

	private void setServerName() {
		try {
			this.serverName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			this.serverName = "Unknown";
		}
	}

	private void setDateTime() {
		this.dateTime = new Date();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, debugMessage, errorCord, httpStatus, moreInfo, serverName, serviceName, userMessage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemLog other = (SystemLog) obj;
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(debugMessage, other.debugMessage) && errorCord == other.errorCord
				&& httpStatus == other.httpStatus && Objects.equals(moreInfo, other.moreInfo) && Objects.equals(serverName, other.serverName)
				&& Objects.equals(serviceName, other.serviceName) && Objects.equals(userMessage, other.userMessage);
	}

	public Date getDateTime() { return dateTime; }

	public String getServerName() { return serverName; }

	public String getServiceName() { return serviceName; }

	public int getErrorCord() { return errorCord; }

	public String getDebugMessage() { return debugMessage; }

	public String getUserMessage() { return userMessage; }

	public String getMoreInfo() { return moreInfo; }

	public HttpStatus getHttpStatus() { return httpStatus; }

	@Override
	public String toString() {
		return String.format(
				hashCode()
						+ " | serverName:%s | serviceName:%s1 | dateTime:%s2 | errorCord:%s3 | debugMessage:%s4 | userMessage:%s5 | moreInfo:%s6",
				getServerName(), getServiceName(), getDateTime(), getErrorCord(), getDebugMessage(), getUserMessage(), getMoreInfo());
	}
}