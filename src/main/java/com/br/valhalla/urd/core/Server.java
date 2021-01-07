package com.br.valhalla.urd.core;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Server {

    private String hostname;

    private List<String> ips;

    public String getHostname() { return hostname; }

    private void setHostname(String hostname) { this.hostname = hostname; }

    public List<String> getIps() { return ips; }

    private void setIps(List<String> ips) { this.ips = ips; }

    public Server() {
        setHostname(getHostnameServer());
        setIps(getAllIpAddress());
    }

    @Override
    public String toString() {
        return "{" + "hostname:'" + getHostname() + '\'' + ", IP:" + getIps() + '}';
    }

    private List<String> getAllIpAddress() {
        List<String> ip = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface n = e.nextElement();
                Enumeration<InetAddress> inetAddress = n.getInetAddresses();
                while (inetAddress.hasMoreElements()) {
                    InetAddress i = inetAddress.nextElement();
                    if (i instanceof Inet4Address && !i.getHostAddress().equals("127.0.0.1")) {
                        ip.add(i.getHostAddress());
                    }
                }
            }
            setIps(ip);
        } catch (Exception ex) {
            ip.add("127.0.0.1");
            setIps(ip);
        }
        return ip;
    }

    private String getHostnameServer() {
        try {
            InetAddress hostname = InetAddress.getLocalHost();
            return hostname.getHostName();
        } catch (Exception ex) {
            return "localhost";
        }
    }
}
