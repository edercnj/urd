package com.br.kerberus.urd.entity;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Objects;

public final class LogMessage {

	@Value("${spring.application.name}")
	private String applicationName;
	private Server server;
	private SystemInformation systemInformation;
	private String message;

	public String getApplicationName() { return applicationName; }
	public void setApplicationName(String applicationName) { this.applicationName = applicationName; }
	public Server getServer() { return server; }
	private void setServer(Server server) { this.server = server; }
	private SystemInformation getSystemInformation() { return systemInformation; }
	private void setSystemInformation(SystemInformation systemInformation) { this.systemInformation = systemInformation; }
	public String getMessage() { return message; }

	private void setMessage(String message) {
		if(message.isBlank() || message.isEmpty())
			throw new IllegalArgumentException("Invalid message. Message must have not black or empty");
		this.message = message;
	}

	@Override
	public String toString() {
		if(this.getSystemInformation() == null)
		{
			return "LogMessage{" +
					"applicationName:'" + applicationName + '\'' +
					", server:" + server +
					", message:'" + message + '\'' +
					'}';
		}
		else {
			return "LogMessage{" +
					"applicationName:'" + applicationName + '\'' +
					", server:" + server +
					", systemInformation:" + systemInformation +
					", message:'" + message + '\'' +
					'}';
		}
	}

	public LogMessage(Server server, SystemInformation systemInformation, String message) {
		this.setServer(server);
		this.setSystemInformation(systemInformation);
		this.setMessage(message);
	}

	public LogMessage(Server server,  String message) {
		this.setServer(server);
		this.setMessage(message);
	}
}