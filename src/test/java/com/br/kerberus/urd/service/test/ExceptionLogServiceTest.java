package com.br.kerberus.urd.service.test;

import com.br.kerberus.urd.entity.core.LogLevel;
import com.br.kerberus.urd.entity.core.LogMethodCall;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Profile;

@Profile("test")
public class ExceptionLogServiceTest {

    @Before
    public void setUp() throws Exception { }

    @After
    public void tearDown() throws Exception { }

    @Test
    public void getLog() { }

    @Test
    public void setLog() { }

    @Test
    public void logException() {
        methodMustReturnLogMessage();
    }

    @LogMethodCall(logLevel = {LogLevel.DEBUG, LogLevel.INFO, LogLevel.ERROR, LogLevel.WARN})
    private void methodMustReturnLogMessage() {
        System.out.println(String.format("Log message to save: %s", "parameter for log"));
    }
}