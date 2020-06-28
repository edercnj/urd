package com.br.valhalla.urd.service;

import com.br.valhalla.urd.core.Application;
import com.br.valhalla.urd.core.AspectLog;
import com.br.valhalla.urd.core.SystemInformation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StartupApplicationLogService extends AspectLog implements LogService {

    private Logger log;

    @Override
    public Logger getLog() { return log; }

    @Override
    public void setLog(Logger log) {this.log = log;}

    public StartupApplicationLogService() {this.setLog(LoggerFactory.getLogger(StartupApplicationLogService.class)); }

    @Before(value = "@annotation(com.br.valhalla.urd.annotation.LogStartupApplication)")
    public void logStartupInformation() {

        log.info("--------------------------------------------------------------------------------------------------");
        log.info(String.format("Starting Application %s", "app"));
        log.info(String.format("Information Application %s", new Application()));
        log.info(String.format("System Information: %s", new SystemInformation().toString()));
        log.info("--------------------------------------------------------------------------------------------------");
    }
}
