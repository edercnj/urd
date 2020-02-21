package com.br.kerberus.urd.entity;

import org.springframework.beans.factory.annotation.Value;

public final class LogMessage {

    @Value("${spring.application.name}")
    private String applicationName;
    private Server server;
    private SystemInformation systemInformation;
    private String message;

    public String getApplicationName() { return applicationName; }

    public void setApplicationName(String applicationName) {
        if (applicationName.isBlank() || applicationName.isEmpty())
            throw new IllegalArgumentException("Invalid application name. Application name must have not blank or empty");
        this.applicationName = applicationName;
    }

    public void setMessage(String message) {
        if (message.isBlank() || message.isEmpty())
            throw new IllegalArgumentException("Invalid message. Message must have not blank or empty");
        this.message = message;
    }

    public void setServer(Server server) {
        if (server == null)
            throw new IllegalArgumentException("Invalid Server. Server must have not null");
        this.server = server;
    }

    public void setSystemInformation(SystemInformation systemInformation) {
        if (systemInformation == null)
            throw new IllegalArgumentException("Invalid System Information. System Information must have not null");
        this.systemInformation = systemInformation;
    }

    public Server getServer() { return server; }

    private SystemInformation getSystemInformation() { return systemInformation; }

    public String getMessage() { return message; }


    @Override
    public String toString() {
        if (this.getSystemInformation() == null) {
            return "{" +
                    "applicationName:'" + getApplicationName() + '\'' +
                    ", server:" + getServer().toString() +
                    ", message:'" + getMessage() + '\'' +
                    '}';
        } else {
            return "{" +
                    "applicationName:'" + getApplicationName() + '\'' +
                    ", server:" + getServer().toString() +
                    ", systemInformation:" + getSystemInformation().toString() +
                    ", message:'" + getMessage() + '\'' +
                    '}';
        }
    }

    public LogMessage(Server server, SystemInformation systemInformation, String message, String applicationName) {
        this.setServer(server);
        this.setSystemInformation(systemInformation);
        this.setMessage(message);
        this.setApplicationName(applicationName);
    }

    public LogMessage(Server server, String message, String applicationName) {
        this.setServer(server);
        this.setMessage(message);
        this.setApplicationName(applicationName);
    }

    public LogMessage(String message) {
        this.setServer(new Server());
        this.setMessage(message);
        this.setApplicationName(getApplicationName());
    }
}