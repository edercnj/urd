package com.br.kerberus.urd.entity.core;

import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {

    LogType LogType() default LogType.EXECUTION_TIME;

    LogLevel[] LogLevel() default {LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR, LogLevel.DEBUG};

    LogTimeType LogTimeType() default LogTimeType.MILLISECONDS;
}
