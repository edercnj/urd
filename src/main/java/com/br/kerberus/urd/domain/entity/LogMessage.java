package com.br.kerberus.urd.domain.entity;

import java.net.URI;
import java.util.Date;
import java.util.Objects;

public final class LogMessage {

	private String serverName;
	private String serviceName;
	private Date dateTime;
	private int errorCord;

	private String developerMessage;
	private String userMessage;
	private URI moreInfo;

	public LogMessage(String serverName, String serviceName, Date dateTime, int errorCord, String developerMessage, String userMessage,
			URI moreInfo) {
		setServerName(serverName);
		setServiceName(serviceName);
		setDateTime(dateTime);
		setErrorCord(errorCord);
		setDeveloperMessage(developerMessage);
		setUserMessage(userMessage);
		setMoreInfo(moreInfo);
	}

	public LogMessage(String serverName, String serviceName, Date dateTime, int errorCord, String developerMessage, String userMessage) {
		setServerName(serverName);
		setServiceName(serviceName);
		setDateTime(dateTime);
		setErrorCord(errorCord);
		setDeveloperMessage(developerMessage);
		setUserMessage(userMessage);
	}

	public String getServerName() { return serverName; }

	public void setServerName(String serverName) { this.serverName = serverName; }

	public String getServiceName() { return serviceName; }

	public void setServiceName(String serviceName) { this.serviceName = serviceName; }

	public Date getDateTime() { return dateTime; }

	public void setDateTime(Date dateTime) { this.dateTime = dateTime; }

	public int getErrorCord() { return errorCord; }

	public void setErrorCord(int errorCord) { this.errorCord = errorCord; }

	public String getDeveloperMessage() { return developerMessage; }

	public void setDeveloperMessage(String developerMessage) { this.developerMessage = developerMessage; }

	public String getUserMessage() { return userMessage; }

	public void setUserMessage(String userMessage) { this.userMessage = userMessage; }

	public URI getMoreInfo() { return moreInfo; }

	public void setMoreInfo(URI moreInfo) { this.moreInfo = moreInfo; }

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, developerMessage, errorCord, moreInfo, serverName, serviceName, userMessage);
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
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(developerMessage, other.developerMessage) && errorCord == other.errorCord
				&& Objects.equals(moreInfo, other.moreInfo) && Objects.equals(serverName, other.serverName) && Objects.equals(serviceName,
						other.serviceName) && Objects.equals(userMessage, other.userMessage);
	}

	@Override
	public String toString() {
		return String.format(
				" # serverName:%s # serviceName:%s1 # dateTime:%s2 # errorCord:%s3 # developerMessage:%s4 # userMessage:%s5 # moreInfo:%s6",
				getServerName(), getServiceName(), getDateTime(), getErrorCord(), getDeveloperMessage(), getUserMessage(), getMoreInfo());
	}

}
