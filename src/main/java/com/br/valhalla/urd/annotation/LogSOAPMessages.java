package com.br.valhalla.urd.annotation;

import com.br.valhalla.urd.core.enumeration.LogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogSOAPMessages {

    LogType LogType() default LogType.SOAP_MESSAGE;
    String preExecuteMessage() default "";
}
