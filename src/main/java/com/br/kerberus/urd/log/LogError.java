package com.br.kerberus.urd.log;

import org.springframework.http.HttpStatus;

public enum LogError {

	SERVER_NOT_FOUND(new SystemLog(501, "Server with id {%s} not found", "Server with id {%s} not found. Please enter a valid value for search.", "", HttpStatus.NOT_FOUND));

	private final SystemLog systemLog;

	LogError(SystemLog log) {

		this.systemLog = log;
	}

	public SystemLog getError() { return systemLog; }
}
