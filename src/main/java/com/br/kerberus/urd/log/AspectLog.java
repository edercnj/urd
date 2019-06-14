package com.br.kerberus.urd.log;

import com.br.kerberus.urd.exception.ManagedException;
import com.br.kerberus.urd.exception.NoManagedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
public class AspectLog {

    private Logger log;

    public Logger getLog() { return log; }

    public void setLog(Logger log) { this.log = log; }

    public AspectLog() {
        this.setLog(LoggerFactory.getLogger(AspectLog.class));
    }

    @Around("@annotation(com.br.kerberus.urd.log.LogExecutionTime)")
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

    @AfterThrowing(value = "@annotation(com.br.kerberus.urd.log.LogException)", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {

        for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                for (Annotation logType : getAnnotationsForLog(method)) {
                    if (logType instanceof LogException)
                        MDC.put("operationType", ((LogException) logType).LogType().toString());
                }
            }
        }

        if (e instanceof ManagedException) {
            ManagedException ex = (ManagedException) e;
            log.info(String.format("Launch Managed Exception: {%s} - method: {%s} - message: {%s}", e.getClass().getName(), joinPoint.getSignature(), ex.getDebugMessage()));
        } else if (e instanceof NoManagedException) {
            log.warn(String.format("Launch NO Managed Exception: {%s} - method: {%s} - message: {%s}", e.getClass().getName(), joinPoint.getSignature(), ((NoManagedException) e).getDebugMessage()));
        } else {
            log.warn(String.format("Launch NO Managed Exception: {%s} - method: {%s} - message: {%s}", e.getClass().getName(), joinPoint.getSignature(), e.getCause().getMessage()));
        }
    }

    @Before(value = "@annotation(com.br.kerberus.urd.log.LogMethodCall)")
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

    @AfterReturning(value = "@annotation(com.br.kerberus.urd.log.LogMetlhodReturn)", returning = "result")
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

    protected static List<Annotation> getAnnotationsForLog(Method methodWithAnnotations) {
        List<Annotation> annotationsReturn = new ArrayList<>();
        Annotation[] annotations = methodWithAnnotations.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof LogException || annotation instanceof LogExecutionTime || annotation instanceof LogHttpMessages ||
                    annotation instanceof LogMethodCall || annotation instanceof LogMetlhodReturn) {
                annotationsReturn.add(annotation);
            }
        }
        return annotationsReturn;
    }
}
