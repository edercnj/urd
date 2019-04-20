package com.br.kerberus.urd.domain.entity;

import com.google.gson.Gson;

import java.net.URI;
import java.net.URL;
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

	public LogMessage(String serverName, String serviceName, Date dateTime, int errorCord, String developerMessage,
			String userMessage, URI moreInfo) {
		setServerName(serverName);
		setServiceName(serviceName);
		setDateTime(dateTime);
		setErrorCord(errorCord);
		setDeveloperMessage(developerMessage);
		setUserMessage(userMessage);
		setMoreInfo(moreInfo);
	}

	public LogMessage(String serverName, String serviceName, Date dateTime, int errorCord, String developerMessage,
			String userMessage) {
		setServerName(serverName);
		setServiceName(serviceName);
		setDateTime(dateTime);
		setErrorCord(errorCord);
		setDeveloperMessage(developerMessage);
		setUserMessage(userMessage);
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getErrorCord() {
		return errorCord;
	}

	public void setErrorCord(int errorCord) {
		this.errorCord = errorCord;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public URI getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(URI moreInfo) {
		this.moreInfo = moreInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((developerMessage == null) ? 0 : developerMessage.hashCode());
		result = prime * result + errorCord;
		result = prime * result + ((moreInfo == null) ? 0 : moreInfo.hashCode());
		result = prime * result + ((serverName == null) ? 0 : serverName.hashCode());
		result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
		result = prime * result + ((userMessage == null) ? 0 : userMessage.hashCode());
		return result;
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
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (developerMessage == null) {
			if (other.developerMessage != null)
				return false;
		} else if (!developerMessage.equals(other.developerMessage))
			return false;
		if (errorCord != other.errorCord)
			return false;
		if (moreInfo == null) {
			if (other.moreInfo != null)
				return false;
		} else if (!moreInfo.equals(other.moreInfo))
			return false;
		if (serverName == null) {
			if (other.serverName != null)
				return false;
		} else if (!serverName.equals(other.serverName))
			return false;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		if (userMessage == null) {
			if (other.userMessage != null)
				return false;
		} else if (!userMessage.equals(other.userMessage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(hashCode()
				+ " | serverName:%s | serviceName:%s1 | dateTime:%s2 | errorCord:%s3 | developerMessage:%s4 | userMessage:%s5 | moreInfo:%s6",
				getServerName(), getServiceName(), getDateTime(), getErrorCord(), getDeveloperMessage(),
				getUserMessage(), getMoreInfo());
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
