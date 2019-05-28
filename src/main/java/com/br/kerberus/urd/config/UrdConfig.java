package com.br.kerberus.urd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class UrdConfig {

    @Bean
    protected void setProperties()
    {
        try {
            System.setProperty("hostname", InetAddress.getLocalHost().getHostName().toUpperCase());

        } catch (UnknownHostException e) {

            System.setProperty("hostname", "unknown");
        }
    }
}
