package com.github.yukihane.hello.java.annotation;

@MyAnnotation
public class Hoge {
    @Deprecated
    public void deprecatedMethod() {}

    @Override
    public String toString() {
        return "hoge";
    }
}
