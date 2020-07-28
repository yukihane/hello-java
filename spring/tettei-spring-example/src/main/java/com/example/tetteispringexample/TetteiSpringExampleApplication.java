package com.example.tetteispringexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class TetteiSpringExampleApplication {
    public static void main(final String[] args) {
        SpringApplication.run(TetteiSpringExampleApplication.class, args);
    }
}
