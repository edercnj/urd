package com.br.valhalla.urd.service;

import com.br.valhalla.urd.annotation.LogMethodCall;
import com.br.valhalla.urd.annotation.LogMethodReturn;
import com.br.valhalla.urd.core.AspectLog;
import com.br.valhalla.urd.core.enumeration.LogType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class MethodLogService extends AspectLog implements LogService {

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

    public MethodLogService() {this.setLog(LoggerFactory.getLogger(MethodLogService.class)); }

    @Before(value = "@annotation(com.br.valhalla.urd.annotation.LogMethodCall)")
    public void logMethodCall(JoinPoint joinPoint) {

        setLogTypeString(getLogTypeFromAnnotation(joinPoint));

        if (joinPoint.getArgs().length > 0)
            log.info(String.format("%s | Calling method: {%s} - with parameters: {%s}", getLogTypeString(), joinPoint.getSignature(), getMethodParameters(joinPoint)));
        else
            log.info(String.format("%s | Calling method: {%s} - without parameters", getLogTypeString(), joinPoint.getSignature()));
    }

    @AfterReturning(value = "@annotation(com.br.valhalla.urd.annotation.LogMethodReturn)", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {

        setLogTypeString(getLogTypeFromAnnotation(joinPoint));

        if (((MethodSignature) joinPoint.getSignature()).getReturnType().getName().equals("void")) {
            log.info(String.format("%s | Method Return: {%s} - void.", getLogTypeString(), joinPoint.getSignature()));
        } else {
            if (result != null)
                log.info(String.format("%s | Method Return: {%s} - return {%s}", getLogTypeString(), joinPoint.getSignature(), result.toString()));
            else
                log.info(String.format("%s | Method Return: {%s} - return {null}", getLogTypeString(), joinPoint.getSignature()));
        }
    }

    private String getLogTypeFromAnnotation(JoinPoint joinPoint) {
        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogMethodReturn)
                        return ((LogMethodReturn) logType).logType().toString();
                    if (logType instanceof LogMethodCall)
                        return ((LogMethodCall) logType).logType().toString();
                }
            }
        }
        return LogType.GENERAL.toString();
    }

    private StringBuilder getMethodParameters(JoinPoint joinPoint) {
        if (joinPoint.getArgs().length > 0) {
            StringBuilder parameters = new StringBuilder();
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                parameters.append(String.format("%s=%s ", joinPoint.getArgs()[i].getClass().getSimpleName(), joinPoint.getArgs()[i].toString()));
            }
            return parameters;
        } else {
            return null;
        }
    }
}


