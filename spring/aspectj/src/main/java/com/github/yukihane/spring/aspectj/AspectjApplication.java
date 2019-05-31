package com.github.yukihane.spring.aspectj;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Component;

@EnableSpringConfigured
@SpringBootApplication
public class AspectjApplication implements CommandLineRunner {

    public static void main(final String[] args) {
        SpringApplication.run(AspectjApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println(new MyPojo().getText());
    }

    @Configurable
    public static class MyPojo {
        @Autowired
        private MyComponent myComponent;

        @Getter
        @Setter
        private String greetingText = "Hello, ";

        public String getText() {
            return getGreetingText() + myComponent.getName();
        }
    }

    @Component
    public static class MyComponent {
        public String getName() {
            return this.getClass().getSimpleName();
        }
    }
}
