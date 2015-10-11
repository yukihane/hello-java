package com.github.yukihane.hello.java.annotation;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

public class MyAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {

        Messager messager = super.processingEnv.getMessager();

        annotations.stream().map(a -> env.getElementsAnnotatedWith(a)).flatMap(e -> e.stream()).forEach(element -> {
            MyAnnotation anno = element.getAnnotation(MyAnnotation.class);

            String msg = "<<@MyAnnotation(\"" + anno.value() + "\")>>\n" + "  Kind : " + element.getKind() + "\n"
                    + "  SimpleName : " + element.getSimpleName() + "\n" + "  Modifiers : " + element.getModifiers()
                    + "\n" + "  asType : " + element.asType() + "\n" + "  EnclosedElements : "
                    + element.getEnclosedElements() + "\n" + "  EnclosingElement : " + element.getEnclosingElement()
                    + "\n" + "  AnnotationMirrors : " + element.getAnnotationMirrors() + "\n";

            System.out.println(msg);
        });

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
