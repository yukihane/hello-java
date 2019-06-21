package com.github.yukihane.mybatisassociation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisAssociationApplication implements CommandLineRunner {

    public static void main(final String[] args) {
        SpringApplication.run(MybatisAssociationApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("hello, world");
    }

}
