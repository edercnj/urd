package com.br.kerberus.urd.service;

import com.br.kerberus.urd.entity.*;
import com.br.kerberus.urd.log.AspectLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class ExecutionTimeLogService extends AspectLog implements LogService {

    private Logger log;

    public Logger getLog() { return log; }

    public void setLog(Logger log) { this.log = log; }

    public ExecutionTimeLogService() { this.setLog(LoggerFactory.getLogger(ExecutionTimeLogService.class)); }

    @Around("@annotation(com.br.kerberus.urd.entity.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        String logTypeString = LogType.EXECUTION_TIME.toString();

        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogExecutionTime) {
                        logTypeString = ((LogExecutionTime) logType).LogType().toString();
                        break;
                    }
                }
            }
        }

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        MDC.put("operationType", logTypeString);
        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }
}
