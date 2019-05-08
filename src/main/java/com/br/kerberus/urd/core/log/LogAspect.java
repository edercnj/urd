package com.br.kerberus.urd.core.log;

import com.br.kerberus.urd.exception.UrdException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
		log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

		return proceed;
	}

	@AfterThrowing(pointcut = "execution(* com.br.kerberus.urd.*.*(..))", throwing = "ex")
	public void logUrdException(UrdException ex){

		log.error(ex.getMessage());
	}

	@AfterThrowing(pointcut = "execution(* com.br.kerberus.urd.*.*(..))", throwing = "ex")
	public void logException(Exception ex){
		log.error(ex.getMessage());
	}


	public Logger getLog() { return log; }

	public void setLog(Logger log) { this.log = log; }
}
