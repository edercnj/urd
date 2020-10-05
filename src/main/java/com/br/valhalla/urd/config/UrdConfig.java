package com.br.valhalla.urd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableAspectJAutoProxy
public class UrdConfig {

    @Bean
    protected void setHostnameProperty() {
        try {
            System.setProperty("hostname", InetAddress.getLocalHost().getHostName().toUpperCase());
        } catch (UnknownHostException e) {
            System.setProperty("hostname", "unknown");
        }
    }
}
