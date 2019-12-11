package com.br.kerberus.urd.service;

import com.br.kerberus.urd.entity.core.LogException;
import com.br.kerberus.urd.exception.ManagedException;
import com.br.kerberus.urd.exception.NoManagedException;
import com.br.kerberus.urd.entity.core.AspectLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(0)
public class ExceptionLogService extends AspectLog implements LogService {

    private Logger log;

    @Override
    public Logger getLog() { return log; }

    @Override
    public void setLog(Logger log) {this.log = log;}

    public ExceptionLogService() {this.setLog(LoggerFactory.getLogger(ExceptionLogService.class)); }

    @AfterThrowing(value = "@annotation(com.br.kerberus.urd.entity.core.LogException)", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {

        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogException)
                        MDC.put("operationType", ((LogException) logType).LogType().toString());
                }
            }
        }

        Annotation[] annotations = e.getClass().getAnnotations();

        if (e instanceof ManagedException) {
            ManagedException ex = (ManagedException) e;
            log.info(String.format("Launch Managed Exception: {%s} - method: {%s} - message: {%s}", e.getClass().getName(), joinPoint.getSignature(), ex.getDebugMessage()));
        } else if (e instanceof NoManagedException) {
            log.error(String.format("Launch NO Managed Exception: {%s} - method: {%s} - message: {%s}", e.getClass().getName(), joinPoint.getSignature(), ((NoManagedException) e).getDebugMessage()));
        } else {
            log.error(String.format("Launch NO Managed Exception: {%s} - method: {%s} - message: {%s}", e.getClass().getName(), joinPoint.getSignature(), e.getCause().getMessage()));
        }
    }
}
