package com.br.kerberus.urd.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogSOAPMessages {

    LogType LogType() default LogType.SOAP_MESSAGES;
}
