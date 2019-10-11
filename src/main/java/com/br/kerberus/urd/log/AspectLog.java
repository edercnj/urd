package com.br.kerberus.urd.log;

import com.br.kerberus.urd.entity.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class AspectLog {

    protected List<Annotation> getAnnotationsForLog(Method methodWithAnnotations) {
        List<Annotation> annotationsReturn = new ArrayList<>();
        Annotation[] annotations = methodWithAnnotations.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof LogException || annotation instanceof LogExecutionTime || annotation instanceof LogHttpMessages ||
                    annotation instanceof LogMethodCall || annotation instanceof LogMetlhodReturn || annotation instanceof LogSOAPMessages
                    || annotation instanceof LogDebug) {
                annotationsReturn.add(annotation);
            }
        }
        return annotationsReturn;
    }
}
