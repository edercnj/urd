package com.br.kerberus.urd.service;

import com.br.kerberus.urd.core.SystemInformation;
import com.br.kerberus.urd.core.AspectLog;
import com.br.kerberus.urd.core.LogException;
import com.br.kerberus.urd.core.LogLevel;
import com.br.kerberus.urd.core.LogType;
import com.br.kerberus.urd.exception.ManagedException;
import com.br.kerberus.urd.exception.NoManagedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class ExceptionLogService extends AspectLog implements LogService {

    private Logger log;
    private String logTypeString = LogType.EXECUTION_TIME.toString();
    public String getLogTypeString() {
        return logTypeString;
    }
    public void setLogTypeString(String logTypeString) {
        this.logTypeString = logTypeString;
    }

    @Override
    public Logger getLog() { return log; }

    @Override
    public void setLog(Logger log) {this.log = log;}

    public ExceptionLogService() {this.setLog(LoggerFactory.getLogger(ExceptionLogService.class)); }

    @AfterThrowing(value = "@annotation(com.br.kerberus.urd.core.LogException)", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {

        setLogTypeString(getLogTypeFromAnnotation(joinPoint) != null ? getLogTypeFromAnnotation(joinPoint) :LogType.EXECUTION_TIME.toString());

        if(getLogLevel().equals(LogLevel.DEBUG))
            log.debug(String.format("System Information: %s", new SystemInformation().toString()));

        if (e instanceof ManagedException) {
            log.info(String.format("%s | Launch Managed Exception: {%s} - method: {%s} - message: {%s}", getLogTypeString(), e.getClass().getName(), joinPoint.getSignature(), ((ManagedException)e).getDebugMessage()));
        } else if (e instanceof NoManagedException) {
            log.warn(String.format("%s | Launch NO Managed Exception: {%s} - method: {%s} - message: {%s}", getLogTypeString(), e.getClass().getName(), joinPoint.getSignature(), ((NoManagedException) e).getDebugMessage()));
        } else {
            log.error(String.format("%s | Launch NO Managed Exception: {%s} - method: {%s} - message: {%s}",getLogTypeString(), e.getClass().getName(), joinPoint.getSignature(), e.getCause().getMessage()));
        }
    }

    private String getLogTypeFromAnnotation(JoinPoint joinPoint) {
        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogException)
                        return ((LogException) logType).logType().toString();
                }
            }
        }
        return LogType.GENERAL.toString();
    }
}
