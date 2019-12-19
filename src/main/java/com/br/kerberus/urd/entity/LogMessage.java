package com.br.kerberus.urd.entity;

public final class LogMessage {

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

    private void setMessage(String message) {
        if (message.isBlank() || message.isEmpty())
            throw new IllegalArgumentException("Invalid message. Message must have not blank or empty");
        this.message = message;
    }

    private void setServer(Server server) {
        if (server == null)
            throw new IllegalArgumentException("Invalid Server. Server must have not null");
        this.server = server;
    }

    private void setSystemInformation(SystemInformation systemInformation) {
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
            return "LogMessage{" +
                    "applicationName:'" + getApplicationName() + '\'' +
                    ", server:" + getServer() +
                    ", message:'" + getMessage() + '\'' +
                    '}';
        } else {
            return "LogMessage{" +
                    "applicationName:'" + getApplicationName() + '\'' +
                    ", server:" + getServer() +
                    ", systemInformation:" + getSystemInformation() +
                    ", message:'" + getMessage() + '\'' +
                    '}';
        }
    }

    public LogMessage(Server server, SystemInformation systemInformation, String messageString, String applicationName) {
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
}