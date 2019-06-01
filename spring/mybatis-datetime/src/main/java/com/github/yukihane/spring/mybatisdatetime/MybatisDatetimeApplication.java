package com.github.yukihane.spring.mybatisdatetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@EnableSpringConfigured
@SpringBootApplication
public class MybatisDatetimeApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MybatisDatetimeApplication.class, args);
    }

}
