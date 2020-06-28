package com.br.valhalla.urd.core;

import com.br.valhalla.urd.annotation.*;
import com.br.valhalla.urd.core.enumeration.LogLevel;
import com.br.valhalla.urd.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class AspectLog {

    @Value("${logging.level.urd}")
    private LogLevel logLevel;

    protected List<Annotation> getAnnotationsForLog(Method methodWithAnnotations) {
        List<Annotation> annotationsReturn = new ArrayList<>();
        Annotation[] annotations = methodWithAnnotations.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof LogException ||
                    annotation instanceof LogExecutionTime ||
                    annotation instanceof LogRestMessage ||
                    annotation instanceof LogMethodCall ||
                    annotation instanceof LogMethodReturn ||
                    annotation instanceof LogSOAPMessages) {
                annotationsReturn.add(annotation);
            }
        }
        return annotationsReturn;
    }

    public LogLevel getLogLevel() { return logLevel; }
}
