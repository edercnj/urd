package com.br.kerberus.urd.service.test;

import com.br.kerberus.urd.entity.core.LogException;
import com.br.kerberus.urd.entity.core.LogLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


public class ExceptionLogServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getLog() {
    }

    @Test
    public void setLog() {
    }

    @Test
    public void logException() {

        try {
            throwsException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @LogException(logLevel = {LogLevel.DEBUG, LogLevel.INFO, LogLevel.ERROR, LogLevel.WARN}, title = "logException method test")
    private void throwsException() throws Exception {
        throw new Exception("Lancando excecao");
    }
}