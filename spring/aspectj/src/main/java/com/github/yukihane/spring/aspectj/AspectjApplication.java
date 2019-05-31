package com.github.yukihane.spring.aspectj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AspectjApplication implements CommandLineRunner {

    @Autowired
    private MyComponent myComponent;

    public static void main(final String[] args) {
        SpringApplication.run(AspectjApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Hello, " + myComponent.getName());
    }

    @Component
    public static class MyComponent {
        public String getName() {
            return this.getClass().getSimpleName();
        }
    }
}
