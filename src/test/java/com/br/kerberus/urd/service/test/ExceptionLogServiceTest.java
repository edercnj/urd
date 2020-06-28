package com.br.kerberus.urd.service.test;

import com.br.kerberus.urd.core.LogMessage;
import com.br.kerberus.urd.core.Server;
import com.br.kerberus.urd.core.SystemInformation;
import com.br.kerberus.urd.annotation.LogMethodCall;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@Profile("test")
@RunWith(SpringRunner.class)
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