package com.example.springbootauthexample202006;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class SpringbootAuthExample202006Application {

    public static void main(final String[] args) {
        SpringApplication.run(SpringbootAuthExample202006Application.class, args);
    }

}
