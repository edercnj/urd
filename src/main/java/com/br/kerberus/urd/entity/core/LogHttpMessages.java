package com.br.kerberus.urd.entity.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogHttpMessages {

    LogType LogType() default LogType.HTTP_MESSAGES;

}