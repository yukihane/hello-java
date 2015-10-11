package com.github.yukihane.hello.java.annotation;

@MyAnnotation("anno of class")
public class Hoge {
    @Deprecated
    public void deprecatedMethod() {}

    @MyAnnotation("anno of method")
    @Override
    public String toString() {
        return "hoge";
    }
}
