package com.github.yukihane.hello.java.annotation;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

public class MyAnnotationProcessor extends AbstractProcessor {

    private int round = 1;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        System.out.println("Round : " + (this.round++));
        annotations.forEach(System.out::println);
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
