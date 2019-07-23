package com.example.customloginpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CustomLoginPageApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CustomLoginPageApplication.class, args);
    }

}
