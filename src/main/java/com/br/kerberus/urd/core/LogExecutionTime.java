package com.br.kerberus.urd.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {

    LogType logType() default LogType.EXECUTION_TIME;
    LogTimeType logTimeType() default LogTimeType.MILLISECONDS;
    String preExecuteMessage() default "";
}
