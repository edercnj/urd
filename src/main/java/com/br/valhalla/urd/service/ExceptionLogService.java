package com.br.valhalla.urd.service;

import com.br.valhalla.urd.annotation.LogException;
import com.br.valhalla.urd.core.AspectLog;
import com.br.valhalla.urd.core.SystemInformation;
import com.br.valhalla.urd.core.enumeration.LogLevel;
import com.br.valhalla.urd.core.enumeration.LogType;
import com.br.valhalla.urd.exception.ManagedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(0)
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

    @AfterThrowing(value = "@annotation(com.br.valhalla.urd.annotation.LogException)", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {

        setLogTypeString(getLogTypeFromAnnotation(joinPoint) != null ? getLogTypeFromAnnotation(joinPoint) : LogType.EXCEPTION.toString());

        if (getLogLevel().equals(LogLevel.DEBUG)) {
            log.debug("System Information: {}", new SystemInformation());
        }

        if (e instanceof ManagedException){
            log.info("{} | Launch Managed Exception: {} - method: {} - message: {}",
                    getLogTypeString(),
                    e.getClass().getName(),
                    joinPoint.getSignature(),
                    ((ManagedException) e).getDebugMessage());
        } else {
            log.error("{} | Launch NO Managed Exception: {} - method: {} - message: {}",
                    getLogTypeString(),
                    e.getClass().getName(),
                    joinPoint.getSignature(),
                    e.getCause().getMessage());
        }
    }

    public String getLogTypeFromAnnotation(JoinPoint joinPoint) {
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
