package com.br.kerberus.urd.entity;

import java.util.Date;
import java.util.Objects;

import com.br.kerberus.urd.log.SystemLog;

public final class LogMessage {

	private String serverName;
	private String serviceName;
	private Date dateTime;
	private int errorCord;

	private String debugMessage;
	private String userMessage;
	private String moreInfo;

	public LogMessage(SystemLog log) {
		setServerName(log.getServerName());
		setServiceName(log.getServiceName());
		setDateTime(log.getDateTime());
		setErrorCord(log.getErrorCord());
		setDebugMessage(log.getDebugMessage());
		setUserMessage(log.getUserMessage());
		setMoreInfo(log.getMoreInfo());
	}


	public String getServerName() { return serverName; }

	public void setServerName(String serverName) { this.serverName = serverName; }

	public String getServiceName() { return serviceName; }

	public void setServiceName(String serviceName) { this.serviceName = serviceName; }

	public Date getDateTime() { return dateTime; }

	public void setDateTime(Date dateTime) { this.dateTime = dateTime; }

	public int getErrorCord() { return errorCord; }

	public void setErrorCord(int errorCord) { this.errorCord = errorCord; }

	public String getDebugMessage() { return debugMessage; }

	public void setDebugMessage(String debugMessage) { this.debugMessage = debugMessage; }

	public String getUserMessage() { return userMessage; }

	public void setUserMessage(String userMessage) { this.userMessage = userMessage; }

	public String getMoreInfo() { return moreInfo; }

	public void setMoreInfo(String moreInfo) { this.moreInfo = moreInfo; }

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, debugMessage, errorCord, moreInfo, serverName, serviceName, userMessage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogMessage other = (LogMessage) obj;
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(debugMessage, other.debugMessage) && errorCord == other.errorCord
				&& Objects.equals(moreInfo, other.moreInfo) && Objects.equals(serverName, other.serverName) && Objects.equals(serviceName,
						other.serviceName) && Objects.equals(userMessage, other.userMessage);
	}

	@Override
	public String toString() {
		return String.format(
				" # serverName:%s # serviceName:%s1 # dateTime:%s2 # errorCord:%s3 # debugMessage:%s4 # userMessage:%s5 # moreInfo:%s6",
				getServerName(), getServiceName(), getDateTime(), getErrorCord(), getDebugMessage(), getUserMessage(), getMoreInfo());
	}

}
