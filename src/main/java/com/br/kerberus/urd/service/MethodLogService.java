package com.br.kerberus.urd.service;

import com.br.kerberus.urd.entity.core.LogMethodCall;
import com.br.kerberus.urd.entity.core.LogMetlhodReturn;
import com.br.kerberus.urd.entity.core.AspectLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(2)
public class MethodLogService extends AspectLog implements LogService {
    private Logger log;

    @Override
    public Logger getLog() { return log; }

    @Override
    public void setLog(Logger log) {this.log = log;}

    public MethodLogService() {this.setLog(LoggerFactory.getLogger(MethodLogService.class)); }

    @Before(value = "@annotation(com.br.kerberus.urd.entity.core.LogMethodCall)")
    public void logMethodCall(JoinPoint joinPoint) {

        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogMethodCall)
                        MDC.put("operationType", ((LogMethodCall) logType).LogType().toString());
                }
            }
        }

        if (joinPoint.getArgs().length > 0) {
            StringBuilder parameters = new StringBuilder();
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                parameters.append(String.format("%s=%s ", joinPoint.getArgs()[i].getClass().getSimpleName(), joinPoint.getArgs()[i].toString()));
            }
            log.info(String.format("Calling method: {%s} - with parameters: {%s}", joinPoint.getSignature(), parameters));

        } else {
            log.info(String.format("Calling method: {%s} - with parameters: {null}", joinPoint.getSignature()));
        }
    }

    @AfterReturning(value = "@annotation(com.br.kerberus.urd.entity.core.LogMetlhodReturn)", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) throws Exception {

        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogMetlhodReturn)
                        MDC.put("operationType", ((LogMetlhodReturn) logType).LogType().toString());
                }
            }
        }

        try {
            if (result != null)
                log.info(String.format("Method Return: {%s} - return {%s}", joinPoint.getSignature(), result.toString()));
            else
                log.info(String.format("Method Return: {%s} - return {null}", joinPoint.getSignature()));
        } catch (Exception e) {
            log.error(String.format("Method {%s} launch exception {%s}", joinPoint.getSignature(), e.getClass().getName()));
            throw new Exception(e.getMessage());
        }
    }
}
