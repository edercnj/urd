package com.br.kerberus.urd.core.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.flywaydb.core.internal.logging.console.ConsoleLog.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	private Logger log;

	public LogAspect() { this.setLog(LoggerFactory.getLogger(LogAspect.class)); }

	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();

		Object proceed = joinPoint.proceed();

		long executionTime = System.currentTimeMillis() - start;

		 //System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
		log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
		return proceed;
	}

	public Logger getLog() { return log; }

	public void setLog(Logger log) { this.log = log; }
}
