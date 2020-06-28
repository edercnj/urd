package com.br.valhalla.urd.service;

import com.br.valhalla.urd.annotation.LogRestMessage;
import com.br.valhalla.urd.core.AspectLog;
import com.br.valhalla.urd.core.enumeration.LogType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;

@Aspect
@Component
public class RestLogService extends AspectLog implements LogService {

    private Logger log;
    private String logTypeString;

    public String getLogTypeString() {
        return logTypeString;
    }

    public void setLogTypeString(String logTypeString) {
        this.logTypeString = logTypeString;
    }

    @Override
    public Logger getLog() { return log; }

    @Override
    public void setLog(Logger log) { this.log = log; }

    public RestLogService() {this.setLog(LoggerFactory.getLogger(RestLogService.class)); }

    @Before(value = "@annotation(com.br.valhalla.urd.annotation.LogRestMessage)")
    public void logRestRequest(JoinPoint joinPoint) {
        setLogTypeString(getLogTypeFromAnnotation(joinPoint));
    }

    @AfterReturning(value = "@annotation(com.br.valhalla.urd.annotation.LogMethodReturn)", returning = "result")
    public void logRestResponse(JoinPoint joinPoint, Object result) {
        setLogTypeString(getLogTypeFromAnnotation(joinPoint));
    }

    private String getLogTypeFromAnnotation(JoinPoint joinPoint) {
        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogRestMessage)
                        return ((LogRestMessage) logType).logType().toString();
                }
            }
        }
        return LogType.GENERAL.toString();
    }

    //before -> Any resource annotated with @Controller annotation
    //and all method and function taking HttpServletRequest as first parameter
    @Before("controller() && allMethod() && args(..,request)")
    public void logBefore(JoinPoint joinPoint, HttpServletRequest request) {

        log.debug("Entering in Method :  " + joinPoint.getSignature().getName());
        log.debug("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        log.debug("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
        log.debug("Target class : " + joinPoint.getTarget().getClass().getName());

        if (null != request) {
            log.debug("Start Header Section of request ");
            log.debug("Method Type : " + request.getMethod());
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                log.debug("Header Name: " + headerName + " Header Value : " + headerValue);
            }
            log.debug("Request Path info :" + request.getServletPath());
            log.debug("End Header Section of request ");
        }
    }
    //After -> All method within resource annotated with @Controller annotation
    // and return a  value
    @AfterReturning(pointcut = "controller() && allMethod()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String returnValue = this.getValue(result);
        log.debug("Method Return value : " + returnValue);
    }
    //After -> Any method within resource annotated with @Controller annotation
    // throws an exception ...Log it
    @AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
        log.error("Cause : " + exception.getCause());
    }
    //Around -> Any method within resource annotated with @Controller annotation
    @Around("controller() && allMethod()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.debug("Method " + className + "." + methodName + " ()" + " execution time : "
                    + elapsedTime + " ms");

            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }
    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }
}
