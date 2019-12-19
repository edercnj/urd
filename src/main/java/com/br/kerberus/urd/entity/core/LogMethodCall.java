package com.br.kerberus.urd.entity.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMethodCall {

    LogType logType() default LogType.METHOD_CALL;
    LogLevel[] logLevel() default {LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR, LogLevel.DEBUG};
}
