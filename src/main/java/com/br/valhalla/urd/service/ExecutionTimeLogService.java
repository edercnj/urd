package com.br.valhalla.urd.service;

import com.br.valhalla.urd.annotation.LogExecutionTime;
import com.br.valhalla.urd.core.AspectLog;
import com.br.valhalla.urd.core.SystemInformation;
import com.br.valhalla.urd.core.enumeration.LogLevel;
import com.br.valhalla.urd.core.enumeration.LogTimeType;
import com.br.valhalla.urd.core.enumeration.LogType;
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
@Order(2)
public class ExecutionTimeLogService extends AspectLog implements LogService {

    private Logger log;
    private String logTypeString;
    private LogTimeType logTimeType;
    private long executionTime = 0L;

    public Logger getLog() { return log; }

    public void setLog(Logger log) { this.log = log; }

    public String getLogTypeString() { return logTypeString; }

    public void setLogTypeString(String logTypeString) { this.logTypeString = logTypeString; }

    public LogTimeType getLogTimeType() { return logTimeType;}

    public void setLogTimeType(LogTimeType logTimeType) { this.logTimeType = logTimeType; }

    public long getExecutionTime() { return executionTime; }

    public void setExecutionTime(long executionTime) { this.executionTime = executionTime; }

    public ExecutionTimeLogService() { this.setLog(LoggerFactory.getLogger(ExecutionTimeLogService.class)); }

    @Around("@annotation(com.br.valhalla.urd.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        setLogTypeString(getLogTypeFromAnnotation(joinPoint) != null ? getLogTypeFromAnnotation(joinPoint) : LogType.EXECUTION_TIME.toString());
        setLogTimeType(getLogTimeTypeFromAnnotation(joinPoint) != null ? getLogTimeTypeFromAnnotation(joinPoint) : LogTimeType.MILLISECONDS);

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        setExecutionTime(totalExecutionTime(getLogTimeType(), start, end));
        MDC.put("operationType", getLogTypeString());

        if(getLogLevel().equals(LogLevel.DEBUG))
            log.debug("{} | System Information: {}", getLogTypeString(), new SystemInformation());

        log.info("{} | {} executed in {} {}",getLogTypeString(), joinPoint.getSignature() , getExecutionTime(), getLogTimeType());

        return proceed;
    }

    private long totalExecutionTime(LogTimeType logTimeType, long start, long executionTime) {
        return switch (logTimeType) {
            case SECONDS -> (executionTime - start) / 1000;
            case MINUTES -> ((executionTime - start) / 1000) / 60;
            default -> executionTime - start;
        };
    }

    private String getLogTypeFromAnnotation(ProceedingJoinPoint joinPoint) {
        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogExecutionTime)
                        return ((LogExecutionTime) logType).logType().toString();
                }
            }
        }
        return LogType.GENERAL.toString();
    }

    private LogTimeType getLogTimeTypeFromAnnotation(ProceedingJoinPoint joinPoint) {
        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogExecutionTime) {
                        return((LogExecutionTime) logType).logTimeType();
                    }
                }
            }
        }
        return LogTimeType.MILLISECONDS;
    }
}
