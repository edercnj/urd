package com.br.kerberus.urd.service;

import com.br.kerberus.urd.annotation.LogRestMessage;
import com.br.kerberus.urd.core.AspectLog;
import com.br.kerberus.urd.core.enumeration.LogType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class RestLogService extends AspectLog implements LogService {

    private Logger log;
    private String logTypeString;

    public String getLogTypeString() {
        return logTypeString;
    }

    public void setLogTypeString(String logTypeString) {
        this.logTypeString = logTypeString;
    }

    @Override
    public Logger getLog() { return log; }

    @Override
    public void setLog(Logger log) { this.log = log; }

    public RestLogService() {this.setLog(LoggerFactory.getLogger(RestLogService.class)); }

    @Before(value = "@annotation(com.br.kerberus.urd.annotation.LogRestMessage)")
    public void logRestRequest(JoinPoint joinPoint) {
        setLogTypeString(getLogTypeFromAnnotation(joinPoint));
    }

    @AfterReturning(value = "@annotation(com.br.kerberus.urd.annotation.LogMethodReturn)", returning = "result")
    public void logRestResponse(JoinPoint joinPoint, Object result) throws Exception {
        setLogTypeString(getLogTypeFromAnnotation(joinPoint));
    }

    private String getLogTypeFromAnnotation(JoinPoint joinPoint) {
        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogRestMessage)
                        return ((LogRestMessage) logType).logType().toString();
                }
            }
        }
        return LogType.GENERAL.toString();
    }
}
