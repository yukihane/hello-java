package com.github.yukihane.examples;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Hello world!
 */
@Slf4j
public class App {

    public static void main(final String[] args) {
        new App().method1();
    }

    public void method1() {
        method2();
    }

    public void method2() {
        // https://stackoverflow.com/questions/1069066#comment28983454_1069066
        var stackTrace = Thread.currentThread().getStackTrace();
//        log.info("{}", stackTrace);
        var str = Arrays.toString(stackTrace).replace(',', '\n');
//        System.out.println(str);
        log.info("{}", str);
    }
}
