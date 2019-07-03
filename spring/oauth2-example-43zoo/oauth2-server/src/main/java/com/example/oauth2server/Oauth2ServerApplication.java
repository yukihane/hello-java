package com.example.oauth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

// 11.3. Expression-Based Access Control
// https://docs.spring.io/spring-security/site/docs/5.1.5.RELEASE/reference/htmlsingle/#el-pre-post-annotations
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class Oauth2ServerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

}
