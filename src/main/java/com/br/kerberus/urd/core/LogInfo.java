package com.br.kerberus.urd.core;

import com.br.kerberus.urd.core.log.SystemLog;
import org.springframework.http.HttpStatus;

public enum LogInfo {

	SERVER_NOT_FOUND(new SystemLog(501, "Server with id {%s} not found", "Server with id {%s} not found. Please enter a valid value for search.", "", HttpStatus.NOT_FOUND));

	private final SystemLog systemLog;

	LogInfo(SystemLog log) {

		this.systemLog = log;
	}

	public SystemLog getError() { return systemLog; }
}
