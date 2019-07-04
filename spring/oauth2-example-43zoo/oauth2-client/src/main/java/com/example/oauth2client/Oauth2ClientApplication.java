package com.example.oauth2client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Oauth2ClientApplication {

    public static void main(final String[] args) {
        SpringApplication.run(Oauth2ClientApplication.class, args);
    }

}
