package com.br.kerberus.urd.config;

import com.br.kerberus.urd.entity.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class UrdConfig {

    public static Server server;

    @Bean
    protected void setProperties()
    {
        try {

            server.setHostname(InetAddress.getLocalHost().getHostName().toUpperCase());
            server.setIp(InetAddress.getLocalHost().getHostAddress());

        } catch (UnknownHostException e) {

            server.setHostname("unknown");
            server.setIp("127.0.0.1");

        }
    }
}
