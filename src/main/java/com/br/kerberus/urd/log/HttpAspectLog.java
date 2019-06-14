package com.br.kerberus.urd.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Enumeration;

import static com.br.kerberus.urd.log.AspectLog.getAnnotationsForLog;

@Aspect
@Component
public class HttpAspectLog {

    private Logger log;

    public Logger getLog() { return log; }

    public void setLog(Logger log) { this.log = log; }

    public HttpAspectLog() {
        this.setLog(LoggerFactory.getLogger(HttpAspectLog.class));
    }


    @Before(value = "@annotation(com.br.kerberus.urd.log.LogHttpMessages)")
    public void logBefore(JoinPoint joinPoint) {

        if (joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                if (joinPoint.getArgs()[i] instanceof HttpServletRequest) {

                    HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[i];

                    for (Method method : joinPoint.getSignature().getDeclaringType().getMethods()) {
                        if (method.getName().equals(joinPoint.getSignature().getName())) {
                            for (Annotation logType : getAnnotationsForLog(method)) {
                                if (logType instanceof LogHttpMessages)
                                    MDC.put("operationType", ((LogHttpMessages) logType).LogType().toString());

                                MDC.put("transactionHash", String.valueOf(request.hashCode() + System.nanoTime()));
                            }
                        }
                    }

                    String requestPath = request.getServletPath();
                    String method = request.getMethod();
                    Enumeration headerNames = request.getHeaderNames();
                    StringBuilder headers = new StringBuilder();
                    String queryString = request.getQueryString();
                    while (headerNames.hasMoreElements()) {
                        String headerName = headerNames.nextElement().toString();
                        String headerValue = request.getHeader(headerName);
                        String header = headerName + " : " + headerValue + " ";
                        headers.append(header);
                    }
                    log.info(String.format("Method Type : {%s} - Request Path: {%s} - Headers: {%s} - Query String: {%s}", method, requestPath, headers, queryString));
                }
            }
        }
    }

    private void getHeaders()
    {

    }
}
