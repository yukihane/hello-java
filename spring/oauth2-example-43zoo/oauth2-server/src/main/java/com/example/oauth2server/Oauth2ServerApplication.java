package com.example.oauth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableAuthorizationServer
@EnableResourceServer
@SpringBootApplication
public class Oauth2ServerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

}
