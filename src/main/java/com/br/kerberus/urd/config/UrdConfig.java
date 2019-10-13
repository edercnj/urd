package com.br.kerberus.urd.config;

import com.br.kerberus.urd.entity.core.LogType;
import org.slf4j.MDC;
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
            MDC.put("operationType", LogType.GENERAL.toString());

        } catch (UnknownHostException e) {

            System.setProperty("hostname", "unknown");
        }
    }
}
