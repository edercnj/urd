package com.br.kerberus.urd.service;

import com.br.kerberus.urd.entity.core.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
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

    @Before(value = "@annotation(com.br.kerberus.urd.entity.core.LogMethodCall)")
    public void logMethodCall(JoinPoint joinPoint) {

        setLogTypeString(getLogTypeFromAnnotation(joinPoint));
        MDC.put("operationType", getLogTypeString());

        if (joinPoint.getArgs().length > 0)
            log.info(String.format("Calling method: {%s} - with parameters: {%s}", joinPoint.getSignature(), getMethodParameters(joinPoint)));
        else
            log.info(String.format("Calling method: {%s} - without parameters", joinPoint.getSignature()));
    }

    @AfterReturning(value = "@annotation(com.br.kerberus.urd.entity.core.LogMetlhodReturn)", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) throws Exception {
        setLogTypeString(getLogTypeFromAnnotation(joinPoint));
        MDC.put("operationType", getLogTypeString());

        try {
            if(((MethodSignature)joinPoint.getSignature()).getReturnType().getName().equals("void")) {
                log.info(String.format("Method Return: {%s} - void return.", joinPoint.getSignature()));
            }else {
                if (result != null)
                    log.info(String.format("Method Return: {%s} - return {%s}", joinPoint.getSignature(), result.toString()));
                else
                    log.info(String.format("Method Return: {%s} - return {null}", joinPoint.getSignature()));
            }
        } catch (Exception e) {
            log.error(String.format("Method {%s} launch exception {%s}", joinPoint.getSignature(), e.getClass().getName()));
            throw new Exception(e.getMessage());
        }
    }

    private String getLogTypeFromAnnotation(JoinPoint joinPoint) {
        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogMetlhodReturn)
                        return ((LogMetlhodReturn) logType).logType().toString();
                    if (logType instanceof LogMethodCall)
                        return ((LogMethodCall) logType).logType().toString();
                }
            }
        }
        return LogType.GENERAL.toString();
    }

    private StringBuilder getMethodParameters(JoinPoint joinPoint)
    {
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
