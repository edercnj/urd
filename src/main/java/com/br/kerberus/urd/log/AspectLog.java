package com.br.kerberus.urd.log;

import com.br.kerberus.urd.exception.UrdException;
import com.br.kerberus.urd.resource.ErrorResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class AspectLog {
	
	private Logger log;

	public AspectLog() {
		this.setLog(LoggerFactory.getLogger(AspectLog.class));
	}

	@Around("@annotation(com.br.kerberus.urd.log.LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

		return proceed;
	}

	@AfterThrowing(value = "@annotation(com.br.kerberus.urd.log.LogDomainsException)", throwing = "e")
	public void logUrdException(JoinPoint joinPoint, UrdException e) {
		log.error(String.format("Launch Managed Exception: {{%s}}  - method: {{%s}} - line {{%s}}", e.getClass().getName(), joinPoint.getSignature(), e.getStackTrace()[0].getLineNumber()));
	}

	@Before(value = "@annotation(com.br.kerberus.urd.log.LogMetlhodCall)")
	public void logMethodCall(JoinPoint joinPoint) {

		if (joinPoint.getArgs().length > 0) {
			StringBuilder parameters = new StringBuilder();
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				parameters.append(String.format("%s=%s ", joinPoint.getArgs()[i].getClass().getSimpleName(), joinPoint.getArgs()[i].toString()));
			}
			log.error(String.format("Calling method: {{%s}} - with parameters: {{ [%s] }}", joinPoint.getSignature(), parameters));

		} else {
			log.error(String.format("Calling method: {{%s}} - with parameters: {{ [null] }}", joinPoint.getSignature()));
		}
	}

	@AfterReturning(value = "@annotation(com.br.kerberus.urd.log.LogMetlhodReturn)", returning = "result")
	public void logMethodReturn(JoinPoint joinPoint, Object result) {

		if (result != null)
			log.error(String.format("Method return: {{%s}} - return {{ [%s] }}", joinPoint.getSignature(), result.toString()));
		else
			log.error(String.format("Method return: {{%s}} -return {{ [null or void] }}", joinPoint.getSignature()));
	}


	@AfterReturning(value = "@annotation(com.br.kerberus.urd.log.LogControllerAdvice)", returning = "result")
	public void logExceptionControllerAdvice(JoinPoint joinPoint, Object result) {

		ResponseEntity<ErrorResponse> errorResponse = (ResponseEntity<ErrorResponse>) result;

		log.error(String.format("Launch NO Managed Exception: {{%s}} - exception message: {{%s}}", joinPoint.getSignature(), Objects.requireNonNull(errorResponse.getBody()).getDebugMessage()));

		//log.error(Objects.requireNonNull(errorResponse.getBody()).getDebugMessage());
	}


	public Logger getLog() { return log; }

	public void setLog(Logger log) { this.log = log; }
}
