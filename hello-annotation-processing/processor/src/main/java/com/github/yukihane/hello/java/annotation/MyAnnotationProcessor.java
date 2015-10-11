package com.github.yukihane.hello.java.annotation;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAnnotationProcessor extends AbstractProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAnnotationProcessor.class);

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {

        annotations.stream().map(a -> env.getElementsAnnotatedWith(a)).flatMap(e -> e.stream())
                .filter(e -> e.getAnnotation(Override.class) != null).map(clazz -> "@Override at " + clazz)
                .forEach(LOGGER::info);

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportedAnnotationTypes = new HashSet<>();
        supportedAnnotationTypes.add("*");

        return supportedAnnotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }
}
