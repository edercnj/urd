package com.br.kerberus.urd.service;

import com.br.kerberus.urd.entity.core.LogExecutionTime;
import com.br.kerberus.urd.entity.core.LogTimeType;
import com.br.kerberus.urd.entity.core.LogType;
import com.br.kerberus.urd.entity.core.AspectLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class ExecutionTimeLogService extends AspectLog implements LogService {

    private Logger log;

    public Logger getLog() { return log; }

    public void setLog(Logger log) { this.log = log; }

    public ExecutionTimeLogService() { this.setLog(LoggerFactory.getLogger(ExecutionTimeLogService.class)); }

    @Around("@annotation(com.br.kerberus.urd.entity.core.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        String logTypeString = LogType.EXECUTION_TIME.toString();
        LogTimeType logTimeType = LogTimeType.MILLISECONDS;
        long executionTime = 0L;

        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogExecutionTime) {
                        logTypeString = ((LogExecutionTime) logType).LogType().toString();
                        logTimeType = ((LogExecutionTime) logType).LogTimeType();
                        break;
                    }
                }
            }
        }

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        executionTime = totalExecutionTime(logTimeType, start, end);
        MDC.put("operationType", logTypeString);
        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }

    private long totalExecutionTime(LogTimeType logTimeType, long start, long executionTime) {
        switch (logTimeType) {
            case SECONDS:
                return (executionTime - start) / 1000;
            case MINUTES:
                return ((executionTime - start) / 1000) / 60;
            case MILLISECONDS:
            default:
                return executionTime - start;
        }
    }
}
