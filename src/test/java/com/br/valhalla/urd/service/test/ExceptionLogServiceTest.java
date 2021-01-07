package com.br.valhalla.urd.service.test;

import com.br.valhalla.urd.annotation.LogMethodCall;
import com.br.valhalla.urd.annotation.LogStartupApplication;
import com.br.valhalla.urd.core.Application;
import com.br.valhalla.urd.core.Server;
import com.br.valhalla.urd.core.SystemInformation;
import com.br.valhalla.urd.core.enumeration.LogLevel;
import com.br.valhalla.urd.service.ExecutionTimeLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@Profile("test")
@RunWith(SpringRunner.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ExceptionLogServiceTest {

    private final Logger log;

    public ExceptionLogServiceTest() { this.log = (LoggerFactory.getLogger(ExceptionLogServiceTest.class)); }

    @Test
    public void logException() {
        methodMustReturnLogMessage("Execute method ExceptionLogServiceTest");
    }

    @LogMethodCall
    @LogStartupApplication
    private void methodMustReturnLogMessage(String message) {
        log.info("------------------------------Starting Application------------------------------");
        log.info("                            <<Application Information>>                         ");
        log.info(String.format("<< %s >>", new Application().toString()));
        log.info("                            <<System Information>>                              ");
        log.info(String.format("<< %s >>", new SystemInformation().toString()));
        log.info("                            <<Server Information>>                              ");
        log.info(String.format("<< %s >>", new Server().toString()));
        log.info("--------------------------------------------------------------------------------");
        log.info(message);
    }
}