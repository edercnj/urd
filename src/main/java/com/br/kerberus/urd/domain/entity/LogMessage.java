package com.br.kerberus.urd.domain.entity;

import com.google.gson.Gson;

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
    private URL moreInfo;

    public LogMessage(String serverName, String serviceName, Date dateTime, int errorCord, String developerMessage, String userMessage, URL moreInfo) {
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

    public URL getMoreInfo() { return moreInfo; }

    public void setMoreInfo(URL moreInfo) { this.moreInfo = moreInfo; }

    @Override
    public int hashCode() { return Objects.hash(getServerName(), getServiceName(), getDateTime(), getErrorCord()); }

    @Override
    public String toString() {
        return String.format(hashCode() + " | serverName:%s | serviceName:%s1 | dateTime:%s2 | errorCord:%s3 | developerMessage:%s4 | userMessage:%s5 | moreInfo:%s6",
                getServerName(), getServiceName(), getDateTime(), getErrorCord(), getDeveloperMessage(), getUserMessage(), getMoreInfo());
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
