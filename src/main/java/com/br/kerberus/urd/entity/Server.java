package com.br.kerberus.urd.entity;

public class Server {

    private String hostname;

    private String ip;

    public String getHostname() {
        if (hostname.isBlank() || hostname.isEmpty())
            return "Unknown";
        else
            return hostname;
    }

    public void setHostname(String hostname) {
        if (hostname.isBlank() || hostname.isEmpty())
            this.hostname = "Unknown";
        else
            this.hostname = hostname;
    }

    public String getIp() { return ip; }

    public void setIp(String ip) {
        if (ip.isEmpty() || ip.isBlank())
            this.ip = "127.0.0.1";
        else
            this.ip = ip;
    }

    public Server(String hostname, String ip) {
        setHostname(hostname);
        setIp(ip);
    }

    @Override
    public String toString() {
        return "Server{" + "hostname:'" + hostname + '\'' + ", ip:'" + ip + '\'' + '}';
    }
}
