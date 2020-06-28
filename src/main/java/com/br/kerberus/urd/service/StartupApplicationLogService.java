package com.br.kerberus.urd.service;

import com.br.kerberus.urd.core.AspectLog;
import com.br.kerberus.urd.core.SystemInformation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StartupApplicationLogService extends AspectLog implements LogService {

    private Logger log;
    private String logTypeString;

    @Override
    public Logger getLog() { return log; }

    public String getLogTypeString() {
        return logTypeString;
    }

    public void setLogTypeString(String logTypeString) {
        this.logTypeString = logTypeString;
    }

    @Override
    public void setLog(Logger log) {this.log = log;}

    public StartupApplicationLogService() {this.setLog(LoggerFactory.getLogger(StartupApplicationLogService.class)); }

    @Before(value = "@annotation(com.br.kerberus.urd.annotation.LogMethodCall)")
    public void logMethodCall(JoinPoint joinPoint) {

        log.info(String.format("Starting Application %s", "app"));
        log.info(String.format("%s | System Information: %s", getLogTypeString(), new SystemInformation().toString()));
    }
}
