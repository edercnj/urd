package com.br.kerberus.urd.service;

import com.br.kerberus.urd.core.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class ExecutionTimeLogService extends AspectLog implements LogService {

    private Logger log;
    private String logTypeString = LogType.EXECUTION_TIME.toString();
    private LogTimeType logTimeType = LogTimeType.MILLISECONDS;
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

    @Around("@annotation(com.br.kerberus.urd.core.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        setLogTypeString(getLogTypeFromAnnotation(joinPoint) != null ? getLogTypeFromAnnotation(joinPoint) : LogType.EXECUTION_TIME.toString());
        setLogTimeType(getLogTimeTypeFromAnnotation(joinPoint) != null ? getLogTimeTypeFromAnnotation(joinPoint) : LogTimeType.MILLISECONDS);

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        setExecutionTime(totalExecutionTime(getLogTimeType(), start, end));
        MDC.put("operationType", getLogTypeString());

        if(getLogLevel().equals(LogLevel.DEBUG))
            log.debug(String.format("%s | System Information: %s", getLogTypeString(), new SystemInformation().toString()));

        log.info(String.format("%s | %s executed in %s %s",getLogTypeString(), joinPoint.getSignature() , getExecutionTime(), getLogTimeType().toString()));

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
