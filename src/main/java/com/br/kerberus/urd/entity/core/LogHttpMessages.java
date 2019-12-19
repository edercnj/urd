package com.br.kerberus.urd.entity.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogHttpMessages {

    LogType logType() default LogType.HTTP_MESSAGES;
    LogLevel[] logLevel() default {LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR, LogLevel.DEBUG};
    HttpMethods[] httpMethods() default {HttpMethods.GET, HttpMethods.DELETE, HttpMethods.PUT, HttpMethods.POST, HttpMethods.PATCH};

}
