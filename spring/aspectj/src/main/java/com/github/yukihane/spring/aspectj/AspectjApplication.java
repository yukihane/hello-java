package com.github.yukihane.spring.aspectj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AspectjApplication implements CommandLineRunner {

    public static void main(final String[] args) {
        SpringApplication.run(AspectjApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Hello, world!");
    }

}
