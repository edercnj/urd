package com.br.kerberus.urd.service.test;

import com.br.kerberus.urd.core.LogMessage;
import com.br.kerberus.urd.core.Server;
import com.br.kerberus.urd.core.SystemInformation;
import com.br.kerberus.urd.annotation.LogMethodCall;
import org.junit.Test;
import org.springframework.context.annotation.Profile;

@Profile("test")
public class ExceptionLogServiceTest {

    @Test
    public void logException() {
        methodMustReturnLogMessage();
    }

    @LogMethodCall(preExecuteMessage = "Execute method ExceptionLogServiceTest")
    private void methodMustReturnLogMessage() {
        LogMessage logMessage = new LogMessage(new Server(), new SystemInformation(), "test message", "urd");
        System.out.println(logMessage.toString());
    }
}