package com.github.yukihane.springdatajpaquerycreationexample;

import com.github.yukihane.springdatajpaquerycreationexample.entity.MyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaQueryCreationExampleApplication implements CommandLineRunner {

    @Autowired
    private MyEntityRepository repository;


    @Override
    public void run(final String... args) throws Exception {
        repository.findById("MY_ID");
    }

    public static void main(final String[] args) {
        SpringApplication.run(SpringDataJpaQueryCreationExampleApplication.class, args);
    }

}
