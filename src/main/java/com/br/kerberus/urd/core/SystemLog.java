package com.br.kerberus.urd.core;

import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Objects;

public final class SystemLog {

	private String serverName;
	@Value("$spring.application.name")
	private String serviceName;
	private Date dateTime;
	private int errorCord;
	private String debugMessage;
	private String userMessage;
	private String moreInfo;

	public SystemLog(int errorCord, String debugMessage, String userMessage, String moreInfo) {
		setServerName();
		setDateTime();
		this.errorCord = errorCord;
		this.debugMessage = debugMessage;
		this.userMessage = userMessage;
		this.moreInfo = moreInfo;
	}

	private void setServerName() {
		try {
			this.serverName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			this.serverName = "Unknown";
		}
	}

	private void setDateTime() { this.dateTime = new Date(); }

	public Date getDateTime() { return dateTime; }

	public String getServerName() { return serverName; }

	public String getServiceName() { return serviceName; }

	public int getErrorCord() { return errorCord; }

	public String getDebugMessage() { return debugMessage; }

	public String getUserMessage() { return userMessage; }

	public String getMoreInfo() { return moreInfo; }

	@Override
	public int hashCode() { return Objects.hash(getServerName(), getServiceName(), getDateTime(), getErrorCord()); }

	@Override
	public String toString() {
		return String.format(
				hashCode()
						+ " | serverName:%s | serviceName:%s1 | dateTime:%s2 | errorCord:%s3 | debugMessage:%s4 | userMessage:%s5 | moreInfo:%s6",
				getServerName(), getServiceName(), getDateTime(), getErrorCord(), getDebugMessage(), getUserMessage(), getMoreInfo());
	}
}