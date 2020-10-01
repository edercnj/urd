package com.br.valhalla.urd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@ComponentScan(basePackages="com.br.valhalla")
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
