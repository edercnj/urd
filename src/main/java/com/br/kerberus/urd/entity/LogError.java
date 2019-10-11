package com.br.kerberus.urd.entity;

import org.springframework.http.HttpStatus;

public enum LogError {

    APPLICATION_NOT_FOUND(new SystemLog(502, "Application not found", "Application not found. Please enter a valid value for search.", "", HttpStatus.NOT_FOUND)),
    SERVER_NOT_FOUND(new SystemLog(501, "Server not found", "Server not found. Please enter a valid value for search.", "", HttpStatus.NOT_FOUND)),
    INTERNAL_SERVER_ERROR(new SystemLog(666, "Internal Server Error", "Could not make request. Try later.", "", HttpStatus.INTERNAL_SERVER_ERROR));

    private final SystemLog systemLog;

    LogError(SystemLog log) { this.systemLog = log; }

    public SystemLog getError() { return systemLog; }
}
