package com.br.themythical.urd.domain.entities;

import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Objects;

public final class SystemErrorMessage {

    private String serverName;
    @Value("$spring.application.name")
    private String serviceName;
    private Date dateTime;
    private int errorCord;
    private String developerMessage;
    private String userMessage;
    private URL moreInfo;

    public SystemErrorMessage(int errorCord, String developerMessage, String userMessage, URL moreInfo) {
        setServerName();
        setDateTime();
        this.errorCord = errorCord;
        this.developerMessage = developerMessage;
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

    public String getServerName() {
        return serverName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getErrorCord() {
        return errorCord;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public URL getMoreInfo() {
        return moreInfo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServerName(), getServiceName(), getDateTime(), getErrorCord());
    }

    @Override
    public String toString() {
        return String.format(hashCode() + " | serverName:%s | serviceName:%s1 | dateTime:%s2 | errorCord:%s3 | developerMessage:%s4 | userMessage:%s5 | moreInfo:%s6",
                getServerName(), getServiceName(), getDateTime(), getErrorCord(), getDeveloperMessage(), getUserMessage(), getMoreInfo());
    }
}
